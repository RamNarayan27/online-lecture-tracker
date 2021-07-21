package com.example.onlinelecturetracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirstSubjectViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<LectureDetails>>
    private val repository: LectureRepository
    val totalLectureDuration: LiveData<Int>

    init {
        val lectureDAO = LectureDatabase.getDatabase(application).lectureDao()
        repository = LectureRepository(lectureDAO)
        readAllData = repository.readAllData
        totalLectureDuration = repository.totalLectureDuration
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

}
