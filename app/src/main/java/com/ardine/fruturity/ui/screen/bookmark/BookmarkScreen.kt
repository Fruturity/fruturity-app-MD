package com.ardine.fruturity.ui.screen.bookmark

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ardine.fruturity.R
import com.ardine.fruturity.data.ResultState
import com.ardine.fruturity.data.response.FruitResponse
import com.ardine.fruturity.di.Injection
import com.ardine.fruturity.ui.ViewModelFactory
import com.ardine.fruturity.ui.components.MyItems

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    viewModel: BookmarkViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (String) -> Unit,
) {
    viewModel.resultState.collectAsState(initial = ResultState.Loading).value.let { resultState ->
        when (resultState) {
            is ResultState.Loading -> {
                viewModel.getAllBookmarkFruits()
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
                BookmarkContent(
                    fruits = resultState.data,
                    updateBookmarkStatus = { fruitId, newStatus ->
                        viewModel.updateBookmarkStatus(fruitId, newStatus)
                    },
                    navigateToDetail = navigateToDetail,
                    modifier = modifier,
                )
            }
            is ResultState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.empty_img),
                        contentDescription = "empty msg")
                    Text(
                        text = stringResource(R.string.empty_msg)
                    )
                }
            }
        }
    }
}

@Composable
fun BookmarkContent(
    fruits: List<FruitResponse>,
    navigateToDetail: (String) -> Unit,
    updateBookmarkStatus :(String,Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        if (fruits.isEmpty()) {
            Text(
                modifier = modifier.padding(8.dp),
                text = stringResource(R.string.empty_msg)
            )
        } else {
            LazyColumn(
                modifier = modifier,
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(fruits) { item ->
                    MyItems(
                        fruitsId = item.id,
                        ripeness = item.ripeness,
                        imageUrl = item.imageUrl,
                        category = item.category,
                        date = item.date,
                        onItemClick = {
                            navigateToDetail(item.id)
                        },
                        bookmarkStatus = item.isBookmark,
                        updateBookmarkStatus = { id, newStatus ->
                            updateBookmarkStatus(id, newStatus)
                        },
                    )
                }
            }
        }
    }
}
