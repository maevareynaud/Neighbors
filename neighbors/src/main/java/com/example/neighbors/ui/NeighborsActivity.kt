package com.example.neighbors.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.neighbors.NavigationListener
import com.example.neighbors.R
import com.example.neighbors.di.DI
import com.example.neighbors.ui.fragments.ListNeighborsFragment

class NeighborsActivity : AppCompatActivity(), NavigationListener {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DI.inject(application)

        setContentView(R.layout.neighbors_activity)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        showFragment(ListNeighborsFragment())

        /* val button: Button = findViewById(R.id.add_button)
        button.setOnClickListener {
            /*val intent = Intent(baseContext, MySecondActivity::class.java)
            startActivity(intent)*/

            showFragment(AddNeighborFragment())

            // Lancer une url
            /*val url = Uri.parse("http://google.fr")
            val i = Intent(Intent.ACTION_VIEW, url)
            startActivity(i)*/

            // Composer un numéro de téléphone
            /*val url = Uri.parse("tel:0675990569")
            val i = Intent(Intent.ACTION_DIAL, url)
            startActivity(i)*/
        }*/
    }

    /*
     * Changer de fragment
     */
    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun updateTitle(title: Int) {
        toolbar.setTitle(title)
    }
}
