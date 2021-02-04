package com.illume.pensieve

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.illume.pensieve.data.Mood
import com.illume.pensieve.ui.main.RecordViewModel
import com.illume.pensieve.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.fragment_record.*

class MainActivity : AppCompatActivity() {

    private lateinit var recordViewModel: RecordViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        recordViewModel = ViewModelProvider(this).get(RecordViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
    }

    private fun clearButtons() {
        smileyVeryHappy.setImageResource(R.drawable.ic_very_happy_24px)
        smileyHappy.setImageResource(R.drawable.ic_happy_24px)
        smileySat.setImageResource(R.drawable.ic_satisfied_24px)
        smileyDis.setImageResource(R.drawable.ic_dissatisfied_24px)
        smileyVeryDis.setImageResource(R.drawable.ic_very_dissatisfied_24px)
    }

    public fun setBlue(v: View) {
        clearButtons()
        smileyVeryHappy.setImageResource(R.drawable.ic_very_happy_blue_24px)
        mood = Mood.VERYHAPPY
    }

    public fun setGreen(v: View) {
        clearButtons()
        smileyHappy.setImageResource(R.drawable.ic_happy_green_24px)
        mood = Mood.HAPPY
    }

    public fun setYellow(v: View) {
        clearButtons()
        smileySat.setImageResource(R.drawable.ic_satisfied_yellow_24px)
        mood = Mood.SATISFIED
    }

    public fun setOrange(v: View) {
        clearButtons()
        smileyDis.setImageResource(R.drawable.ic_dissatisfied_orange_24px)
        mood = Mood.DISSATISFIED
    }

    public fun setRed(v: View) {
        clearButtons()
        smileyVeryDis.setImageResource(R.drawable.ic_very_dissatisfied_red_24px)
        mood = Mood.VERYDISSATISFIED
    }

    private var mood: Int = Mood.SATISFIED
    public fun getMood(): Int {
        return mood
    }
}