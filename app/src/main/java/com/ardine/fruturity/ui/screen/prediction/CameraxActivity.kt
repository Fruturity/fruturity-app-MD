package com.ardine.fruturity.ui.screen.prediction

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ardine.fruturity.ui.theme.FruturityTheme

class CameraxActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FruturityTheme {
                CameraScreen()
            }
        }
    }
}

