package com.example.eventum.screen_hello.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SubdirectoryArrowLeft
import androidx.compose.material.icons.sharp.Man
import androidx.compose.material.icons.sharp.SubdirectoryArrowLeft
import androidx.compose.material3.Button
//import androidx.compose.material.icons.sharp.SubdirectoryArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventum.ui.theme.Montserrat
import com.example.eventum.ui.theme.SoftLightOrange

import com.example.eventum.ui.theme.SoftRed
import com.example.eventum.ui.theme.SoftLightRed
import com.example.eventum.ui.theme.SoftOrange


@Composable
fun ButtonComponentType1(labelvalue: String,
                    clickFunction: () -> Unit) {
    Text(
        text = labelvalue,
        style = TextStyle(color = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .clickable(onClick = clickFunction)
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        SoftRed,
                        SoftLightRed
                    )
                ),
                shape = RoundedCornerShape(50.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = Montserrat,
        textAlign = TextAlign.Center
    )

    }

@Composable
fun ButtonComponentType2(labelvalue: String,
                         clickFunction: () -> Unit) {
//    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = labelvalue,
        style = TextStyle(color = Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(54.dp)
            .clickable(onClick = clickFunction)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        SoftOrange,
                        SoftLightOrange
                    )
                ),
                shape = RoundedCornerShape(50.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = Montserrat,
        textAlign = TextAlign.Center
    )
}

@Composable
fun BackButton(clickFunction: () -> Unit){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    )
    {
        IconButton (
            onClick = clickFunction,
            modifier = Modifier.size(75.dp)
                .background(Color.White, shape = RoundedCornerShape(30.dp))

        ) {
            // Inner content including an icon and a text label
            Icon(
                imageVector = Icons.Default.SubdirectoryArrowLeft,
                contentDescription = "back"
            )
        }
    }

}