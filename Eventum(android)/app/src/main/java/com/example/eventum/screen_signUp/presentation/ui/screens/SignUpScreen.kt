package com.example.eventum.screen_signUp.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AccountCircle
import androidx.compose.material.icons.sharp.Lock
import androidx.compose.material.icons.sharp.Mail
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.eventum.screen_signUp.domain.model.SignUpModel
import com.example.eventum.screen_signUp.domain.model.SignUpRequest
import com.example.eventum.screen_signUp.presentation.event.SignUpEvent
import com.example.eventum.screen_signUp.presentation.ui.components.BasicTextComponent
import com.example.eventum.screen_hello.presentation.ui.components.HeaderTextComponent
import com.example.eventum.screen_signUp.presentation.ui.components.BasicFieldWithIcon
import com.example.eventum.screen_signUp.presentation.ui.components.BasicTextField
import com.example.eventum.screen_signUp.presentation.ui.components.ErrorTextComponent
import com.example.eventum.screen_signUp.presentation.ui.components.SecretTextField
import com.example.eventum.screen_signUp.presentation.ui.components.SecretTextFieldWithIcon
import com.example.eventum.screen_signUp.presentation.viewModel.SignUpViewModel
import com.example.eventum.ui.theme.BackGround


@Composable
@Preview
fun SignUpScreen(navController: NavHostController = rememberNavController(),
                 signUpViewModel: SignUpViewModel = hiltViewModel()) {
    val navigationStatus by signUpViewModel.navigationStatusRead.collectAsState()
    LaunchedEffect(navigationStatus) {
        try {
            navController.navigate(navigationStatus)
        }
        catch (_: Exception) {}
    }

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var secondPassword by remember { mutableStateOf("") }


    Surface (
        modifier = Modifier
            .fillMaxSize()
            .background(BackGround)
            .padding(28.dp)
    ){
        Column(modifier = Modifier.fillMaxSize()
            .background(BackGround)) {
            HeaderTextComponent(value = stringResource(id = R.string.create_account))
//            BasicTextComponent(value = stringResource(id = R.string.hello))
            BasicFieldWithIcon (labelValue = stringResource(id = R.string.your_name),
                Icons.Sharp.AccountCircle) {
                name = it // handling link to function in viewModel
                signUpViewModel.handleEvent(SignUpEvent.NameChanged(it))
            }
            BasicFieldWithIcon (labelValue = stringResource(id = R.string.first_name),
                Icons.Sharp.Mail) {
                email = it // handling link to function in viewModel
                signUpViewModel.handleEvent(SignUpEvent.EmailChanged(it))
                println(it)
            }
            SecretTextFieldWithIcon(labelValue = stringResource(id = R.string.enter_password),
                    Icons.Sharp.Lock)  {
                password = it
                signUpViewModel.handleEvent(SignUpEvent.PasswordChanged(it))
                println(it)
            }
            SecretTextFieldWithIcon(labelValue = stringResource(id = R.string.repeat_password),
                    Icons.Sharp.Lock) {
                secondPassword = it
                signUpViewModel.handleEvent(SignUpEvent.SecondPasswordChanged(it))
                println(it)
            }
            ButtonComponentType1 (labelvalue = stringResource(id = R.string.register)) {
                println("$name, $email, $password, $secondPassword")
                signUpViewModel.handleEvent(
                    SignUpEvent.SignUpFinished(
                        SignUpModel(
                        name, email, password, secondPassword
                    )
                    )
                )
            }
            ErrorTextComponent()

            BackButton { signUpViewModel.handleEvent(SignUpEvent.MoveToHelloPage()) }


        }
    }
}