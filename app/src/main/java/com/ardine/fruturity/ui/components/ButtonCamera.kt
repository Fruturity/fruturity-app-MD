package com.ardine.fruturity.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ardine.fruturity.ui.theme.FruturityTheme

@Composable
fun ButtonCamera(
    icon : ImageVector,
    modifier: Modifier = Modifier,
    onClickButtonCamera : () -> Unit,
    tintColor: Color = Color.Unspecified
){

    Button(
        onClick = onClickButtonCamera,
        modifier = Modifier.size(height = 70.dp, width = 70.dp),
        shape = CircleShape,
        content = {
            Image(imageVector = icon,
                contentDescription = null,
                modifier = modifier
                    .fillMaxSize(4f),
                colorFilter = ColorFilter.tint(tintColor)            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ButtonCameraPreview(){
    FruturityTheme {
        ButtonCamera(
            icon = Icons.Default.CameraAlt,
            tintColor = Color.White,
            onClickButtonCamera = {}
        )
    }
}