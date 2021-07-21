package com.example.onlinelecturetracker

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize

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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(unitNumber)
        parcel.writeInt(lectureNumber)
        parcel.writeString(lectureName)
        parcel.writeString(type)
        parcel.writeInt(duration)
        parcel.writeString(status)
        parcel.writeString(subject)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LectureDetails> {
        override fun createFromParcel(parcel: Parcel): LectureDetails {
            return LectureDetails(parcel)
        }

        override fun newArray(size: Int): Array<LectureDetails?> {
            return arrayOfNulls(size)
        }
    }
}
