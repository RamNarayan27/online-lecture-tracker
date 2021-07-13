package com.example.onlinelecturetracker

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BDAAdapter(private val details: MutableList<LectureDetails>) :
    RecyclerView.Adapter<BDAAdapter.BDAViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BDAViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lecture_card, parent, false)
        return BDAViewHolder(view)
    }

    override fun onBindViewHolder(holder: BDAViewHolder, position: Int) {
        holder.unitNumber.text = details[position].unitNumber.toString()
        holder.lectureNumber.text = details[position].lectureNumber.toString()
        holder.lectureName.text = details[position].lectureName
        holder.lectureType.text = details[position].type
        when (details[position].status) {
            "Done" -> {
                holder.status.setImageResource(R.drawable.done)
            }
            "Pending" -> {
                holder.status.setImageResource(R.drawable.pending)
            }
        }
        holder.duration.text = details[position].duration.toString()
        Log.i("onBindViewHolder",position.toString())
    }

//    fun changeStatus() {
//        details[position].status = "Done"
//        notifyItemChanged(position)
//    }

    override fun getItemCount(): Int {
        return details.size
    }

    class BDAViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val unitNumber: TextView = itemView.findViewById(R.id.unit_number)
        val lectureNumber: TextView = itemView.findViewById(R.id.lecture_number)
        val lectureName: TextView = itemView.findViewById(R.id.lecture_name)
        val lectureType: TextView = itemView.findViewById(R.id.lecture_type)
        val status: ImageView = itemView.findViewById(R.id.status)
        val duration: TextView = itemView.findViewById(R.id.duration)
    }

}
