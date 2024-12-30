package com.cumaliguzel.noteapp.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NotePreferences(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("notes_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveNotes(notes: List<Note>) {
        val json = gson.toJson(notes)
        sharedPreferences.edit()
            .putString(NOTES_KEY, json)
            .apply()
    }

    fun getNotes(): List<Note> {
        val json = sharedPreferences.getString(NOTES_KEY, null)
        return if (json != null) {
            val type = object : TypeToken<List<Note>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }

    companion object {
        private const val NOTES_KEY = "notes_key"
    }
} 