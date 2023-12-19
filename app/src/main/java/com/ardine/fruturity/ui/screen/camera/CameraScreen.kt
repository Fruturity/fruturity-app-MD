package com.ardine.fruturity.ui.screen.camera

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ardine.fruturity.ui.theme.FruturityTheme

@Composable
fun CameraScreen(
    modifier: Modifier = Modifier
){

    //image picker contract

    var hasImage by remember { mutableStateOf(false)}

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {uri ->

            hasImage = uri != null
            imageUri = uri
        }
    )
    Box(
        modifier = modifier
    ){

        if (hasImage && imageUri != null){
            AsyncImage(
                model = imageUri ,
                contentDescription = "select image",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = {
                    imagePicker.launch("image/*")
                },
            ) {
                Text(
                    text = "Select Image"
                )
            }
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = { /* TODO */ },
            ) {
                Text(
                    text = "Take photo"
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.NEXUS_6P)
@Composable
fun ButtonPreview(){
    FruturityTheme {
        CameraScreen()
    }
}

