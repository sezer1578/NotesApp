package com.ozaltun.mynotesapp.repository

import androidx.lifecycle.LiveData
import com.ozaltun.mynotesapp.dao.NotesDAO
import com.ozaltun.mynotesapp.model.Notes

class NotesRepository(val dao: NotesDAO) {

    fun getallNotes(): LiveData<List<Notes>> = dao.getNotes()
    fun getLowNotes(): LiveData<List<Notes>> = dao.getLowNotes()
    fun getHighNotes(): LiveData<List<Notes>> = dao.getHighNotes()
    fun getNormalmNotes(): LiveData<List<Notes>> = dao.getNormalNotes()

    fun insertNotes(notes: Notes) {
        dao.insertNotes(notes)
    }

    fun deleteNotes(id: Int) {
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes) {
        dao.updateNotes(notes)
    }
}