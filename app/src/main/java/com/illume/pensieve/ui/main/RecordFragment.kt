package com.illume.pensieve.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.illume.pensieve.MainActivity
import com.illume.pensieve.R
import com.illume.pensieve.data.Mood
import com.illume.pensieve.data.RecordEntity
import com.illume.pensieve.utils.Utils
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * A record fragment for recording user journal entries
 */
class RecordFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private lateinit var recordViewModel: RecordViewModel
    private val utils = Utils()
    private lateinit var now: Date
    private var mMood: Int = Mood.SATISFIED
    private lateinit var mTitle: EditText
    private lateinit var mNote: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
        recordViewModel = ViewModelProviders.of(activity!!).get(RecordViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_record, container, false)

        val fab: View = root.findViewById<FloatingActionButton>(R.id.submitFab)
        fab.setOnClickListener { view ->
            lifecycleScope.launch {
                saveUserData(view)
            }
        }
        now = Calendar.getInstance().time        // todo get time from user fields
        activity.let{
            val act = it as MainActivity
            mMood = act.getMood()
        }
        mTitle = root.findViewById<EditText>(R.id.title_edit)
        mNote = root.findViewById<EditText>(R.id.details_edit)
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
        now = Calendar.getInstance().time        // todo get time from user fields
        val location = utils.getCurrentLocation(context)
//        mMood: Int = Mood.SATISFIED
        // todo action category
        val record = RecordEntity()
        if (location != null) {
            record.lat = location.latitude
            record.lon = location.longitude
        }
        activity.let{
            val act = it as MainActivity
            mMood = act.getMood()
        }
        record.mood = mMood
        record.title = mTitle.text.toString()
        record.note = mNote.text.toString()
        recordViewModel.insert(record)
        val size = recordViewModel.allRecords.value?.size
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