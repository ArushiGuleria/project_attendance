package com.example.facerecog

import android.os.Parcel
//import android.os.Parcelable
//import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.Nullable
import java.io.Serializable

//@Parcelize
data class attendanceData(
    val id: String,
    @Nullable val name: String?

): Serializable {
  //  constructor(parcel: Parcel) : this(
   //     parcel.readString()!!,
   //     parcel.readString()!!,

    //)

  //  override fun writeToParcel(parcel: Parcel, flags: Int) {
   //     parcel.writeString(id)
   //     parcel.writeString(name)
   // }

  //  override fun describeContents(): Int {
    //    return 0
   // }

//    companion object CREATOR : Parcelable.Creator<attendanceData> {
    //    override fun createFromParcel(parcel: Parcel): attendanceData {
    //        return attendanceData(parcel)
   //     }

      //  override fun newArray(size: Int): Array<attendanceData?> {
          //  return arrayOfNulls(size)
    //    }
  //  }
}
