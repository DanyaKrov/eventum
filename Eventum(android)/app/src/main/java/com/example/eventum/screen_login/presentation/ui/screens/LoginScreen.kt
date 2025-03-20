package com.example.eventum.screen_login.presentation.ui.screens

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
import com.example.eventum.common.Constants
import com.example.eventum.screen_login.presentation.event.LoginEvent
import com.example.eventum.screen_login.presentation.viewModel.LoginViewModel
import com.example.eventum.screen_signUp.presentation.ui.components.BasicTextField
import com.example.eventum.screen_signUp.presentation.ui.components.ButtonComponent
import com.example.eventum.screen_signUp.presentation.ui.components.HeaderTextComponent
import com.example.eventum.screen_signUp.presentation.ui.components.SecretTextField

@Composable
@Preview
fun LoginScreen(navController: NavHostController = rememberNavController(),
                viewModel: LoginViewModel = hiltViewModel()) {
    val navigationStatus by viewModel.navigationStatusRead.collectAsState()
    LaunchedEffect(navigationStatus) {
        when(navigationStatus) {
            "move_to_signUp" -> navController.navigate("sign_up")
            Constants.NAVIGATION_MOVE_TO_MAIN_PAGE -> navController.navigate("main_page")
        }
    }
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ){
        Column(modifier = Modifier.fillMaxSize()) {
            HeaderTextComponent(value = stringResource(id = R.string.login_into_account))
            BasicTextField(labelValue = stringResource(id = R.string.first_name)) {
                viewModel.handleEvent(LoginEvent.EmailChanged(it))
            }
            SecretTextField(labelValue = stringResource(id = R.string.enter_password)) {
                viewModel.handleEvent(LoginEvent.PasswordChanged(it))
            }
            ButtonComponent(labelvalue = stringResource(id = R.string.login)) {
                viewModel.handleEvent(LoginEvent.LoginFinished())
            }
        }
    }
}