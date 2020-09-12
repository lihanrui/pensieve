package com.illume.pensieve

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.illume.pensieve.data.Mood
import com.illume.pensieve.ui.main.RecordViewModel
import com.illume.pensieve.ui.main.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {
    //    val db = Room.databaseBuilder(
//        applicationContext,
//        AppDatabase::class.java, "user_records.db"
//    ).build()

    private var mood: Int = Mood.SATISFIED

    private lateinit var recordViewModel: RecordViewModel
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
        recordViewModel = ViewModelProvider(this).get(RecordViewModel::class.java)
    }


    private fun clearButtons() {
        val image1 = findViewById<ImageView>(R.id.smileyVeryHappy)
        val image2 = findViewById<ImageView>(R.id.smileyHappy)
        val image3 = findViewById<ImageView>(R.id.smileySat)
        val image4 = findViewById<ImageView>(R.id.smileyDis)
        val image5 = findViewById<ImageView>(R.id.smileyVeryDis)
        val imageViews = arrayOf<ImageView>(image1, image2, image3, image4, image5)
        for (iView in imageViews) {
            iView.setColorFilter(null)
        }
    }

    public fun setBlue(v: View) {
        clearButtons()
        val iView = findViewById<ImageView>(R.id.smileyVeryHappy)
        iView.setColorFilter(ContextCompat.getColor(this, R.color.colorBlue))
        mood = Mood.VERYHAPPY
    }

    public fun setGreen(v: View) {
        clearButtons()
        val iView = findViewById<ImageView>(R.id.smileyHappy)
        iView.setColorFilter(ContextCompat.getColor(this, R.color.colorGreen))
        mood = Mood.HAPPY
    }

    public fun setYellow(v: View) {
        clearButtons()
        val iView = findViewById<ImageView>(R.id.smileySat)
        iView.setColorFilter(ContextCompat.getColor(this, R.color.colorYellow))
        mood = Mood.SATISFIED
    }

    public fun setOrange(v: View) {
        clearButtons()
        val iView = findViewById<ImageView>(R.id.smileyDis)
        iView.setColorFilter(ContextCompat.getColor(this, R.color.colorOrange))
        mood = Mood.DISSATISFIED
    }

    public fun setRed(v: View) {
        clearButtons()
        val iView = findViewById<ImageView>(R.id.smileyVeryDis)
        iView.setColorFilter(ContextCompat.getColor(this, R.color.colorRed))
        mood = Mood.VERYDISSATISFIED
    }

    public fun getMood(): Int {
        return mood
    }

}