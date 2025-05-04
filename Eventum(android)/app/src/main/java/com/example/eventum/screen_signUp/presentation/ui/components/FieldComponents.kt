package com.example.eventum.screen_signUp.presentation.ui.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eventum.screen_signUp.presentation.viewModel.SignUpViewModel
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import com.example.eventum.ui.theme.LightGreyCustom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicTextField(labelValue: String, viewModel: SignUpViewModel = hiltViewModel(),
                   changeFunction: (String) -> Unit) {

    var textValue by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textValue,
        onValueChange = {
            textValue = it
            changeFunction(it) }, // call link to function in viewModel
        maxLines = 2,
        label = { Text(labelValue) }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicFieldWithIcon(labelValue: String,
                       icon: ImageVector,
                       viewModel: SignUpViewModel = hiltViewModel(),
                       changeFunction: (String) -> Unit){
    var textValue by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(40.dp),
        value = textValue,
        onValueChange = {
            textValue = it
            changeFunction(it) }, // call link to function in viewModel
        maxLines = 2,
        label = { Text(labelValue)
        },
        leadingIcon = { Icon(icon, contentDescription = "e-mail") },

    )
    Spacer(modifier = Modifier.height(30.dp))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecretTextField(labelValue: String,
                    changeFunction: (String) -> Unit) {

    var textValue by remember { mutableStateOf("") }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textValue,
        onValueChange = {
            textValue = it
            changeFunction(it) }, // call link to function in viewModel
        label = { Text(labelValue) },
        maxLines = 2,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecretTextFieldWithIcon(labelValue: String,
                            icon: ImageVector,
                    changeFunction: (String) -> Unit) {

    var textValue by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(40.dp),
        value = textValue,
        onValueChange = {
            textValue = it
            changeFunction(it) }, // call link to function in viewModel
        label = { Text(labelValue )},
        maxLines = 2,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = { Icon(icon, contentDescription = "password") },

    )
    Spacer(modifier = Modifier.height(30.dp))
}
