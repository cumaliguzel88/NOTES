package com.cumaliguzel.noteapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cumaliguzel.noteapp.ui.navigation.Screen
import com.cumaliguzel.noteapp.ui.screens.AddEditNoteScreen
import com.cumaliguzel.noteapp.ui.screens.NoteListScreen
import com.cumaliguzel.noteapp.viewmodel.NoteViewModel

@Composable
fun NoteNavigation(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.NoteList.route,
        modifier = modifier
    ) {
        composable(Screen.NoteList.route) {
            NoteListScreen(
                onAddNote = { navController.navigate(Screen.AddEdit.createRoute()) },
                onEditNote = { noteId -> navController.navigate(Screen.AddEdit.createRoute(noteId)) },
                viewModel = viewModel
            )
        }
        composable(
            route = Screen.AddEdit.route,
            arguments = listOf(navArgument("noteId") {
                type = NavType.StringType
                nullable = true
                defaultValue = null
            })
        ) { backStackEntry ->
            AddEditNoteScreen(
                noteId = backStackEntry.arguments?.getString("noteId"),
                onNavigateBack = { navController.popBackStack() },
                viewModel = viewModel
            )
        }
    }
} 