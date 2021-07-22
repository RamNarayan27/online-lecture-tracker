package com.example.onlinelecturetracker

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LectureDAO {
    @Query("SELECT * FROM lectures_table WHERE subject='BDA' ORDER BY lectureNumber ASC")
    fun getBDARecords() : LiveData<List<LectureDetails>>

    @Query("SELECT * FROM lectures_table WHERE subject='Cloud'")
    fun getCloudRecords() : LiveData<List<LectureDetails>>

    @Query("SELECT * FROM lectures_table WHERE subject='PDS'")
    fun getPDSRecords() : LiveData<List<LectureDetails>>

    @Query("SELECT * FROM lectures_table WHERE subject='Embedded'")
    fun getEmbeddedRecords() : LiveData<List<LectureDetails>>

    @Query("SELECT * FROM lectures_table WHERE subject='Elective'")
    fun getElectiveRecords() : LiveData<List<LectureDetails>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(lecture: LectureDetails)

    @Update
    fun updateLecture(lecture: LectureDetails)

    @Query("SELECT SUM(duration) from lectures_table WHERE status='Pending' AND subject='BDA'")
    fun getTotalBDALecturesDuration() : LiveData<Int>

    @Query("SELECT SUM(duration) from lectures_table WHERE status='Pending' AND subject='Cloud'")
    fun getTotalCloudLecturesDuration() : LiveData<Int>

    @Query("SELECT SUM(duration) from lectures_table WHERE status='Pending' AND subject='PDS'")
    fun getTotalPDSLecturesDuration() : LiveData<Int>

    @Query("SELECT SUM(duration) from lectures_table WHERE status='Pending' AND subject='Embedded'")
    fun getTotalEmbeddedLecturesDuration() : LiveData<Int>

    @Query("SELECT SUM(duration) from lectures_table WHERE status='Pending' AND subject='Elective'")
    fun getTotalElectiveLecturesDuration() : LiveData<Int>

    @Delete
    suspend fun deleteLecture(lecture: LectureDetails)

    @Query("DELETE FROM lectures_table")
    suspend fun deleteAllLectures()
}
