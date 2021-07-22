package com.example.onlinelecturetracker

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class EmbeddedAdapter(private val context: Context, private val viewModel: FourthSubjectViewModel): RecyclerView.Adapter<EmbeddedAdapter.EmbeddedViewHolder>() {

    private var details = emptyList<LectureDetails>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmbeddedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lecture_card, parent, false)
        return EmbeddedViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmbeddedViewHolder, position: Int) {
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
        holder.card.setOnClickListener{
            val intent = Intent(this.context,UpdateLecture::class.java).apply{
                putExtra("Object",details[position])
            }
            context.startActivity(intent)
        }
        holder.card.setOnLongClickListener {
            deleteLecture(context,viewModel,position)
            true
        }
    }

    private fun deleteLecture(context: Context, viewModel: FourthSubjectViewModel, position: Int) {
        val builder = AlertDialog.Builder(context)
        builder.setPositiveButton("Yes"){_,_ ->
            viewModel.deleteLecture(details[position])
            Toast.makeText(context,"Successfully Deleted", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_,_ ->
            Toast.makeText(context,"Cancelled", Toast.LENGTH_SHORT).show()
        }
        builder.setTitle("Delete Lecture")
        builder.setMessage("Do you want to delete the lecture?")
        builder.create().show()
    }

    override fun getItemCount(): Int {
        return details.size
    }

    fun setData(lectures: List<LectureDetails>){
        this.details = lectures
        notifyDataSetChanged()
    }

    class EmbeddedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val unitNumber: TextView = itemView.findViewById(R.id.unit_number_input_layout)
        val lectureNumber: TextView = itemView.findViewById(R.id.lecture_number_input_layout)
        val lectureName: TextView = itemView.findViewById(R.id.lecture_name_input_layout)
        val lectureType: TextView = itemView.findViewById(R.id.lecture_type_input_layout)
        val status: ImageView = itemView.findViewById(R.id.status)
        val duration: TextView = itemView.findViewById(R.id.duration)
        val card: CardView = itemView.findViewById(R.id.card_layout)
    }

}
