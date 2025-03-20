package com.example.eventum.screen_signUp.presentation.ui.screens

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
import com.example.eventum.screen_signUp.presentation.event.SignUpEvent
import com.example.eventum.screen_signUp.presentation.ui.components.BasicTextComponent
import com.example.eventum.screen_signUp.presentation.ui.components.HeaderTextComponent
import com.example.eventum.screen_signUp.presentation.ui.components.BasicTextField
import com.example.eventum.screen_signUp.presentation.ui.components.ButtonComponent
import com.example.eventum.screen_signUp.presentation.ui.components.ErrorTextComponent
import com.example.eventum.screen_signUp.presentation.ui.components.SecretTextField
import com.example.eventum.screen_signUp.presentation.viewModel.SignUpViewModel


@Composable
@Preview
fun SignUpScreen(navController: NavHostController = rememberNavController(),
                 signUpViewModel: SignUpViewModel = hiltViewModel()) {
    val navigationStatus by signUpViewModel.navigationStatusRead.collectAsState()
    LaunchedEffect(navigationStatus) {
        when(navigationStatus) {
            Constants.NAVIGATION_MOVE_TO_LOGIN_PAGE -> navController.navigate("login")
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
            HeaderTextComponent(value = stringResource(id = R.string.create_account))
            BasicTextComponent(value = stringResource(id = R.string.hello))
            BasicTextField(labelValue = stringResource(id = R.string.first_name)) {
                signUpViewModel.handleEvent(SignUpEvent.EmailChanged(it)) // handling link to function in viewModel
            }
            SecretTextField(labelValue = stringResource(id = R.string.enter_password))  {
                signUpViewModel.handleEvent(
                    SignUpEvent.PasswordChanged(it)) }
            SecretTextField(labelValue = stringResource(id = R.string.repeat_password)) {
                signUpViewModel.handleEvent(
                    SignUpEvent.SecondPasswordChanged(it)) }
            ButtonComponent(labelvalue = stringResource(id = R.string.register)) {
                signUpViewModel.handleEvent(
                    SignUpEvent.SignUpFinished()
                )
            }
            ErrorTextComponent()
            ButtonComponent(labelvalue = stringResource(id = R.string.have_account)) {
                signUpViewModel.handleEvent(
                    SignUpEvent.MoveToLogin()
                )
            }
        }
    }
}