package com.example.eventum.mainPage.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventum.R
import com.example.eventum.mainPage.viewModel.MainPageViewModel
import com.example.eventum.signUp.event.SignUpEvent
import com.example.eventum.signUp.ui.components.BasicTextComponent
import com.example.eventum.signUp.ui.components.BasicTextField
import com.example.eventum.signUp.ui.components.ButtonComponent
import com.example.eventum.signUp.ui.components.ErrorTextComponent
import com.example.eventum.signUp.ui.components.HeaderTextComponent
import com.example.eventum.signUp.ui.components.SecretTextField
import com.example.eventum.signUp.viewModel.SignUpViewModel

@Composable
@Preview
fun EventsScreen(navController: NavHostController = rememberNavController(),
                 viewModel: MainPageViewModel = hiltViewModel()
) {
}