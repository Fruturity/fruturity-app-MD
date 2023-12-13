package com.ardine.fruturity.ui.screen.myStuff.history

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.ardine.fruturity.model.FruitHistory
import com.ardine.fruturity.ui.ViewModelFactory
import com.ardine.fruturity.ui.components.SearchBar

@Composable
fun HistoryScreen(
    modifier : Modifier = Modifier,
    viewModel: HistoryViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
){
    val historyState by viewModel.historyState

    viewModel.resultState.collectAsState(initial = ResultState.Loading).value.let { resultState ->
        when(resultState){
            is ResultState.Loading -> {
                viewModel.getAllFruits()
            }
            is ResultState.Success -> {
                HistoryContent(
                    fruits = resultState.data,
                    navigateToDetail = navigateToDetail,
                    query = historyState.query,
                    onQueryChange = viewModel::onQueryChange,
                    modifier = modifier,
                )
            }
            is ResultState.Error -> {
                Toast.makeText(LocalContext.current, R.string.empty_msg, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryContent (
    fruits: List<FruitHistory>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    query: String,
    onQueryChange: (String) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Box(modifier = modifier) {
            SearchBar(
                query = query,
                onQueryChange = onQueryChange,
                modifier = modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
        if (fruits.isEmpty()) {
            Text(
                modifier = modifier
                    .align(Alignment.CenterHorizontally),
                text = stringResource(R.string.empty_msg)
            )
        }
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            stickyHeader {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = "sa"
                )
            }

//            items(fruits, key = { it.fruits.id }) { data ->
//                MyItems(data = data, navigateToDetail = navigateToDetail)
//            }
        }
    }
}