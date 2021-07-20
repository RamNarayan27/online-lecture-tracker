package com.example.onlinelecturetracker

import androidx.lifecycle.LiveData

class LectureRepository(private val lectureDAO: LectureDAO) {

    val readAllData: LiveData<List<LectureDetails>> = lectureDAO.getBDARecords()

    suspend fun addLecture(lecture: LectureDetails){
        lectureDAO.insert(lecture)
    }

}