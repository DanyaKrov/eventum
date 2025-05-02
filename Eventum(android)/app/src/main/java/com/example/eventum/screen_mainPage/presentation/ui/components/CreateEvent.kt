package com.example.eventum.screen_mainPage.presentation.ui.components

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eventum.domain.model.DomainState
import com.example.eventum.screen_mainPage.domain.model.EventRequestModel
import com.example.eventum.screen_mainPage.presentation.viewModel.MainPageViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEvent (
    context: Context,
    onCreate: (EventRequestModel) -> Unit,
    creationStatus: State<DomainState>,
    snackbarHostState: SnackbarHostState
) {

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    val calendar = remember { Calendar.getInstance() }


    LaunchedEffect(creationStatus.value)  { // idk but this is not working right now(
        creationStatus.value.isSuccess?.let {
            if (it) {
                snackbarHostState.showSnackbar("Событие создано успешно")
                name = ""
                description = ""
            }
        }
    }

    fun openDateTimePicker() {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                selectedDate = format.format(calendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Создать событие",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Название события") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Описание") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = selectedDate,
            onValueChange = {},
            label = { Text("Время события") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { openDateTimePicker() }) {
                    Icon(Icons.Default.CalendarToday, contentDescription = "Выбрать дату и время")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (name.isNotBlank() && selectedDate.isNotBlank()) {
                    val newEvent = EventRequestModel(
                        name = name,
                        description = description,
                        time = selectedDate,
                    )
                    onCreate(newEvent)

                    coroutineScope.launch {
                        name = ""
                        description = ""
                        snackbarHostState.showSnackbar("Событие создано успешно")
                    }
                } else {
                    // show text on how it is incorrect
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Создать событие")
        }
    }
}