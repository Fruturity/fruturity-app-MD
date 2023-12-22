    package com.ardine.fruturity.ui.screen.prediction

    import android.Manifest
    import android.content.Context
    import android.content.pm.PackageManager
    import android.net.Uri
    import android.util.Log
    import android.widget.Toast
    import androidx.activity.compose.rememberLauncherForActivityResult
    import androidx.activity.result.PickVisualMediaRequest
    import androidx.activity.result.contract.ActivityResultContracts
    import androidx.compose.foundation.Image
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.rememberScrollState
    import androidx.compose.foundation.shape.CircleShape
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.foundation.verticalScroll
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.CameraAlt
    import androidx.compose.material.icons.filled.InsertPhoto
    import androidx.compose.material.icons.filled.Photo
    import androidx.compose.material3.CircularProgressIndicator
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.unit.dp
    import androidx.core.content.ContextCompat
    import androidx.lifecycle.viewmodel.compose.viewModel
    import coil.compose.AsyncImage
    import com.ardine.fruturity.data.ResultState
    import com.ardine.fruturity.di.Injection
    import com.ardine.fruturity.ui.ViewModelFactory
    import com.ardine.fruturity.ui.components.ButtonCamera
    import com.ardine.fruturity.ui.components.ButtonDetection
    import com.ardine.fruturity.ui.screen.utils.getImageUri
    import com.ardine.fruturity.ui.screen.utils.reduceFileImage
    import com.ardine.fruturity.ui.screen.utils.uriToFile


    //    @Composable
//    fun CameraScreen(
//        viewModel: CameraViewModel = viewModel(factory = ViewModelFactory(Injection2.provideRepository())),
//    //    onUploadImage : String
//
//    ){
//
////        val uploadImageState by viewModel.uploadImage.observeAsState()
////
////        // Gunakan uploadImageState sesuai kebutuhan Anda
////        uploadImageState?.let { resultState ->
////            when (resultState) {
////                is ResultState.Loading -> {
////                    // Handle state Loading
////                    Box(
////                        modifier = Modifier
////                            .fillMaxSize()
////                            .padding(16.dp),
////                        contentAlignment = Alignment.Center
////                    ) {
////                        CircularProgressIndicator(
////                            modifier = Modifier.size(40.dp)
////                        )
////                    }
////                }
////                is ResultState.Success -> {
////                    //val response = resultState.data
////                    CameraContent(
////                        uploadImageState = resultState,
////                        onUploadImage ={ imagePart ->
////                            viewModel.uploadImagePredict(imagePart)
////                        }
////                    )
////                }
////                is ResultState.Error -> {
////    //                val error = resultState.exception
////                    Box(
////                        modifier = Modifier
////                            .fillMaxSize()
////                            .padding(16.dp),
////                        contentAlignment = Alignment.Center
////                    ) {
////                        // Tambahkan elemen UI atau tindakan sesuai kebutuhan
////                        // Sebagai contoh, menampilkan pesan kesalahan
////                        Toast.makeText(LocalContext.current, "cannot detection", Toast.LENGTH_SHORT).show()
////                    }
////                }
////            }
////        }
//    }



    @Composable
    fun CameraContent(
        modifier: Modifier = Modifier,
       // uploadImageState : ResultState<UploadImagePredectionResponse>?,
       // onUploadImage : (MultipartBody.Part) -> Unit
        viewModel: CameraViewModel = viewModel(
            factory = ViewModelFactory(Injection.provideRepository())
        ),
        context: Context = LocalContext.current,
    ) {

        val uploadImageState by viewModel.uploadImage.observeAsState()

        // Gunakan uploadImageState sesuai kebutuhan Anda
        uploadImageState?.let { resultState ->
            when (resultState) {
                is ResultState.Loading -> {
                    // Handle state Loading
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
                is ResultState.Success -> {
                    val response = resultState.data
                    Log.d("succesresponse", "Response: $response")

                    Toast.makeText(LocalContext.current, "Upload berhasil: ${response}", Toast.LENGTH_SHORT).show()

                }
                is ResultState.Error -> {
                    //                val error = resultState.exception
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        // Tambahkan elemen UI atau tindakan sesuai kebutuhan
                        // Sebagai contoh, menampilkan pesan kesalahan
                        Toast.makeText(LocalContext.current, "cannot detection", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }



//        val context = LocalContext.current
//        val file = context.createImageFile()
//        val uri = FileProvider.getUriForFile(
//            Objects.requireNonNull(context),
//            context.packageName + ".provider", file
//        )

        var capturedImageUri by remember {
            mutableStateOf<Uri>(Uri.EMPTY)
        }

    //    var imageUri by remember {
    //        mutableStateOf<Uri?>(null)
    //    }



        var currentImageUri: Uri? = null


        val cameraLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()){
                capturedImageUri = currentImageUri!!
            }

        val launcherGallery = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia()
        ) { uri: Uri? ->
            if (uri != null) {
                currentImageUri = uri
                capturedImageUri = currentImageUri!!
            } else {
                Log.d("Photo Picker", "No Media Selected")
            }

        }

        fun startGallery() {
            launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        //galery
       // val bitmap = remember{ mutableStateOf<Bitmap?>(null) }

        //akses galery
    //    val launcher = rememberLauncherForActivityResult(
    //        contract = ActivityResultContracts.GetContent()){ uri: Uri? ->
    //        imageUri = uri
    //    }

//        val permissionLauncher = rememberLauncherForActivityResult(
//            ActivityResultContracts.RequestPermission()
//        ){
//            if (it)
//            {
//                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
//                cameraLauncher.launch(uri)
//            }
//            else
//            {
//                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
//            }
//        }

        fun startCamera() {
            currentImageUri = context.getImageUri(context)
            cameraLauncher.launch(currentImageUri)
        }

        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) {
            if (it) {
                startCamera()
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
//            if (capturedImageUri.path?.isNotEmpty() == true) {
//                Image(
//                    modifier = Modifier
//                        .height(480.dp)
//                        .fillMaxWidth()
//                        .padding(top = 5.dp)
//                        .clip(RoundedCornerShape(17.dp)),
//                    painter = rememberImagePainter(capturedImageUri),
//                    contentDescription = null
//                )
//            } else {
//                Image(
//                    modifier = Modifier
//                        .height(480.dp)
//                        .fillMaxWidth()
//                        .padding(top = 12.dp)
//                        .clip(RoundedCornerShape(17.dp)),
//                    imageVector = Icons.Default.Photo,
//                    contentDescription = null
//                )
//            }
            if (capturedImageUri.path?.isNotEmpty() == true) {
                AsyncImage(
                    model = capturedImageUri,
                    modifier = Modifier
                        .height(480.dp)
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                        .clip(RoundedCornerShape(17.dp)),
                    contentDescription = "LogoApp",
                    contentScale = ContentScale.Fit
                )
            } else if (capturedImageUri.path?.isEmpty() == true) {
                Image(
                    imageVector = Icons.Default.Photo,
//                    painter = painterResource(id = R.drawable.logoapp),
                    modifier = Modifier
                        .height(480.dp)
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .clip(RoundedCornerShape(17.dp)),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
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

                Row (
                    modifier = modifier ,
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    ButtonCamera(
                        icon = Icons.Default.CameraAlt,
                        tintColor = Color.White,
                        onClickButtonCamera = {
                            val permissionCheckResult =
                                ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                startCamera()
                            } else {
                                // Request a permission
                                permissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                        },
                    )

                    //val mCOntext = LocalContext.current

                    ButtonCamera(
                        icon = Icons.Default.InsertPhoto,
                        tintColor = Color.White,
                        onClickButtonCamera = {
                           startGallery()
                        },
                    )
                }

                //val contextActivty = LocalContext.current
                ButtonDetection(
                    onClick = {
//                        if(capturedImageUri.path?.isNotEmpty()==true){
////                            val imageFile = File(capturedImageUri.path!!)
////                            val imagePart = MultipartBody.Part.createFormData(
////                                "image",
////                                imageFile.name,
////                                imageFile.asRequestBody())
//                           onUploadImage(imagePart)
                        val imageFile = context.uriToFile(capturedImageUri, context).reduceFileImage()
                        viewModel.uploadImagePredict(imageFile)
                        Log.d("image_file_fruturtyrufwsfse","$imageFile")
//                        }else{
//                            Toast.makeText(context, "Capture an image first", Toast.LENGTH_SHORT).show()
//                        }
                        //context.startActivity(Intent(context, ResultActivity::class.java))
                        Toast.makeText(context, "Image Uploaded Successfully!", Toast.LENGTH_SHORT).show()
                    },
                    text = "Start Detection!!"
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }

//    fun Context.createImageFile(): File {
//        val timeStamp = SimpleDateFormat("yyyy_MM_dd_HH:mm:ss").format(Date())
//        val imageFileName = "JPEG_" + timeStamp + "_"
//        val image = File.createTempFile(
//            imageFileName,
//            ".jpg",
//            externalCacheDir
//        )
//
//        return image
//    }


    //@Preview(showBackground = true)
    //@Composable
    //fun ImagerCapturePreview(){
    //    FruturityTheme {
    //        CameraContent()
    //    }
    //}