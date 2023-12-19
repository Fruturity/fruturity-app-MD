package com.ardine.fruturity.ui.screen.result

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ardine.fruturity.ui.theme.FruturityTheme

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FruturityTheme {
                // A surface container using the 'background' color from the theme
                CenteredBox()
            }
        }
    }
}

