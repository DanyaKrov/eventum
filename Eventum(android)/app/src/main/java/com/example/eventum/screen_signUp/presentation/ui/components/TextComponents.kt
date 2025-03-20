package com.example.eventum.screen_signUp.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
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
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp),
        style = androidx.compose.ui.text.TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = TextColor,
        textAlign = TextAlign.Center
    )
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