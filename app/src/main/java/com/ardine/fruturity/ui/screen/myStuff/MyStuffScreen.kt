package com.ardine.fruturity.ui.screen.myStuff

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ardine.fruturity.ui.screen.myStuff.bookmark.BookmarkScreen
import com.ardine.fruturity.ui.screen.myStuff.history.HistoryScreen
import com.ardine.fruturity.ui.theme.FruturityTheme

@Composable
fun MyStuffScreen(
    modifier: Modifier = Modifier,
) {
    val tabs = listOf("History","Bookmark")
    var tabIndex by remember { mutableStateOf(0) }

    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = {
//                    Text(
//                        text = "My Stuff",
//                        textAlign = TextAlign.Center,
//                        modifier = modifier
//                            .padding(10.dp)
//                    )
//                },
//                navigationIcon = {
//                    IconButton(onClick = { /* Handle back button click */ }) {
//                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
//                    }
//                },
//                actions = {
//                    IconButton(onClick = { /* Handle edit button click */ }) {
//                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
//                    }
//                },
//                modifier = modifier
//                    .fillMaxWidth()
//                    .height(56.dp)
//            )
//        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
            ) {
            TabRow(
                selectedTabIndex = tabIndex,
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(text = { Text(title) },
                            selected = tabIndex == index,
                            onClick = { tabIndex = index }
                        )
                    }
                }
                when (tabIndex) {
                    0 -> HistoryScreen(
                        navigateToDetail = { fruitId ->
//                                DetailScreen(fruitId = fruitId)
                        },
                        modifier = modifier,
                    )
                    1 -> BookmarkScreen()
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MyStuffScreenPreview() {
    FruturityTheme {
        MyStuffScreen()
    }
}