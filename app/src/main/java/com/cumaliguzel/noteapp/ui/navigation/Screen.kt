package com.cumaliguzel.noteapp.ui.navigation

sealed class Screen(val route: String) {
    object NoteList : Screen("noteList")
    object AddEdit : Screen("addEdit?noteId={noteId}") {
        fun createRoute(noteId: String? = null) = "addEdit${noteId?.let { "?noteId=$it" } ?: ""}"
    }
} 