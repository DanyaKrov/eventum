package com.example.eventum.signUp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventum.R
import com.example.eventum.signUp.ui.components.BasicTextComponent
import com.example.eventum.signUp.ui.components.HeaderTextComponent
import com.example.eventum.signUp.ui.components.BasicTextField
import com.example.eventum.signUp.ui.components.ButtonComponent
import com.example.eventum.signUp.ui.components.SecretTextField

@Composable
@Preview
fun SignUpScreen() {
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ){
        Column(modifier = Modifier.fillMaxSize()) {
            HeaderTextComponent(value = stringResource(id = R.string.create_account))
            BasicTextComponent(value = stringResource(id = R.string.hello))
            BasicTextField(labelValue = stringResource(id = R.string.first_name))
            SecretTextField(labelValue = stringResource(id = R.string.enter_password))
            SecretTextField(labelValue = stringResource(id = R.string.repeat_password))
            ButtonComponent(labelvalue = stringResource(id = R.string.register))
        }
    }
}