package com.ozaltun.mynotesapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ozaltun.mynotesapp.model.Notes

@Dao
interface NotesDAO {
    @Query("SELECT * FROM notes")
    fun getNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM notes WHERE priority=3")
    fun getHighNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM notes WHERE priority=2")
    fun getNormalNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM notes WHERE priority=1")
    fun getLowNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes:Notes)

    @Query("DELETE FROM notes WHERE id=:id")
    fun deleteNotes(id:Int)

    @Update
    fun updateNotes(notes:Notes)

}