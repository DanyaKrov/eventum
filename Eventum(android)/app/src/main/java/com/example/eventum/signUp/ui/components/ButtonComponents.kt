package com.example.eventum.signUp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.eventum.signUp.vIewModel.SignUpViewModel
import com.example.eventum.signUp.event.SignUpEvent

@Composable
fun ButtonComponent(labelvalue: String, viewModel: SignUpViewModel = viewModel()) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { viewModel.handleEvent(SignUpEvent.ButtonClicked())},
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(color = Color.Magenta),
            contentAlignment = Alignment.Center
            ) {
            Text(
                text = labelvalue,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
                )
        }

    }
}