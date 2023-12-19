package com.ardine.fruturity.ui.screen.myStuff.history

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ardine.fruturity.R
import com.ardine.fruturity.data.ResultState
import com.ardine.fruturity.di.Injection
import com.ardine.fruturity.data.model.FruitHistory
import com.ardine.fruturity.ui.ViewModelFactory
import com.ardine.fruturity.ui.components.MyItems
import com.ardine.fruturity.ui.components.SearchBar

@Composable
fun HistoryScreen(
    modifier : Modifier = Modifier,
    viewModel: HistoryViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
){
    val searchState by viewModel.searchState

    viewModel.resultState.collectAsState(initial = ResultState.Loading).value.let { resultState ->
        when(resultState){
            is ResultState.Loading -> {
                viewModel.getAllFruits()
            }
            is ResultState.Success -> {
                HistoryContent(
                    fruits = resultState.data,
                    navigateToDetail = navigateToDetail,
                    query = searchState.query,
                    onQueryChange = viewModel::onQueryChange,
                    updateBookmarkStatus = {
                        viewModel.updateFruitMark(it)
                    },
                    modifier = modifier,
                )
            }
            is ResultState.Error -> {
                Toast.makeText(LocalContext.current, R.string.empty_msg, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }
}

@Composable
fun HistoryContent (
//    groupedFruits: Map<String, List<FruitHistory>>,
    fruits: List<FruitHistory>,
    query: String,
    navigateToDetail: (Long) -> Unit,
    updateBookmarkStatus :(id: Long) -> Unit,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column {
        Box(
            modifier = modifier
                .padding(horizontal = 8.dp)
        ) {
            SearchBar(
                query = query,
                onQueryChange = onQueryChange,
                modifier = modifier
                    .clip(RoundedCornerShape(8.dp))
            )
        }
            if (fruits.isEmpty()) {
                Text(
                    modifier = modifier,
                    text = stringResource(R.string.empty_msg)
                )
            } else {
                LazyColumn(
                    modifier = modifier,
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
    //                fruits.forEach { items ->
    //                    stick/yHeader {
    //                        Text(text = items.fruits.category, modifier = Modifier.fillMaxWidth())
    //                    }
                        items(fruits) { items ->
                            MyItems(
                                fruitsId = items.fruits.id ,
                                ripeness = items.fruits.ripeness,
                                imageUrl = items.fruits.imageUrl,
                                category = items.fruits.category,
                                date = items.fruits.date,
                                bookmarkStatus = items.fruits.isBookmark,
                                updateBookmarkStatus = updateBookmarkStatus,
                            )
                        }
    //                }
                }
            }

    }
}