package com.henryli.tabbed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.henryli.tabbed.data.AppDatabase
import com.henryli.tabbed.ui.main.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {
    //    val db = Room.databaseBuilder(
//        applicationContext,
//        AppDatabase::class.java, "user_records.db"
//    ).build()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
//        val fab: FloatingActionButton = findViewById(R.id.fab)
//
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }


    }

    override fun onResume() {
        super.onResume()
        db = AppDatabase(this)
    }

    companion object {
        private lateinit var db: AppDatabase

        // onResume should have initialized db by this point
        fun getDb(): AppDatabase {
            return db
        }
    }
}