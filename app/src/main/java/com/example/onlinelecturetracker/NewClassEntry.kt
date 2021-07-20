package com.example.onlinelecturetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class NewClassEntry : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_class_entry)
        val message = intent.getStringExtra("Subject")
        val viewModel = ViewModelProvider(this).get(FirstSubjectViewModel::class.java)
        val submitButton = findViewById<Button>(R.id.submit)
//        when(message) {
//            "BDA" -> { Toast.makeText(this,"Hello From BDA",Toast.LENGTH_SHORT).show() }
//            "Cloud" -> { Toast.makeText(this,"Hello From Cloud",Toast.LENGTH_SHORT).show() }
//            "PDS" -> { Toast.makeText(this,"Hello From PDS",Toast.LENGTH_SHORT).show() }
//            "Embedded" -> { Toast.makeText(this,"Hello From Embedded",Toast.LENGTH_SHORT).show() }
//            "Elective" -> { Toast.makeText(this,"Hello From Elective",Toast.LENGTH_SHORT).show() }
//        }
        submitButton.setOnClickListener{
            addDataToDatabase(viewModel)
        }
//        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
    private fun addDataToDatabase(viewModel: FirstSubjectViewModel) {
        val unitNumber = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.unit_number_edit_text).text.toString().toInt()
        val lectureNumber = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.lecture_number_edit_text).text.toString().toInt()
        val lectureName = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.lecture_name_edit_text).text.toString()
        val type = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.lecture_type_edit_text).text.toString()
        val duration = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.duration_edit_text).text.toString().toInt()
        val status = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.lecture_status_edit_text).text.toString()
        val subject = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.subject_edit_text).text.toString()
        val lecture = LectureDetails(0,unitNumber,lectureNumber, lectureName, type, duration, status, subject)
        viewModel.addLecture(lecture)
        Toast.makeText(this,"Successfully Added",Toast.LENGTH_SHORT).show()
    }
}
