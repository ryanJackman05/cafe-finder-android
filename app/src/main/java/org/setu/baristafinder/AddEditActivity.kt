package org.setu.baristafinder

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.setu.baristafinder.models.BaristaModel
import org.setu.placemark.MapPickerActivity

class AddEditActivity : AppCompatActivity() {

    // 1-to-1 fields with add_edit
    private lateinit var nameInput: EditText
    private lateinit var addressInput: EditText
    private lateinit var xInput: EditText
    private lateinit var yInput: EditText
    private lateinit var imagePreview: ImageView
    private var selectedImageUri: Uri? = null;

    private var selectedLatitude: Double? = null;
    private var selectedLongitude: Double? = null;

    private var editingId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_edit)

        nameInput =
            findViewById(R.id.nameInput)

        addressInput =
            findViewById(R.id.addressInput)

        xInput =
            findViewById(R.id.xInput)

        yInput =
            findViewById(R.id.yInput)

        val saveButton =
            findViewById<Button>(R.id.saveButton)

        val cancelButton =
            findViewById<Button>(R.id.cancelButton)

        imagePreview = findViewById(R.id.imageView)
        val imagePicker =
            registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->
                if (uri != null) {
                    // Keep access to the document URI for later use, when supported.
                    try {
                        contentResolver.takePersistableUriPermission(
                            uri,
                            android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
                        )
                    } catch (_: SecurityException) {
                        // Some providers may not grant persistable access.
                    }

                    selectedImageUri = uri
                    imagePreview.setImageURI(uri)
                }
            }

        findViewById<Button>(R.id.imageButton).setOnClickListener {
            imagePicker.launch(arrayOf("image/*"))
        }

        val mapPicker =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == RESULT_OK) {
                    val data = result.data ?: return@registerForActivityResult
                    selectedLatitude = data.getDoubleExtra("latitude", 0.0)
                    selectedLongitude = data.getDoubleExtra("longitude", 0.0)

                    selectedLatitude?.let {
                        xInput.setText(it.toString())
                    }

                    selectedLongitude?.let {
                        yInput.setText(it.toString())
                    }
                }
            }

        findViewById<Button>(R.id.buttonLoc).setOnClickListener {
            val intent = Intent(this, MapPickerActivity::class.java)
            mapPicker.launch(intent)
        }

        val bottomNavigation =
            findViewById<BottomNavigationView>(
                R.id.bottom_navigation
            )

        bottomNavigation.setOnItemSelectedListener { item ->

            when (item.itemId) {

                R.id.m_viewSaved -> {
                    startActivity(
                        Intent(this, ListActivity::class.java)
                    )
                    true
                }

                R.id.m_addNew -> {
                    startActivity(
                        Intent(this, MainActivity::class.java)
                    )
                    true
                }

                R.id.m_viewMap -> {
                    startActivity(
                        Intent(this, MapViewActivity::class.java)
                    )
                    true
                }

                else -> false
            }
        }

        // editing only works if loadExistingMark has all the fields, so it's called after the view mapping
        editingId =
            intent.getLongExtra("id", -1L)
                .takeIf { it != -1L }

        if (editingId != null) {

            findViewById<TextView>(R.id.formTitle)
                .text = "Edit Mark"

            loadExistingMark(editingId!!)
        }

        saveButton.setOnClickListener {
            saveMark()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun loadExistingMark(id: Long) {

        val mark =
            AppData.baristaStore.findOne(id)

        if (mark == null) {

            Toast.makeText(
                this,
                "Mark not found",
                Toast.LENGTH_SHORT
            ).show()

            finish()
            return
        }

        nameInput.setText(mark.name)
        addressInput.setText(mark.address)
        xInput.setText(mark.x.toString())
        yInput.setText(mark.y.toString())
        selectedImageUri = mark.imageUri?.let { Uri.parse(it) }

        if (selectedImageUri != null) {
            imagePreview.setImageURI(selectedImageUri)
        } else {
            imagePreview.setImageResource(android.R.drawable.ic_menu_gallery)
        }
    }

    private fun saveMark() {

        val name =
            nameInput.text.toString().trim()

        val address =
            addressInput.text.toString().trim()

        if (title.isEmpty()) {
            nameInput.error = "Title is required"
            return
        }

        val x =
            xInput.text.toString().toDoubleOrNull()

        if (x == null) {
            xInput.error = "Enter a valid number"
            return
        }

        val y =
            yInput.text.toString().toDoubleOrNull()

        if (y == null) {
            yInput.error = "Enter a valid number"
            return
        }

        if (editingId == null) {
            try{ // to catch failed validate() calls from the barista store
                val mark = BaristaModel(
                    name = name,
                    address = address,
                    x = x,
                    y = y,
                    imageUri = selectedImageUri?.toString()
                )

                AppData.baristaStore.create(mark)

                Toast.makeText(
                    this,
                    "Mark created",
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: IllegalArgumentException) {
                android.widget.Toast.makeText(
                    this,
                    e.message ?: "Please check the entered values.",
                    android.widget.Toast.LENGTH_LONG
                ).show()
            }

        } else {

            val mark = BaristaModel(
                id = editingId!!,
                name = name,
                address = address,
                x = x,
                y = y,
                imageUri = selectedImageUri?.toString()
            )

            AppData.baristaStore.update(mark)

            Toast.makeText(
                this,
                "Mark updated",
                Toast.LENGTH_SHORT
            ).show()
        }

        finish()
    }
}
