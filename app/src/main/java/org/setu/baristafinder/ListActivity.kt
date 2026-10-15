package org.setu.baristafinder

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

import org.setu.baristafinder.models.BaristaModel

class ListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SavedBaristaAdapter // TODO add

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recyclerView =
            findViewById(R.id.marksRecyclerView)

        adapter = SavedBaristaAdapter( // Adapter handles data -> view/partial mapping, AppData handles storage
            baristaModels = AppData.baristaStore.findAll(),

            listener = object : BaristaItemListener { // create anonymous/blank object that implements (:) BaristaItemListener
                // within the object declaration, these functions must be implemented
                override fun onItemClick(barista: BaristaModel) {
                    editBarista(barista)
                }

                override fun onItemLongClick(barista: BaristaModel): Boolean {
                    ConfirmDialog(this@ListActivity).show( // construct Dialog handler, and run .show
                        headerText = "Delete Saved Barista",
                        dialogText = "Delete Barista ${barista.name}?",
                        onConfirm = { deleteBarista(barista) }
                    )
                    return true // confirm dialog created, TODO OPT check if this is practical or needed
                }
            }
        )

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        recyclerView.adapter = adapter

        val nameSearch =
            findViewById<EditText>(R.id.nameSearchInput)
        nameSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s!!.isEmpty()){
                    adapter.updateMarks(AppData.baristaStore.findAll()); // normal
                    return
                }
                val bl = ArrayList<BaristaModel>()
                for (b in AppData.baristaStore.findAll()){
                    if(b.name.contains(s,ignoreCase=true)){
                        bl.add(b)
                    }
                }
                adapter.updateMarks(bl)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        })

        val returnButton =
            findViewById<Button>(R.id.returnButton)
        returnButton.setOnClickListener {

            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }

        // new button for quickly making data
        val createButton =
            findViewById<Button>(R.id.createButton)
        createButton.setOnClickListener {
            createMany()
            adapter.updateMarks(
                AppData.baristaStore.findAll()
            )
        }

        val bottomNavigation =
            findViewById<BottomNavigationView>(
                R.id.bottom_navigation
            )

        bottomNavigation.setOnItemSelectedListener { item ->

            when (item.itemId) {

                R.id.m_viewSaved -> {
                    startActivity(
                        Intent(this, MainActivity::class.java)
                    )
                    true
                }

                R.id.m_addNew -> {
                    startActivity(
                        Intent(this, AddEditActivity::class.java)
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
    }

    override fun onResume() {
        super.onResume()

        if (::adapter.isInitialized) {

            adapter.updateMarks(
                AppData.baristaStore.findAll()
            )
        }
    }

    private fun editBarista(mark: BaristaModel) {

        val intent =
            Intent(this, AddEditActivity::class.java)

        intent.putExtra("id", mark.id)

        startActivity(intent)
    }

    private fun deleteBarista(mark: BaristaModel) {

        AppData.baristaStore.delete(mark.id)

        adapter.updateMarks(
            AppData.baristaStore.findAll()
        )
    }

    private fun createMany(){
        AppData.baristaStore.create(BaristaModel(
            name="Tina's Cantina",
            address="52 Foylewood Business Park,\nBallybrack, Co. Waterlow\nWQS 98LP",
            //description="A local cafe with a wide drink selection and delicious freshly-made lunch items",
            x=20.2,y=4.0
        ))
        AppData.baristaStore.create(BaristaModel(
            name="Coffee Joe",
            address="Upper John Street,\nCillcomer, Co. Waterlow\nWQS 5GH2",
            //description="Branch of the Coffee Joe company. Includes the famous Joe's Blend bean option, and a drive-thru",
            x=4.6,y=5.7
        ))
        AppData.baristaStore.create(BaristaModel(
            name="Beanright",
            address="Ponberry Shopping Center,\nWaterlow, Co. Waterlow",
            //description="Branch of the Coffee Joe company. Includes the famous Joe's Blend bean option, and a drive-thru",
            x=1.3,y=0.9
        ))
    }
}

interface BaristaItemListener { // interface of methods for activity to derive from for each item

    fun onItemClick(barista: BaristaModel)

    fun onItemLongClick(barista: BaristaModel): Boolean

}