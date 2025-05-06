package com.example.eventum.screen_hello.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventum.R
import com.example.eventum.screen_hello.presentation.ui.components.HeaderTextComponent
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.eventum.screen_hello.presentation.event.HelloEvent
import com.example.eventum.screen_hello.presentation.ui.components.ButtonComponentType1
import com.example.eventum.screen_hello.presentation.ui.components.ButtonComponentType2
import com.example.eventum.screen_hello.presentation.viewModel.HelloViewModel
import com.example.eventum.ui.theme.BackGround

@Composable
@Preview
fun HelloScreen(navController: NavHostController = rememberNavController(),
                  helloViewModel: HelloViewModel = hiltViewModel()) {
    val navigationStatus by helloViewModel.navigationStatusRead.collectAsState()
    LaunchedEffect(navigationStatus) {
        try {
            navController.navigate(navigationStatus)
        }
        catch (_: Exception) {}
    }
    Surface (
    modifier = Modifier
    .fillMaxSize()
    .background(BackGround)
    .padding(28.dp)
    ){
        Column(modifier = Modifier.fillMaxSize()
            .background(BackGround)) {

            Spacer(modifier = Modifier.height(150.dp))
            HeaderTextComponent(value = stringResource(id = R.string.Welcome))
            Spacer(modifier = Modifier.height(100.dp))
            ButtonComponentType1 (stringResource(id = R.string.SignIn)) {
                helloViewModel.handleEvent(
                    HelloEvent.MoveToLogin()
                )
            }
            Spacer(modifier = Modifier.height(44.dp))
            ButtonComponentType2(stringResource(id = R.string.SignUp)) {
                helloViewModel.handleEvent(
                    HelloEvent.MoveToSignUp()
                )
            }
        }
    }
}