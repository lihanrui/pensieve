package com.henryli.tabbed.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.henryli.tabbed.MainActivity
import com.henryli.tabbed.R
import com.henryli.tabbed.data.Mood
import com.henryli.tabbed.data.RecordEntity
import com.henryli.tabbed.utils.Utils
import java.text.SimpleDateFormat
import java.util.*
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


/**
 * A record fragment for recording user journal entries
 */
class RecordFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private lateinit var recordViewModel: RecordViewModel
    private val utils = Utils()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
        recordViewModel = ViewModelProviders.of(this).get(RecordViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_record, container, false)

        val fab: View = root.findViewById<FloatingActionButton>(R.id.submitFab)
        fab.setOnClickListener { view ->
            lifecycleScope.launch{
                saveUserData(view)
            }
        }
        return root
    }

    override fun onResume() {
//        val textView: TextView? = view?.findViewById(R.id.section_label)
//        pageViewModel.text.observe(viewLifecycleOwner, Observer<String> {
//            textView?.text = it
//        })
        setTimeUi()
        super.onResume()
    }

    private fun setTimeUi() {
        val now = Calendar.getInstance().time
        val dateView = view?.findViewById<TextView>(R.id.date_current)
        val timeView = view?.findViewById<TextView>(R.id.time_current)
        val dateFormat = SimpleDateFormat("MM-dd-yyyy")
        val timeFormat = SimpleDateFormat("HH:mm")
        dateView?.text = dateFormat.format(now)
        timeView?.text = timeFormat.format(now)
    }

    // time, location, mood, title, note, action category
    private suspend fun saveUserData(view: View) {
        val now = Calendar.getInstance().time        // todo get time from user fields
        val mLocation = utils.getCurrentLocation(context)
        val mMood: Int = Mood.SATISFIED
        val mTitle = view?.findViewById<TextView>(R.id.title_edit)?.text
        val mNote = view?.findViewById<TextView>(R.id.details_edit)?.text
        // todo action category
        val record = RecordEntity()
        if (mLocation != null) {
            record.lat = mLocation.latitude
            record.lon = mLocation.longitude
        }
        record.mood = mMood
        record.title = mTitle.toString()
        record.note = mNote.toString()
        val dao = MainActivity.getDb().recordDao()
        dao.insert(record)
        val size = dao.getAll().value?.size
        Snackbar.make(view, "Saving! ${size}", Snackbar.LENGTH_LONG)
            .setAction("Click text to make snackbar go away", View.OnClickListener() {
                //                    Log.e("TESTING", "SAVING SNACK CLICK")
            })
            .show()
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a  new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): RecordFragment {
            return RecordFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}