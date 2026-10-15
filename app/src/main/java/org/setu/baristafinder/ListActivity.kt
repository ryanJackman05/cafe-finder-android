package org.setu.baristafinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
                // within the object declaration, these methods must be implemented
                override fun onItemClick(barista: BaristaModel) {
                    TODO("Not yet implemented")
                }

                override fun onItemLongClick(barista: BaristaModel): Boolean {
                    TODO("Not yet implemented")
                }
            }
        )

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        recyclerView.adapter = adapter

        val returnButton =
            findViewById<Button>(R.id.returnButton)

        returnButton.setOnClickListener {

            startActivity(
                Intent(this, MainActivity::class.java)
            )
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

    private fun editMark(mark: BaristaModel) {

        val intent =
            Intent(this, AddEditActivity::class.java)

        intent.putExtra("id", mark.id)

        startActivity(intent)
    }

    private fun deleteMark(mark: BaristaModel) {

        AppData.baristaStore.delete(mark.id)

        adapter.updateMarks(
            AppData.baristaStore.findAll()
        )
    }
}

interface BaristaItemListener { // interface of methods for activity to derive from for each item

    fun onItemClick(barista: BaristaModel)

    fun onItemLongClick(barista: BaristaModel): Boolean

}