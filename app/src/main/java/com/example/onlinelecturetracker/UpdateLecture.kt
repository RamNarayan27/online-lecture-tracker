package com.example.onlinelecturetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import java.time.Duration

class UpdateLecture : AppCompatActivity() {
    private lateinit var lectureNumber: TextInputEditText
    private lateinit var unitNumber: TextInputEditText
    private lateinit var lectureName: TextInputEditText
    private lateinit var lectureType: TextInputEditText
    private lateinit var duration: TextInputEditText
    private lateinit var status: TextInputEditText
    private lateinit var subject: TextInputEditText
    private lateinit var submitButton: Button
    private lateinit var viewModel: FirstSubjectViewModel

    /**
     * TODO : Since I am only testing with a single fragment the code will work,
     * TODO : but when i am extending the app to all the fragments the viewModels must be updated accordingly.
     * TODO : Update ViewModels accordingly.
     * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_lecture)
        viewModel = ViewModelProvider(this).get(FirstSubjectViewModel::class.java)
        val lecture = intent.getParcelableExtra<LectureDetails>("Object")

        lectureNumber = findViewById(R.id.lecture_number_edit_text_update)
        unitNumber = findViewById(R.id.unit_number_edit_text_update)
        lectureName = findViewById(R.id.lecture_name_edit_text_update)
        lectureType = findViewById(R.id.lecture_type_edit_text_update)
        duration = findViewById(R.id.duration_edit_text_update)
        status = findViewById(R.id.lecture_status_edit_text_update)
        subject = findViewById(R.id.subject_edit_text_update)
        submitButton = findViewById(R.id.submit_update)

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
            viewModel.updateLecture(updatedLecture)
            /** TODO : Add code to navigate back to the original fragment.*/
        }
    }
}
