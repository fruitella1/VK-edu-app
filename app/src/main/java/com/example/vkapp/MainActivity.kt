package com.example.vkapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.core.text.isDigitsOnly
import com.example.vkapp.ui.theme.VkAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val context = LocalContext.current
                    var textState by remember { mutableStateOf("") }
                    var errorState by remember { mutableStateOf(false) }
                    var errorShare by remember { mutableStateOf(false) }
                    var errorCall by remember { mutableStateOf(false) }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        TextField(
                            value = textState,
                            onValueChange = { textState = it },
                            label = { Text("Input your text") }
                        )

                        Button(
                            onClick = {
                                if (!textState.isBlank()) {
                                    errorState = false
                                    val explicitIntent = Intent(
                                        this@MainActivity,
                                        MainActivity2::class.java,
                                    )
                                    explicitIntent.putExtra("USER_TEXT", textState)
                                    startActivity(explicitIntent)
                                } else {
                                    errorState = true
                                }
                            },
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            if (!errorState) {
                                Text("Open second screen")
                            } else {
                                Text("Input can't be null")
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(onClick = {
                            if (!textState.isBlank()) {
                                val textWithoutPlus = textState.replace("+", "")
                                if ((textState.startsWith('+') || textState.startsWith('8')) &&
                                    textWithoutPlus.isDigitsOnly() &&
                                    textState.length >= 11 &&
                                    textState.length <= 12
                                ) {
                                    errorCall = false
                                    val implicitIntent = Intent(Intent.ACTION_DIAL).apply {
                                        data = "tel:$textState".toUri()
                                    }
                                    context.startActivity(implicitIntent)
                                }else{
                                    errorCall = true
                                }
                            } else {
                                errorCall = true
                            }
                        })
                        {
                            if (errorCall) {
                                Text("Invalid number input")
                            } else {
                                Text("Call friend")
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                if (!textState.isBlank()) {
                                    errorShare = false
                                    val implicitIntent = Intent(Intent.ACTION_SEND).apply {
                                        type = "text/plain"
                                        putExtra(Intent.EXTRA_TEXT, textState)
                                    }
                                    val chooser = Intent.createChooser(implicitIntent, "share with")
                                    context.startActivity(chooser)
                                } else {
                                    errorShare = true
                                }
                            },
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            if (!errorShare) {
                                Text("Share")
                            } else {
                                Text("Input can't be null")
                            }
                        }
                    }
                }
            }
        }
    }
}