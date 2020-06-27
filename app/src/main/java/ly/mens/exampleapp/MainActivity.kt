package ly.mens.exampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import ly.mens.exampleapp.view.list.ItemListFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureActionBar()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.content, ItemListFragment())
                .commit()
        }
    }

    private fun configureActionBar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        supportFragmentManager.addOnBackStackChangedListener {
            val showBackButton = supportFragmentManager.backStackEntryCount > 0
            supportActionBar?.setDisplayShowHomeEnabled(showBackButton)
            supportActionBar?.setDisplayHomeAsUpEnabled(showBackButton)
        }
    }
}