package com.example.onlinelecturetracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BDAAdapter(val details: MutableList<LectureDetails>) :
    RecyclerView.Adapter<BDAAdapter.BDAViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BDAViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lecture_card, parent, false)
        return BDAViewHolder(view)
    }

    override fun onBindViewHolder(holder: BDAViewHolder, position: Int) {
        holder.unitNumber.text = details[position].unitNumber.toString()
        holder.lectureNumber.text = details[position].lectureNumber.toString()
        holder.lectureName.text = details[position].lectureName.toString()
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
    }

    override fun getItemCount(): Int {
        return details.size
    }

    class BDAViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val unitNumber = itemView.findViewById<TextView>(R.id.unit_number)
        val lectureNumber = itemView.findViewById<TextView>(R.id.lecture_number)
        val lectureName = itemView.findViewById<TextView>(R.id.lecture_name)
        val lectureType = itemView.findViewById<TextView>(R.id.lecture_type)
        val status = itemView.findViewById<ImageView>(R.id.status)
        val duration = itemView.findViewById<TextView>(R.id.duration)
    }

}
