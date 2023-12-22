package com.ardine.fruturity.ui.screen.prediction

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ardine.fruturity.ui.theme.FruturityTheme

class CameraxActivity : ComponentActivity() {

//    private val cameraViewModel : CameraViewModel by viewModels {
//        ViewModelFactory(Injection2.provideRepository())
//    }
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

