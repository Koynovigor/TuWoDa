package com.itsc.tuwoda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.itsc.tuwoda.ui.theme.MyFABWithText

class MainActivity : ComponentActivity() {

    private val model = MyViewModel()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                bottomBar = {
                    MyBottomBar(model = model)
                },
                content = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        contentAlignment = Alignment.TopEnd,
                    ){
                        MyFloatingActionButton(
                            background = R.drawable.more,
                            icon = R.drawable.morepoint,
                            size = 50.dp
                        )
                    }
                },
                floatingActionButton = {
                    Column(
                        modifier = Modifier.offset(y = 25.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Box(
                            contentAlignment = Alignment.TopEnd
                        ) {
                            if (model.stateRouting){
                                MyFABWithText(
                                    text = "Построить новый маршрут",
                                    x = (-15).dp,
                                    y = (-45).dp,
                                    paddingCardHorizontal = 20.dp,
                                    paddingTextHorizontal = 10.dp,
                                    sizeButton = 45.dp,
                                    backgroundButton = R.drawable.ellipsefull,
                                    iconButton = R.drawable.plus,
                                    colorBackgroundButton = R.color.blue_light_main,
                                    colorBackgroundText = R.color.blue_light_main_alfa,
                                    model = model
                                )
                                if(model.stateMapDialog){
                                    if (model.stateMapDialog){
                                        AlertDialog(
                                            containerColor = colorResource(id = R.color.blue_main_alfa),
                                            onDismissRequest = { model.stateMapDialog = false },
                                            confirmButton = {
                                                Button(
                                                    onClick = { /*TODO*/ }
                                                ) {
                                                    Text(text = "Построить")
                                                }
                                            },
                                            dismissButton = {
                                                Button(
                                                    onClick = { /*TODO*/ }
                                                ) {
                                                    Text(text = "Назад")
                                                }
                                            },
                                            text = {
                                                Column {
                                                    ItemDialog(
                                                        name = "Начальная точка",
                                                        colorName = Color.White,
                                                        state = model.stateTextName,
                                                        onState = {state ->
                                                            model.stateTextName = state
                                                        }
                                                    )
                                                    ItemDialog(
                                                        name = "Конечная точка",
                                                        colorName = Color.White,
                                                        state = model.stateTextTitle,
                                                        onState = {state ->
                                                            model.stateTextTitle = state
                                                        }
                                                    )
                                                }

                                            },
                                        )
                                    }

                                }
                                MyFABWithText(
                                    backgroundButton = R.drawable.ellipsefull,
                                    iconButton = R.drawable.star,
                                    colorBackgroundButton = R.color.blue_light_main,
                                    colorBackgroundText = R.color.blue_light_main_alfa,
                                    text = "Повторить маршрут",
                                    sizeButton = 45.dp,
                                    x = (-65).dp,
                                    paddingCardHorizontal = 20.dp,
                                    paddingTextHorizontal = 10.dp,
                                )
                            }
                            MyFloatingActionButton(
                                background = R.drawable.ellipsefull,
                                icon = R.drawable.routing,
                                padding = 5.dp,
                                state = model.stateRouting,
                                onState = {state ->
                                    model.stateRouting = state
                                }
                            )
                        }
                        MyFloatingActionButton(
                            background = R.drawable.ellipsefull,
                            icon = R.drawable.geo,
                            padding = 5.dp
                        )
                    }
                },
                floatingActionButtonPosition = FabPosition.End
            )
        }
    }
}