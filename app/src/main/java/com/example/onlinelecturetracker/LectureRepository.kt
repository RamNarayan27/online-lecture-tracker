package com.example.onlinelecturetracker

import androidx.lifecycle.LiveData

class LectureRepository(private val lectureDAO: LectureDAO) {

    val readAllData: LiveData<List<LectureDetails>> = lectureDAO.getBDARecords()
    val totalLectureDuration: LiveData<Int> = lectureDAO.getTotalLectureDuration()

    suspend fun addLecture(lecture: LectureDetails){
        lectureDAO.insert(lecture)
    }

    suspend fun updateLecture(lecture: LectureDetails){
        lectureDAO.updateLecture(lecture)
    }

}
