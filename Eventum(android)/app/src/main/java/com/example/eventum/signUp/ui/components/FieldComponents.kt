package com.example.eventum.signUp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.eventum.signUp.vIewModel.SignUpViewModel
import com.example.eventum.signUp.event.SignUpEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicTextField(labelValue: String, viewModel: SignUpViewModel = viewModel()) {

    var textValue by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textValue,
        onValueChange = {
            textValue = it
            viewModel.handleEvent(SignUpEvent.EmailChanged(it)) },
        maxLines = 2,
        label = { Text(labelValue) }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecretTextField(labelValue: String, viewModel: SignUpViewModel = viewModel()) {

    var textValue by remember { mutableStateOf("") }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textValue,
        onValueChange = {
            textValue = it
            viewModel.handleEvent(SignUpEvent.PasswordChanged(it))},
        label = { Text(labelValue) },
        maxLines = 2,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}
