package com.cumaliguzel.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.cumaliguzel.noteapp.data.NotePreferences
import com.cumaliguzel.noteapp.repository.NoteRepository
import com.cumaliguzel.noteapp.ui.NoteNavigation
import com.cumaliguzel.noteapp.ui.theme.NoteAppTheme
import com.cumaliguzel.noteapp.viewmodel.NoteViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val preferences = NotePreferences(this)
        val repository = NoteRepository(preferences)
        val factory = viewModelFactory {
            initializer {
                NoteViewModel(repository)
            }
        }
        
        setContent {
            NoteAppTheme {
                val viewModel: NoteViewModel = viewModel(factory = factory)
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NoteNavigation(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}