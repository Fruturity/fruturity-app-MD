package com.ardine.fruturity.ui.screen.prediction

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ardine.fruturity.di.Injection
import com.ardine.fruturity.ui.ViewModelFactory
import com.ardine.fruturity.ui.theme.FruturityTheme

class CameraxActivity : ComponentActivity() {

    private val cameraViewModel : CameraViewModel by viewModels {
        ViewModelFactory(Injection.provideRepository())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FruturityTheme {
//                CameraContent(viewModel = cameraViewModel)
                CameraContent()
            }
        }
    }
}

