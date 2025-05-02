package com.example.todo.presentation.screens.addtodo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo.data.repository.TodoRepository
import com.example.todo.presentation.screens.dashboard.DashboardViewModel
// 1. add the viewmodel with the function to operate
// 2. OnDismiss as our form will be showcased on a pop  up
@Composable
fun AddToDoForm(
    viewModel: () -> Unit,
    onDismiss: () -> Unit
){
    // variable to save inputs
    var title by remember{ mutableStateOf("") }
    var description by remember{ mutableStateOf("") }
    var tasker by remember{ mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
         Text(
             text = "Add To DO",
             style = MaterialTheme.typography.headlineMedium,
             modifier = Modifier.padding(bottom = 16.dp)
         )

        OutlinedTextField(
            value =  title , onValueChange = {title = it},
            label = { Text("Title")},
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value =  description , onValueChange = {description = it},
            label = { Text("Description")},
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value =  tasker , onValueChange = {tasker = it},
            label = { Text("Tasker Name")},
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = onDismiss,
                colors = ButtonDefaults.buttonColors()) {
                Text("Cancel")
            }
            Button(onClick = {
                if(title.isNotBlank()){
                    //viewModel.addToDO(title,description,tasker)
                    onDismiss()
                }
            }, enabled = title.isNotBlank()) {
                 Text("Add to do")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun AddToDoFormPreview(){

    AddToDoForm(viewModel = {}, onDismiss = {})
}











