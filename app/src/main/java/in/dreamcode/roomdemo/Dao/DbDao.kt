package `in`.dreamcode.roomdemo.Dao

import android.arch.persistence.room.*

@Dao
interface DbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteModel: NoteModel): Long

    @Update
    fun updateNote(noteModel: NoteModel): Int

    @Delete
    fun deleteNote(noteModel: NoteModel): Int

    @Query("SELECT * FROM NoteModel")
    fun getAllNote(): List<NoteModel>

    @Query("SELECT * FROM NoteModel WHERE id= :noteId")
    fun getNoteById(noteId: Int): NoteModel

}