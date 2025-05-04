package com.example.eventum.screen_hello.presentation.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eventum.screen_signUp.presentation.viewModel.SignUpViewModel
import com.example.eventum.ui.theme.Montserrat
import com.example.eventum.ui.theme.TextColor

@Composable
fun BasicTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = androidx.compose.ui.text.TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = TextColor,
        textAlign = TextAlign.Center
    )
}


@Composable
fun HeaderTextComponent(value: String) {
    Spacer(modifier = Modifier.height(100.dp))
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp),
        style = androidx.compose.ui.text.TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = Montserrat
        ),
        color = TextColor,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(50.dp))
}


@Composable
fun ErrorTextComponent(signUpViewModel: SignUpViewModel = hiltViewModel()) {
    val textValue by signUpViewModel.model.value.requirementsStatement.collectAsState()
    Text(
        text = textValue,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 60.dp),
        style = androidx.compose.ui.text.TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = TextColor,
        textAlign = TextAlign.Center
    )
}