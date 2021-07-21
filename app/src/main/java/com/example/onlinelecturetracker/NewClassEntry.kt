package com.example.onlinelecturetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText

class NewClassEntry : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_class_entry)
        val fragmentName = intent.getStringExtra("FragmentName")
        val firstSubjectViewModel = ViewModelProvider(this).get(FirstSubjectViewModel::class.java)
        val secondSubjectViewModel = ViewModelProvider(this).get(SecondSubjectViewModel::class.java)
        val thirdSubjectViewModel = ViewModelProvider(this).get(ThirdSubjectViewModel::class.java)
        val fourthSubjectViewModel = ViewModelProvider(this).get(FourthSubjectViewModel::class.java)
        val fifthSubjectViewModel = ViewModelProvider(this).get(FifthSubjectViewModel::class.java)
        val submitButton = findViewById<Button>(R.id.submit)

        submitButton.setOnClickListener{
            val unitNumber = findViewById<TextInputEditText>(R.id.unit_number_edit_text).text.toString().toInt()
            val lectureNumber = findViewById<TextInputEditText>(R.id.lecture_number_edit_text).text.toString().toInt()
            val lectureName = findViewById<TextInputEditText>(R.id.lecture_name_edit_text).text.toString()
            val type = findViewById<TextInputEditText>(R.id.lecture_type_edit_text).text.toString()
            val duration = findViewById<TextInputEditText>(R.id.duration_edit_text).text.toString().toInt()
            val status = findViewById<TextInputEditText>(R.id.lecture_status_edit_text).text.toString()
            val subject = findViewById<TextInputEditText>(R.id.subject_edit_text).text.toString()
            val lecture = LectureDetails(0,unitNumber,lectureNumber, lectureName, type, duration, status, subject)
            when(fragmentName){
                "FirstSubject" -> {
                    firstSubjectViewModel.addLecture(lecture)
                }
                "SecondSubject" -> {

                }
                "ThirdSubject" -> {

                }
                "FourthSubject" -> {

                }
                "FifthSubject" -> {

                }
            }
            Toast.makeText(this,"Successfully Added",Toast.LENGTH_SHORT).show()
            onBackPressed()
            finish()
        }
    }
}
