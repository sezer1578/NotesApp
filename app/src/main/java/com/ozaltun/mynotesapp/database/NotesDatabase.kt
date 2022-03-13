package com.ozaltun.mynotesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ozaltun.mynotesapp.dao.NotesDAO
import com.ozaltun.mynotesapp.model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun myNotesDao(): NotesDAO

    companion object {
        var INSTANCE: NotesDatabase? = null

        fun getDatabaseInstance(context: Context) : NotesDatabase {
            val temInstance = INSTANCE
            if(temInstance!=null) {
                return temInstance
            }
            synchronized(this){
                val roomDatabaseInstance = Room.databaseBuilder(context,NotesDatabase::class.java,"notes").allowMainThreadQueries().build()
                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }
    }

}