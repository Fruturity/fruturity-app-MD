package com.ardine.fruturity.ui.screen.camera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Photo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberImagePainter
import com.ardine.fruturity.ui.components.ButtonCamera
import com.ardine.fruturity.ui.components.ButtonDetection
import com.ardine.fruturity.ui.theme.FruturityTheme
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@Composable
fun ImageCapture(
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        context.packageName + ".provider", file
    )

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

//    var imageUri by remember {
//        mutableStateOf<Uri?>(null)
//    }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()){
            capturedImageUri = uri
        }

    //galery
    // val bitmap = remember{ mutableStateOf<Bitmap?>(null) }

    //akses galery
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()){ uri: Uri? ->
//        imageUri = uri
//    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){
        if (it)
        {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
        }
        else
        {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // take picture with camera
        if (capturedImageUri.path?.isNotEmpty() == true) {
            Image(
                modifier = Modifier
                    .height(480.dp)
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clip(RoundedCornerShape(17.dp)),
                painter = rememberImagePainter(capturedImageUri),
                contentDescription = null
            )
        } else {
            Image(
                modifier = Modifier
                    .height(480.dp)
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(17.dp)),
                imageVector = Icons.Default.Photo,
                contentDescription = null
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        //picture from galery
//        imageUri?.let {
//            if (Build.VERSION.SDK_INT < 20){
//                bitmap.value = MediaStore.Images
//                    .Media.getBitmap(context.contentResolver, it)
//            }else{
//                val source = ImageDecoder.createSource(context.contentResolver, it)
//                bitmap.value = ImageDecoder.decodeBitmap(source)
//            }
//        }
//
//        bitmap.value?.let { bit ->
//        }
        //button
        Column(
            modifier = modifier
                .padding(top = 200.dp)
                .clip(CircleShape),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

//                ButtonCamera(
//                    icon = Icons.Default.Photo,
//                    onClickButtonCamera = {},
//                )

            ButtonCamera(
                icon = Icons.Default.CameraAlt,
                tintColor = Color.White,
                onClickButtonCamera = {
                    val permissionCheckResult =
                        ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED)
                    {
                        cameraLauncher.launch(uri)
                    }
                    else
                    {
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                },
            )

            ButtonDetection(
                onClick = {

                },
                text = "Start Detection!!"
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyy_MM_dd_HH:mm:ss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )

    return image
}


@Preview(showBackground = true)
@Composable
fun ImagerCapturePreview(){
    FruturityTheme {
        ImageCapture()
    }
}