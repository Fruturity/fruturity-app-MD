package com.ardine.fruturity.ui.screen.myStuff.detail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.ardine.fruturity.di.Injection
import com.ardine.fruturity.ui.ViewModelFactory

@Composable
fun DetailScreen(
    fruitId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
//    navigateToCart: () -> Unit
) {
    viewModel.resultState.collectAsState(initial = ResultState.Loading).value.let { resultState ->
        when (resultState) {
            is ResultState.Loading -> {
                viewModel.getFruitById(fruitId)
            }
            is ResultState.Success -> {
                val data = resultState.data
                DetailContent(
                    data.fruits.imageUrl,
                    data.fruits.category,
                    data.fruits.ripeness,
                    data.fruits.date,
                    data.fruits.description,
                    data.count,
                    onBackClick = navigateBack,
//                    onAddToCart = { count ->
//                        viewModel.addToCart(data.fruits, count)
//                        navigateToCart()
//                    }
                )
            }
            is ResultState.Error -> {
                Toast.makeText(LocalContext.current,R.string.empty_msg, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }
}

@Composable
fun DetailContent(
    imageUrl: String,
    category: String,
    ripeness: String,
    date: String,
    description: String,
    count: Int,
    onBackClick: () -> Unit,
//    onAddToCart: (count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    var totalPrice by rememberSaveable { mutableIntStateOf(0) }
    var orderCount by rememberSaveable { mutableIntStateOf(count) }

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
                    painter = rememberImagePainter(imageUrl),
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
                        .clickable { onBackClick() }
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
                        text = category, // Use the category instead of title
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 30.sp
                        ),
                    )

                    Spacer(modifier = modifier.height(16.dp))

                    Text(
                        text = description, // Use the description
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
                        text = stringResource(R.string.ripeness, ripeness),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.ExtraBold
                        ),
                    )
                    Text(
                        text = date, // Use the date instead of kg
                        style = MaterialTheme.typography.bodySmall,
                    )
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