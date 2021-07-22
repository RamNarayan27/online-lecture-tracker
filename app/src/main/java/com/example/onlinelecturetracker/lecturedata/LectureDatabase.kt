package com.example.onlinelecturetracker.lecturedata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LectureDetails::class], version = 1, exportSchema = false)
abstract class LectureDatabase: RoomDatabase() {

    abstract fun lectureDao(): LectureDAO

    companion object{
        @Volatile
        private var INSTANCE: LectureDatabase? = null

        fun getDatabase(context: Context): LectureDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LectureDatabase::class.java,
                    "lectures_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}