package com.ardine.fruturity.ui.screen.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ardine.fruturity.R

@Composable
fun HomeScreen (
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = 10.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .height(180.dp)
                .fillMaxWidth()
                .padding(16.dp)
        )
        Text(
            text = stringResource(R.string.hi_it_s_fruturity),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
            )
        )
        Text(
            text = stringResource(R.string.which_delightful_fruit_graces_you_today),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
            )
        )
        Text(
            text = stringResource(R.string.tap_to_start_detecting),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(700),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
            )
        )
        Spacer(modifier = modifier.height(8.dp))
        Box(
            modifier = modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        ) {
//            Image(
//                painter = painterResource(id = R.drawable.ic_scan),
//                contentDescription = "Icon Scanner",
//                contentScale = ContentScale.Crop,
//                modifier = modifier
//                    .fillMaxSize()
//                    .clip(CircleShape)
//            )
            val mCOntext = LocalContext.current
            Button(
                onClick = {
//                    mCOntext.startActivity(Intent(mCOntext, CameraxActivity::class.java))
                },
                shape = CircleShape
            ) {
                Text(text = "start detection")
                Image(
                    painter = painterResource(id = R.drawable.ic_scan),
                    contentDescription = null ,
                    modifier = Modifier
                        .fillMaxWidth(2f)
                        .fillMaxHeight(2f)
                        .size(100.dp)
                )

            }
        }

    }
}