package com.example.eventum.screen_profile.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AccountBox
import androidx.compose.material.icons.sharp.AccountCircle
import androidx.compose.material.icons.sharp.Mail
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.eventum.R
import com.example.eventum.domain.model.User
import com.example.eventum.screen_signUp.presentation.event.SignUpEvent
import com.example.eventum.screen_signUp.presentation.ui.components.BasicFieldWithIcon
import com.example.eventum.ui.theme.Montserrat

@Composable
fun ProfileCard(user: User?){
    TwoPartRow("Имя", user?.name  ?: "")
    Divider()
    TwoPartRow("E-mail", user?.email ?:"")
}

@Composable
fun TwoPartRow(label: String, value: String){
    Row(Modifier.fillMaxWidth()
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            "$label:",
            fontFamily = Montserrat,
            fontWeight = FontWeight.Medium,
        )
        Text(
            value,
            fontFamily = Montserrat,
            fontWeight = FontWeight.Medium,
        )
    }
    Spacer(Modifier.height(16.dp))
}

@Composable
fun Profile(onClick: ()->Unit){

}

//@Composable
//fun EditProfile(
//    user: User,
//
//                ){
//    BasicFieldWithIcon (labelValue = stringResource(id = R.string.your_name),
//        Icons.Sharp.AccountCircle) {
//        user.name = it // handling link to function in viewModel
//    }
//    BasicFieldWithIcon (labelValue = stringResource(id = R.string.first_name),
//        Icons.Sharp.Mail) {
//        email = it // handling link to function in viewModel
//        signUpViewModel.handleEvent(SignUpEvent.EmailChanged(it))
//        println(it)
//    }
//}
