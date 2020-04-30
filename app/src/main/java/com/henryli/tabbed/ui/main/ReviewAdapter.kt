package com.henryli.tabbed.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.henryli.tabbed.R
import com.henryli.tabbed.data.RecordEntity
import kotlinx.android.synthetic.main.recycler_card.view.*

class ReviewAdapter(private val records: LiveData<List<RecordEntity>>) :
    RecyclerView.Adapter<ReviewAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(itemView: ConstraintLayout) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.imageView
        val recycleTitle = itemView.recycler_title
        val recycleContent = itemView.recycler_content
        val recycleLocation = itemView.recycler_location
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val constraintView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_card, parent, false) as ConstraintLayout
        return MyViewHolder(constraintView)
    }

    override fun getItemCount(): Int {
        records.value?.let {
            return it.size
        }
        return 0 // maybe return an error?
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        records.value?.let {
            val record = it.get(position)
            holder.imageView
            holder.recycleTitle.text = record.title
            holder.recycleContent.text = record.note
            holder.recycleLocation
        }
    }

//    // Create new views (invoked by the layout manager)
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): ReviewAdapter.MyViewHolder {
//        // create a new view
//        val constraintLayoutCard = LayoutInflater.from(parent.context)
//            .inflate(R.layout.recycler_card, parent, false) as TextView
//        // set the view's size, margins, paddings and layout parameters
//        return MyViewHolder(constraintLayoutCard)
//    }


//
//    // Replace the contents of a view (invoked by the layout manager)
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        // - get element from your dataset at this position
//        // - replace the contents of the view with that element
//        holder.textView.text = myDataset[position]
//    }
//
//    // Return the size of your dataset (invoked by the layout manager)
//    override fun getItemCount() = records.size

}