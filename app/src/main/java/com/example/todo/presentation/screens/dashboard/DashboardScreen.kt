package com.example.todo.presentation.screens.dashboard
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todo.data.model.TodoItem
import com.example.todo.presentation.components.DrawerContent
import com.example.todo.presentation.components.TodoItemCard
import com.example.todo.presentation.screens.addtodo.AddToDoForm
import com.example.todo.presentation.screens.addtodo.EditToDoForm
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val todos by viewModel.todos.collectAsState()
    val firebassetodos by viewModel.firebaseTodos.collectAsState()

    val showAddDialog = remember { mutableStateOf(false) }
    val showEditDialog = remember { mutableStateOf(false) }
    val todoBeingEdited = remember { mutableStateOf<TodoItem?>(null) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            DrawerContent(
                onNavigateToHome = {
                    navController.navigate("dashboard")
                },
                onLogout = {
                    FirebaseAuth.getInstance().signOut()
                    navController.navigate("login") {
                        popUpTo("dashboard") { inclusive = true }
                    }
                }
            )
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Dashboard") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { showAddDialog.value = true }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Todo")
                }
            }
        ) { padding ->
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(firebassetodos) { todo ->
                    TodoItemCard(
                        todo = todo,
                        onCompleteChange = { viewModel.toogleTodoCompletion(todo.id) },
                        onEditClick = {
                            todoBeingEdited.value = it
                            showEditDialog.value = true
                        },
                        onDeleteClick = { viewModel.deleteTodoFromFirebase(it) }
                    )
                }
            }

            if (showAddDialog.value) {
                AlertDialog(
                    onDismissRequest = { showAddDialog.value = false },
                    title = { Text("Add Todo") },
                    text = {
                        AddToDoForm(
                            viewModel = viewModel,
                            onDismiss = { showAddDialog.value = false }
                        )
                    },
                    confirmButton = {},
                    dismissButton = {}
                )
            }

            if (showEditDialog.value && todoBeingEdited.value != null) {
                AlertDialog(
                    onDismissRequest = { showEditDialog.value = false },
                    title = { Text("Edit Todo") },
                    text = {
                        EditToDoForm(
                            todo = todoBeingEdited.value!!,
                            onSubmit = { updatedTodo ->
                                viewModel.updateTodoFromFirebase(updatedTodo)
                                showEditDialog.value = false
                            },
                            onDismiss = { showEditDialog.value = false }
                        )
                    },
                    confirmButton = {},
                    dismissButton = {}
                )
            }
        }
    }
}
