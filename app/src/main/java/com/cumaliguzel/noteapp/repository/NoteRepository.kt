package com.cumaliguzel.noteapp.repository

import com.cumaliguzel.noteapp.data.Note
import com.cumaliguzel.noteapp.data.NotePreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(private val preferences: NotePreferences) {
    suspend fun getAllNotes(): List<Note> = withContext(Dispatchers.IO) {
        preferences.getNotes()
    }

    suspend fun addNote(note: Note) = withContext(Dispatchers.IO) {
        val notes = preferences.getNotes().toMutableList()
        notes.add(0, note) // Add new note at the beginning
        preferences.saveNotes(notes)
    }

    suspend fun updateNote(note: Note) = withContext(Dispatchers.IO) {
        val notes = preferences.getNotes().toMutableList()
        val index = notes.indexOfFirst { it.id == note.id }
        if (index != -1) {
            notes[index] = note
            preferences.saveNotes(notes)
        }
    }

    suspend fun deleteNote(id: String) = withContext(Dispatchers.IO) {
        val notes = preferences.getNotes().toMutableList()
        notes.removeAll { it.id == id }
        preferences.saveNotes(notes)
    }
} 