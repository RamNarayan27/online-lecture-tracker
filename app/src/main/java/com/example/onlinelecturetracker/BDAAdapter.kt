package com.example.onlinelecturetracker

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BDAAdapter() :
    RecyclerView.Adapter<BDAAdapter.BDAViewHolder>() {

    private var details = emptyList<LectureDetails>()

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

    fun setData(lectures: List<LectureDetails>){
        this.details = lectures
        notifyDataSetChanged()
    }

    class BDAViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val unitNumber: TextView = itemView.findViewById(R.id.unit_number_input_layout)
        val lectureNumber: TextView = itemView.findViewById(R.id.lecture_number_input_layout)
        val lectureName: TextView = itemView.findViewById(R.id.lecture_name_input_layout)
        val lectureType: TextView = itemView.findViewById(R.id.lecture_type_input_layout)
        val status: ImageView = itemView.findViewById(R.id.status)
        val duration: TextView = itemView.findViewById(R.id.duration)
    }

}
