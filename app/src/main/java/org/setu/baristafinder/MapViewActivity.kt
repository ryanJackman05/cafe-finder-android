package org.setu.baristafinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.setu.baristafinder.models.BaristaModel

class MapViewActivity : AppCompatActivity() {
    // will contain code for map view when implemented. For now will remain an empty view with a return button.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_map)

        val returnButton =
            findViewById<Button>(R.id.returnButton)
        returnButton.setOnClickListener {

            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }
    }
}