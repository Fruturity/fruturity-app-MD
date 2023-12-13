package com.ardine.fruturity.ui.screen.myStuff

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ardine.fruturity.ui.theme.FruturityTheme
import com.google.android.material.tabs.TabItem
import kotlinx.coroutines.launch

//enum class TabItem { History, Bookmark }

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MyStuffScreen(
    modifier: Modifier = Modifier,
) {
    var searchText by remember { mutableStateOf("") }
//    var selectedTab by remember { mutableStateOf(TabItem.History) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My Stuff",
                        textAlign = TextAlign.Center,
                        modifier = modifier
                            .padding(10.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back button click */ }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle edit button click */ }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                    }
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )
        }
    ) { paddingValues ->
        Column(modifier = modifier.padding(paddingValues)) {
            val pagerState = rememberPagerState(
                pageCount = { 2 }
            )
            val couroutineScope = rememberCoroutineScope()

            TabRow(
                selectedTabIndex = pagerState.currentPage,
                indicator = {
                    TabRowDefaults.Indicator(
                        modifier = modifier.tabIndicatorOffset(tabPostions[pagerState.currentPage]),
                        height = 2.dp,
                    )
                }
            ) {
                Tab(
                    selected = pagerState.currentPage == 0,
                    text = {
                        Text("History")
                    },
                    onClick = {
                        couroutineScope.launch { pagerState.animateScrollToPage(page = 0) }
                    },
                )
                Tab(
                    selected = pagerState.currentPage == 1,
                    text = {
                        Text("Bookmark")
                    },
                    onClick = {
                        couroutineScope.launch { pagerState.animateScrollToPage(page = 1) }

                    },
                )
            }
           HorizontalPager (
                state = pagerState,
                userScrollEnabled = false
            ) { page ->
                Column(
                    modifier = modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Text (text="hello $page")
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyStuffScreenPreview() {
    FruturityTheme {
        MyStuffScreen()
    }
}
