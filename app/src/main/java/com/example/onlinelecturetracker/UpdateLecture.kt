package com.example.onlinelecturetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText

class UpdateLecture : AppCompatActivity() {
    private lateinit var lectureNumber: TextInputEditText
    private lateinit var unitNumber: TextInputEditText
    private lateinit var lectureName: TextInputEditText
    private lateinit var lectureType: TextInputEditText
    private lateinit var duration: TextInputEditText
    private lateinit var status: TextInputEditText
    private lateinit var subject: TextInputEditText
    private lateinit var submitButton: Button
    private lateinit var firstSubjectViewModel: FirstSubjectViewModel
    private lateinit var secondSubjectViewModel: SecondSubjectViewModel
    private lateinit var thirdSubjectViewModel: ThirdSubjectViewModel
    private lateinit var fourthSubjectViewModel: FourthSubjectViewModel
    private lateinit var fifthSubjectViewModel: FifthSubjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_lecture)
        firstSubjectViewModel = ViewModelProvider(this).get(FirstSubjectViewModel::class.java)
        secondSubjectViewModel = ViewModelProvider(this).get(SecondSubjectViewModel::class.java)
        thirdSubjectViewModel = ViewModelProvider(this).get(ThirdSubjectViewModel::class.java)
        fourthSubjectViewModel = ViewModelProvider(this).get(FourthSubjectViewModel::class.java)
        fifthSubjectViewModel = ViewModelProvider(this).get(FifthSubjectViewModel::class.java)

        val lecture = intent.getParcelableExtra<LectureDetails>("Object")

        lectureNumber = findViewById(R.id.lecture_number_edit_text)
        unitNumber = findViewById(R.id.unit_number_edit_text)
        lectureName = findViewById(R.id.lecture_name_edit_text)
        lectureType = findViewById(R.id.lecture_type_edit_text)
        duration = findViewById(R.id.duration_edit_text)
        status = findViewById(R.id.lecture_status_edit_text)
        subject = findViewById(R.id.subject_edit_text)
        submitButton = findViewById(R.id.submit)

        lectureNumber.setText(lecture?.lectureNumber.toString())
        unitNumber.setText(lecture?.unitNumber.toString())
        lectureName.setText(lecture?.lectureName.toString())
        lectureType.setText(lecture?.type.toString())
        duration.setText(lecture?.duration.toString())
        status.setText(lecture?.status.toString())
        subject.setText(lecture?.subject.toString())
        submitButton.setOnClickListener {
            val updatedLecture = LectureDetails(
                lecture!!.id,
                unitNumber.text.toString().toInt(),
                lectureNumber.text.toString().toInt(),
                lectureName.text.toString(),
                lectureType.text.toString(),
                duration.text.toString().toInt(),
                status.text.toString(),
                subject.text.toString()
            )
            when(lecture.subject){
                "BDA" -> {
                    firstSubjectViewModel.updateLecture(updatedLecture)
                }
                "Cloud" -> {
                    secondSubjectViewModel.updateLecture(updatedLecture)
                }
                "PDS" -> {
                    thirdSubjectViewModel.updateLecture(updatedLecture)
                }
                "Embedded" -> {
                    fourthSubjectViewModel.updateLecture(updatedLecture)
                }
                "Elective" -> {
                    fifthSubjectViewModel.updateLecture(updatedLecture)
                }
            }
            onBackPressed()
            finish()
        }
    }
}
