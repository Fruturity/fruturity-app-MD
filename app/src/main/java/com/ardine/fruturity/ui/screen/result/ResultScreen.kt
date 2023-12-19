package com.ardine.fruturity.ui.screen.result

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ardine.fruturity.MainActivity
import com.ardine.fruturity.ui.theme.FruturityTheme

//@Composable
//fun ResultScreen(){
//
//}

@Composable
fun CenteredBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Kotak dengan padding dan warna latar belakang
        Box(
            modifier = Modifier
                .size(300.dp)
                .background(Color.White)
                .padding(16.dp)
                .shadow(8.dp),
            contentAlignment = Alignment.Center
        ) {
            // Isi kotak, misalnya teks
            Text(
                text = "Result is :",
                color = Color.Black,
                modifier = Modifier
                    .padding(bottom = 175.dp)
            )

            Text(
                text = "MATANG",
                color = Color.Black,
                fontSize = 30.sp,
                fontStyle = FontStyle.Normal
            )
        }
    }

    BackButton(
        modifier = Modifier
            .padding(16.dp)
    )
}

@Composable
fun BackButton(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    IconButton(
        onClick = {
            context.startActivity(Intent(context, MainActivity::class.java))

            // Tambahkan logika untuk menanggapi klik tombol kembali di sini
            // Misalnya, kembali ke layar sebelumnya
           // context.onBackPressedDispatcher.onBackPressed()
        },
        modifier = modifier
    ) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
    }
}

@Preview(showBackground = true)
@Composable
fun CenteredBoxPreview() {
    FruturityTheme {
        CenteredBox()
    }
}