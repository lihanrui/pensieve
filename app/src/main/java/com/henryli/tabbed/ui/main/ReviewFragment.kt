package com.henryli.tabbed.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.henryli.tabbed.MainActivity
import com.henryli.tabbed.R
import com.henryli.tabbed.data.RecordEntity

/**
 * A display fragment for the database items.
 */
class ReviewFragment : Fragment() {

    private lateinit var recordViewModel: RecordViewModel
//    private lateinit var textView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var reviewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recordViewModel = ViewModelProviders.of(this).get(RecordViewModel::class.java).apply {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_review, container, false)
//        textView = root.findViewById(R.id.section_label)
//        recordViewModel.text.observe(viewLifecycleOwner, Observer<String> {
//            textView.text = it
//        })
        val dao = MainActivity.getDb().recordDao()
        val records: LiveData<List<RecordEntity>> = dao.getAll()
//        if (records.value != null) {
//            textView.text = "Found LiveData value"
//        }

        recyclerView = root.findViewById<RecyclerView>(R.id.review_cardview).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = LinearLayoutManager(this@ReviewFragment.context)
            val lm = layoutManager as LinearLayoutManager?
            lm?.let {
                it.orientation = LinearLayoutManager.VERTICAL
            }
            // specify an viewAdapter (see also next example)
            adapter = ReviewAdapter(records)
            adapter?.let{
                reviewAdapter = it
            }
        }


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao = MainActivity.getDb().recordDao()
        val records: LiveData<List<RecordEntity>> = dao.getAll()
        records.observe(
            viewLifecycleOwner,
            Observer<List<RecordEntity>> { recordList: List<RecordEntity>? ->
                // Update the UI.
                if (recordList != null && recordList.size > 0) {
//                    textView.text = "Size: ${recordList.size} Title0 ${recordList.get(0).title}"
                    reviewAdapter = ReviewAdapter(records)
                }
            })
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment. Used by SectionsPagerAdapter
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): ReviewFragment {
            return ReviewFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}