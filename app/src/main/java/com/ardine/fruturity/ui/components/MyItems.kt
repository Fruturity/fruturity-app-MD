package com.ardine.fruturity.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ardine.fruturity.R
import com.ardine.fruturity.ui.theme.FruturityTheme

@Composable
fun MyItems(
    fruitsId : Long,
    ripeness : String,
    image : Int,
    category : String,
    detetcted : String,
    onClick : () -> Unit,
    bookmarkStatus : Boolean,
    updateBookmarkStatus : () -> Unit,
    modifier : Modifier= Modifier
){
    Card (
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
            .padding(8.dp)
            .shadow(2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
            // .clip(shape = RoundedCornerShape(5.dp))
        ) {

            Image(
                painter = painterResource(image),
                contentDescription = null,
                //  alignment = Alignment.CenterStart,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .padding(start = 5.dp)
            )
//        Spacer(modifier = Modifier.width(6.dp))
            Column(
                modifier = Modifier
                    .padding(vertical = 14.dp, horizontal = 8.dp)
            ) {
                Text(
                    text = "Ripeness\"$ripeness\"",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Category : \"$category\"",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "Detected on : $detetcted ",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.secondaryContainer

                )
            }

            IconButton(onClick = updateBookmarkStatus) {
                Icon(
                    painter = if (bookmarkStatus) {
                        painterResource(R.drawable.ic_bookmarked_white)
                    } else {
                        painterResource(R.drawable.ic_bookmark_white)
                    },
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryItemPreview(){
    FruturityTheme {
        MyItems(
            fruitsId = 0,
            ripeness = "matang" ,
            image = R.drawable.image_test,
            category = "banana" ,
            detetcted = "12/01/2003",
            onClick = {},
            bookmarkStatus = false,
            updateBookmarkStatus = {}
        )
    }
}