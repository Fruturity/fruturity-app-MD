package com.ardine.fruturity.ui.screen.camera

//import com.jetpackcompose.playground.TensorFLowHelper.imageSize
//import com.jetpackcompose.playground.common.CreateNotification
//import com.jetpackcompose.playground.ui.theme.JetPackComposePlaygroundTheme
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ardine.fruturity.ui.screen.camera.TensorFLowHelper.imageSize
import com.ardine.fruturity.ui.theme.FruturityTheme

class ImageClasificationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FruturityTheme {
                // A surface container using the 'background' color from the theme

                ImagePicker()
                val context = LocalContext.current

            }
        }
    }


    //write here

    @Composable
    fun ImagePicker() {
        var photoUri by remember {
            mutableStateOf<Uri?>(null)
        }

        val context = LocalContext.current
        var bitmap by remember {
            mutableStateOf<Bitmap?>(null)
        }
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri->
                photoUri = uri
            }
        )


            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                photoUri?.let {
                    if (Build.VERSION.SDK_INT < 28)
                        bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                    else {
                        val source = ImageDecoder.createSource(context.contentResolver, it)
                        bitmap = ImageDecoder.decodeBitmap(
                            source,
                            ImageDecoder.OnHeaderDecodedListener { decoder, info, source ->
                                decoder.allocator = ImageDecoder.ALLOCATOR_SOFTWARE
                                decoder.isMutableRequired = true
                            })
                    }
                }

                bitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "Image from the gallery",
                        Modifier.size(400.dp)
                    )
                    Spacer(modifier = Modifier.padding(20.dp))

                    val scaledBitmap = Bitmap.createScaledBitmap(it, imageSize, imageSize, false);
                    TensorFLowHelper.classifyImage(scaledBitmap) { scan->
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(text = "Image is classified as:")
                            Text(text = scan, color = Color.White, fontSize = 24.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(20.dp))

                Button(onClick = {
                    launcher.launch("image/*")
                }, modifier = Modifier.fillMaxWidth()) {
                   Text(text = "Pick an image")
                }
            }

    }
}

@Preview(showBackground = true)
@Composable
fun imagePickerPreview(){
    FruturityTheme {
    }
}

