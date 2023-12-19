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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyItems(
    fruitsId: String,
    ripeness: String,
    imageUrl: String,
    category: String,
    date: String,
    onItemClick: (String) -> Unit,
//    bookmarkStatus: Boolean,
//    updateBookmarkStatus: (id :Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
            .padding(4.dp),
        onClick = { onItemClick(fruitsId) }
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(
                    data = imageUrl,
                    builder = {
                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "Ripeness: $ripeness",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = "Category : $category",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "Detected on : $date ",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = modifier.padding(vertical = 2.dp)
                )
            }

//            IconButton(
////                onClick = { updateBookmarkStatus(fruitsId) }
//            ) {
//                Icon(
//                    painter = if (bookmarkStatus) {
//                        painterResource(R.drawable.ic_bookmarked_white)
//                    } else {
//                        painterResource(R.drawable.ic_bookmark_white)
//                    },
//                    contentDescription = "Bookmark Icon",
//                )
            }
        }
    }

//@Preview(showBackground = true)
//@Composable
//fun HistoryItemPreview(){
//    FruturityTheme {
//        MyItems(
//            fruitsId = 0,
//            ripeness = "matang" ,
//            image = R.drawable.image_test,
//            category = "banana" ,
//            date = "12/01/2003",
////            onClick = {},
//            bookmarkStatus = false,
//            updateBookmarkStatus = {}
//        )
//    }
//}