package com.ardine.fruturity.ui.screen.camera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ardine.fruturity.ui.theme.FruturityTheme

class CameraxActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FruturityTheme {
                ImageCapture()
            }
        }
    }
}

