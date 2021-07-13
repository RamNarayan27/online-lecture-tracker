package com.example.onlinelecturetracker

data class LectureDetails(
    val unitNumber: Int,
    val lectureNumber: Int,
    val lectureName: String,
    val type: String,
    val duration: Int,
    var status: String
)