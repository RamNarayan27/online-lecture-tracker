package com.example.onlinelecturetracker.lecturedata

import androidx.lifecycle.LiveData
import com.example.onlinelecturetracker.lecturedata.LectureDAO
import com.example.onlinelecturetracker.lecturedata.LectureDetails

class LectureRepository(private val lectureDAO: LectureDAO) {

    val readAllBDAData: LiveData<List<LectureDetails>> = lectureDAO.getBDARecords()
    val totalBDALecturesDuration: LiveData<Int> = lectureDAO.getTotalBDALecturesDuration()
    val readAllCloudData: LiveData<List<LectureDetails>> = lectureDAO.getCloudRecords()
    val totalCloudLecturesDuration: LiveData<Int> = lectureDAO.getTotalCloudLecturesDuration()
    val readAllPDSData: LiveData<List<LectureDetails>> = lectureDAO.getPDSRecords()
    val totalPDSLecturesDuration: LiveData<Int> = lectureDAO.getTotalPDSLecturesDuration()
    val readAllEmbeddedData: LiveData<List<LectureDetails>> = lectureDAO.getEmbeddedRecords()
    val totalEmbeddedLecturesDuration: LiveData<Int> = lectureDAO.getTotalEmbeddedLecturesDuration()
    val readAllElectiveData: LiveData<List<LectureDetails>> = lectureDAO.getElectiveRecords()
    val totalElectiveLecturesDuration: LiveData<Int> = lectureDAO.getTotalElectiveLecturesDuration()

    suspend fun addLecture(lecture: LectureDetails){
        lectureDAO.insert(lecture)
    }

    suspend fun updateLecture(lecture: LectureDetails){
        lectureDAO.updateLecture(lecture)
    }

    suspend fun deleteLecture(lecture: LectureDetails){
        lectureDAO.deleteLecture(lecture)
    }

    suspend fun deleteAllLectures(){
        lectureDAO.deleteAllLectures()
    }

}
