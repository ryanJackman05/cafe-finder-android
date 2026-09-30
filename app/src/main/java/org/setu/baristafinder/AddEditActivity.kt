package org.setu.baristafinder

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.setu.baristafinder.models.BaristaModel

class AddEditActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var addressInput: EditText
    private lateinit var xInput: EditText
    private lateinit var yInput: EditText

    private var editingId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createUserInterface()

        editingId = intent.getLongExtra("id", -1L)

        if (editingId != -1L) {
            loadExistingBarista(editingId!!)
        }
    }

    private fun createUserInterface() {

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        nameInput = EditText(this).apply {
            hint = "Name"
        }

        addressInput = EditText(this).apply {
            hint = "Address"
        }

        xInput = EditText(this).apply {
            hint = "X coordinate"
            inputType =
                android.text.InputType.TYPE_CLASS_NUMBER or
                        android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
        }

        yInput = EditText(this).apply {
            hint = "Y coordinate"
            inputType =
                android.text.InputType.TYPE_CLASS_NUMBER or
                        android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
        }

        val saveButton = Button(this).apply {
            text = "Save"

            setOnClickListener {
                saveBarista()
            }
        }

        val cancelButton = Button(this).apply {
            text = "Cancel"

            setOnClickListener {
                finish()
            }
        }

        root.addView(nameInput)
        root.addView(addressInput)
        root.addView(xInput)
        root.addView(yInput)
        root.addView(saveButton)
        root.addView(cancelButton)

        setContentView(root)
    }

    private fun loadExistingBarista(id: Long) {

        val barista = AppData.baristaStore.findOne(id)

        if (barista == null) {
            Toast.makeText(
                this,
                "Barista not found",
                Toast.LENGTH_SHORT
            ).show()

            finish()
            return
        }

        nameInput.setText(barista.name)
        addressInput.setText(barista.address)
        xInput.setText(barista.x.toString())
        yInput.setText(barista.y.toString())
    }

    private fun saveBarista() {

        val name = nameInput.text.toString().trim()
        val address = addressInput.text.toString().trim()

        if (name.isEmpty()) {
            nameInput.error = "Name is required"
            return
        }

        val x = xInput.text.toString().toDoubleOrNull()

        if (x == null) {
            xInput.error = "Enter a valid number"
            return
        }

        val y = yInput.text.toString().toDoubleOrNull()

        if (y == null) {
            yInput.error = "Enter a valid number"
            return
        }

        if (editingId == null || editingId == -1L) {

            val barista = BaristaModel(
                name = name,
                address = address,
                x = x,
                y = y
            )

            AppData.baristaStore.create(barista)

            Toast.makeText(
                this,
                "Barista created",
                Toast.LENGTH_SHORT
            ).show()

        } else {

            val barista = BaristaModel(
                id = editingId!!,
                name = name,
                address = address,
                x = x,
                y = y
            )

            AppData.baristaStore.update(barista)

            Toast.makeText(
                this,
                "Barista updated",
                Toast.LENGTH_SHORT
            ).show()
        }

        finish()
    }
}
