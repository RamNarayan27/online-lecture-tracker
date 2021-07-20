package com.example.onlinelecturetracker

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lectures_table")
data class LectureDetails(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val unitNumber: Int,
    val lectureNumber: Int,
    val lectureName: String,
    val type: String,
    val duration: Int,
    var status: String,
    var subject: String
)