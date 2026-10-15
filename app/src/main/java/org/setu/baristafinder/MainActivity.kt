package org.setu.baristafinder

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.content.Intent
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        } // applies insets to the main view by the system's bottom and top navigation

        val viewMarksButton =
            findViewById<Button>(R.id.savedListButton)

        val addMarkButton =
            findViewById<Button>(R.id.addButton)

        viewMarksButton.setOnClickListener {
            startActivity(
                Intent(this, ListActivity::class.java)
            )
        }

        addMarkButton.setOnClickListener {
            startActivity(
                Intent(this, AddEditActivity::class.java)
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
                        Intent(this, ListActivity::class.java)
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
                    true
                }

                else -> false
            }
        }
    }
}