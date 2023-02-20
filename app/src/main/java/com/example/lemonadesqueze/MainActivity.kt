package com.example.lemonadesqueze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonadesqueze.ui.theme.LemonadeSquezeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeSquezeTheme {
                LoadLemonadeApp()
            }
        }
    }
}



@Preview
@Composable
fun LoadLemonadeApp(){
    LoadImagesAndContent()
}

@Composable
fun LoadImagesAndContent() {

    var imgNum by remember {
        mutableStateOf(1)
    }
    var num2Increment = 1
    val imageId = when (imgNum) {
        1 -> painterResource(R.drawable.lemon_tree)
        2 -> painterResource(R.drawable.lemon_squeeze)
        3 -> painterResource(R.drawable.lemon_drink)
        else -> painterResource(R.drawable.lemon_restart)
    }
    val imageContent = when (imgNum) {
        1 -> "Tap the lemon tree to select a lemon"
        2 -> "Keep tapping the lemon to squeeze it"
        3 -> "Tap the lemonade to drink it"
        else -> "Tap the empty glass to start again"
    }
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = imageContent, modifier = Modifier.padding(bottom = 15.dp))
            Button(onClick = {
                if (imgNum == 1) {
                    imgNum = 2
                } else if (imgNum == 2) {
                    if (num2Increment == 1) {
                        num2Increment++
                    } else if ((num2Increment >= 2) && (num2Increment < 3)) {
                        if ((1..100).random() % 2 == 0) {
                            imgNum = 3
                        } else {
                            num2Increment++
                        }
                    } else {
                        imgNum = 3
                    }

                } else if (imgNum == 3) {
                    imgNum = 4
                } else if (imgNum == 4) {
                    imgNum = 1
                }
            }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
                Image(
                    painter = imageId,
                    contentDescription = null,
                    modifier = Modifier.border(border = BorderStroke(1.dp, color = Color.Green))
                )
            }

        }
    }

}



