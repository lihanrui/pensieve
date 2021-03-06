package com.illume.pensieve.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.illume.pensieve.R
import com.illume.pensieve.data.RecordEntity

/**
 * A display fragment for the database items.
 */
class ReviewFragment : Fragment() {

    private lateinit var recordViewModel: RecordViewModel
//    private lateinit var textView: TextView
    private lateinit var reviewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var records : LiveData<List<RecordEntity>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recordViewModel = ViewModelProviders.of(activity!!).get(RecordViewModel::class.java)
        records = recordViewModel.allRecords
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_review, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.review_cardview).apply {
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
            adapter = ReviewAdapter(this@ReviewFragment.context!!)
            adapter?.let{
                reviewAdapter = it
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        records.observe(
            activity!!,
            Observer<List<RecordEntity>> { recordList: List<RecordEntity>? ->
                // Update the UI.
                if (recordList != null && recordList.size > 0) {
                    val adapter = reviewAdapter as ReviewAdapter
                    adapter.setRecords(recordList)
                }
            })
        super.onViewCreated(view, savedInstanceState)
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