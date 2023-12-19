package com.ardine.fruturity.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ardine.fruturity.ui.theme.FruturityTheme

@Composable
fun ButtonDetection(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
) {


    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(size = (0.dp))
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonDetectionPreview(){
    FruturityTheme {
        ButtonDetection(onClick = { }, text = "lets detection")
    }
}