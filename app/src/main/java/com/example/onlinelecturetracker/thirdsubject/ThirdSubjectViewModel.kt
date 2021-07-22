package com.example.onlinelecturetracker.thirdsubject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.onlinelecturetracker.lecturedata.LectureDatabase
import com.example.onlinelecturetracker.lecturedata.LectureDetails
import com.example.onlinelecturetracker.lecturedata.LectureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ThirdSubjectViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<LectureDetails>>
    private val repository: LectureRepository
    val totalLectureDuration: LiveData<Int>

    init {
        val lectureDAO = LectureDatabase.getDatabase(application).lectureDao()
        repository = LectureRepository(lectureDAO)
        readAllData = repository.readAllPDSData
        totalLectureDuration = repository.totalPDSLecturesDuration
    }

    fun addLecture(lecture: LectureDetails){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLecture(lecture)
        }
    }

    fun updateLecture(lecture: LectureDetails){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLecture(lecture)
        }
    }

    fun deleteLecture(lecture: LectureDetails){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteLecture(lecture)
        }
    }

    fun deleteAllLectures() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllLectures()
        }
    }
}
