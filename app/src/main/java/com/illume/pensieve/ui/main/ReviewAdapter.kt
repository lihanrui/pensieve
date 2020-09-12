package com.illume.pensieve.ui.main

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.illume.pensieve.R
import com.illume.pensieve.data.Mood
import com.illume.pensieve.data.RecordEntity
import kotlinx.android.synthetic.main.recycler_card.view.*
import java.text.SimpleDateFormat
import java.util.*

class ReviewAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<ReviewAdapter.MyViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var records = emptyList<RecordEntity>()

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.imageView
        val itemTime = itemView.recycler_time
        val itemTitle = itemView.recycler_title
        val itemContent = itemView.recycler_content
        val itemLocation = itemView.recycler_location
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val constraintView =
            inflater.inflate(R.layout.recycler_card, parent, false)
        return MyViewHolder(constraintView)
    }

    override fun getItemCount(): Int {
        return records.size// maybe return an error?
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val record = records[position]
        when(record.mood ){
            Mood.VERYHAPPY-> {
                holder.imageView.setImageResource(R.drawable.ic_very_happy_blue_24px)
            }
            Mood.HAPPY-> {
                holder.imageView.setImageResource(R.drawable.ic_happy_green_24px)
            }
            Mood.SATISFIED-> {
                holder.imageView.setImageResource(R.drawable.ic_satisfied_yellow_24px)
            }
            Mood.DISSATISFIED-> {
                holder.imageView.setImageResource(R.drawable.ic_dissatisfied_orange_24px)
            }
            Mood.VERYDISSATISFIED-> {
                holder.imageView.setImageResource(R.drawable.ic_very_dissatisfied_red_24px)
            }
        }
        holder.itemTime.text = convertLongToTime(record.time)
        holder.itemTitle.text = record.title
        holder.itemContent.text = record.note
        holder.itemLocation
    }

     fun setRecords(recordList : List<RecordEntity>){
        this.records = recordList
        notifyDataSetChanged()
    }

    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("MM.dd.yyyy HH:mm")
        return format.format(date)
    }
}