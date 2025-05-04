package com.example.eventum.screen_login.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Lock
import androidx.compose.material.icons.sharp.Mail
import androidx.compose.material3.Icon
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
import com.example.eventum.screen_hello.presentation.ui.components.BackButton
import com.example.eventum.screen_hello.presentation.ui.components.ButtonComponentType1
import com.example.eventum.screen_login.presentation.event.LoginEvent
import com.example.eventum.screen_login.presentation.viewModel.LoginViewModel
import com.example.eventum.screen_signUp.presentation.ui.components.BasicFieldWithIcon
import com.example.eventum.screen_signUp.presentation.ui.components.BasicTextField
import com.example.eventum.screen_hello.presentation.ui.components.HeaderTextComponent
import com.example.eventum.screen_signUp.presentation.ui.components.ErrorTextComponent
import com.example.eventum.screen_signUp.presentation.ui.components.SecretTextField
import com.example.eventum.screen_signUp.presentation.ui.components.SecretTextFieldWithIcon
import com.example.eventum.ui.theme.BackGround

@Composable
@Preview
fun LoginScreen(navController: NavHostController = rememberNavController(),
                viewModel: LoginViewModel = hiltViewModel()) {
    val navigationStatus by viewModel.navigationStatusRead.collectAsState()
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
            HeaderTextComponent(value = stringResource(id = R.string.login_into_account))
//            BasicTextField(labelValue = stringResource(id = R.string.first_name)) {
//                viewModel.handleEvent(LoginEvent.EmailChanged(it))
//            }
            BasicFieldWithIcon (labelValue = stringResource(id = R.string.first_name),
                    Icons.Sharp.Mail
            ) {
                viewModel.handleEvent(LoginEvent.EmailChanged(it))
            }
            SecretTextFieldWithIcon (labelValue = stringResource(id = R.string.enter_password),
                Icons.Sharp.Lock) {
                viewModel.handleEvent(LoginEvent.PasswordChanged(it))
            }
            ErrorTextComponent()
            ButtonComponentType1(labelvalue = stringResource(id = R.string.login)) {
                viewModel.handleEvent(LoginEvent.LoginFinished())
            }
        }

        BackButton { viewModel.handleEvent(LoginEvent.MoveToHello()) }

    }
}