package com.ozaltun.mynotesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ozaltun.mynotesapp.database.NotesDatabase
import com.ozaltun.mynotesapp.model.Notes
import com.ozaltun.mynotesapp.repository.NotesRepository

class NotesViewModel(application: Application) :AndroidViewModel(application) {

    val repository : NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }
    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }
    fun getNotes():LiveData<List<Notes>> = repository.getallNotes()
    fun getLowNotes():LiveData<List<Notes>> = repository.getLowNotes()
    fun getHighNotes():LiveData<List<Notes>> = repository.getHighNotes()
    fun getNormalNotes():LiveData<List<Notes>> = repository.getNormalmNotes()

    fun deleteNotes(id:Int){
        repository.deleteNotes(id)
    }
    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }
}