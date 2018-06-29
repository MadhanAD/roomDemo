package `in`.dreamcode.roomdemo.Dao

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable


@Entity(tableName = "NoteModel")
class NoteModel() : Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
    @ColumnInfo(name = "title")
    var title: String = ""
    @ColumnInfo(name = "description")
    var description: String = ""
    @ColumnInfo(name = "dateTime")
    var dataTime: Long = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        title = parcel.readString() as String
        description = parcel.readString() as String
        dataTime = parcel.readLong()
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id!!)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeLong(dataTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NoteModel> {
        override fun createFromParcel(parcel: Parcel): NoteModel {
            return NoteModel(parcel)
        }

        override fun newArray(size: Int): Array<NoteModel?> {
            return arrayOfNulls(size)
        }
    }
}