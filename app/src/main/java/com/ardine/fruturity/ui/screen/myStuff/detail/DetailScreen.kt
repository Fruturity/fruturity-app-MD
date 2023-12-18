package com.ardine.fruturity.ui.screen.myStuff.detail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.ardine.fruturity.R
import com.ardine.fruturity.data.ResultState
import com.ardine.fruturity.data.response.FruitResponse
import com.ardine.fruturity.di.Injection
import com.ardine.fruturity.ui.ViewModelFactory

@Composable
fun DetailScreen(
    fruitId: String,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
//    navigateBack: () -> Unit,
) {
    viewModel.resultState.collectAsState(initial = ResultState.Loading).value.let { resultState ->
        when (resultState) {
            is ResultState.Loading -> {
                viewModel.getFruitById(fruitId)
            }
            is ResultState.Success -> {
                DetailContent(
                    fruits = resultState.data,
//                    onBackClick = navigateBack,
                )
            }
            is ResultState.Error -> {
                Toast.makeText(LocalContext.current,R.string.empty_msg, Toast.LENGTH_SHORT).show()
            }

        }
    }
}

@Composable
fun DetailContent(
    fruits: FruitResponse,
//    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
//                ICoilImage
                Image(
                    painter = rememberImagePainter(fruits.imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = modifier
                        .padding(16.dp)
//                        .clickable { onBackClick() }
                )
            }
            Row(
                modifier = modifier
            ) {
                Column(
                    modifier = modifier
                        .padding(16.dp)
                        .weight(2f)
                ) {
                    Text(
                        text = fruits.category,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 30.sp
                        ),
                    )

                    Spacer(modifier = modifier.height(16.dp))

                    Text(
                        text = fruits.notes,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.sp
                        ),
                        textAlign = TextAlign.Justify,
                    )
                }
                Column(
                    modifier = modifier.padding(16.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = stringResource(R.string.ripeness, fruits.ripeness),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold
                        ),
                    )
//                    Text(
//                        text = date,
//                        style = MaterialTheme.typography.bodySmall,
//                    )
                }
            }
        }
    }
}


//@Preview(showBackground = true, device = Devices.PIXEL_4)
//@Composable
//fun DetailContentPreview() {
//    FruturityTheme {
//        DetailContent(
//            R.drawable.apple,
//            "Apple",
//            "An apple keeps the doctor away",
//            12000,
//            1,
//            onBackClick = {},
//            onAddToCart = {},
//        )
//    }
//}