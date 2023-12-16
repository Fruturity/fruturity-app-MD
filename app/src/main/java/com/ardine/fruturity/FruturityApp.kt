package com.ardine.fruturity

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ardine.fruturity.data.MenuItem
import com.ardine.fruturity.handler.BackPressHandler
import com.ardine.fruturity.ui.screen.camera.CameraxActivity
import com.ardine.fruturity.ui.theme.FruturityTheme

@Composable
fun FruturityApp(
    modifier : Modifier = Modifier
) {
    val appState = rememberDrawerState()
    val (isFullAppBar, setIsFullAppBar) = remember { mutableStateOf(false) }

    BackPressHandler(enabled = appState.drawerState.isOpen) {
        appState.onBackPress()
        setIsFullAppBar(false)
    }

    val items = listOf(
        MenuItem(
            title = stringResource(R.string.language),
            icon = Icons.Default.Home,
            text = "English"
        ),
        MenuItem(
            title = stringResource(R.string.theme),
            icon = Icons.Default.Favorite
        ),
        MenuItem(
            title = stringResource(R.string.about_us),
            icon = Icons.Default.Info
        ),
        MenuItem(
            title = stringResource(R.string.help_center),
            icon = Icons.Default.AccountCircle
        ),
    )

    val selectedItem = remember { mutableStateOf(items[0]) }

    Scaffold(
        topBar = {
            MyTopBar(
                onMenuClick = {
                    appState.onMenuClick()
                    setIsFullAppBar(!isFullAppBar)
                },
                isFull = isFullAppBar
            )
        },

//        snackbarHost = { SnackbarHost(appState.snackbarHostState) },
//        floatingActionButton = {
//            Box(
//                modifier = Modifier
//                    .padding(start = 16.dp, top = 16.dp)
//                    .size(56.dp)
//            ) {
//                IconButton(
//                    onClick = {
//                        appState.onMenuClick()
//                    },
//                    modifier = Modifier.fillMaxSize()
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Menu,
//                        contentDescription = stringResource(R.string.menu)
//                    )
//                }
//            }
//        },
    ) { paddingValues ->
        ModalNavigationDrawer(
            modifier = Modifier.padding(paddingValues),
            drawerState = appState.drawerState,
            gesturesEnabled = true,
            drawerContent = {
                ModalDrawerSheet {
                    Column (
                        modifier = Modifier
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.primary)
                    ){
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "Logo",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                            )
                        }

                        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                        Spacer(modifier = modifier.height(8.dp))

                        items.forEach { item ->
                            NavigationDrawerItem(
                                icon = { Icon(item.icon, contentDescription = null) },
                                label = { Text(item.title) },
                                selected = item == selectedItem.value,
                                onClick = {
                                    appState.onItemSelected(item)
                                    selectedItem.value = item
                                },
                                modifier = modifier
                                    .padding(horizontal = 12.dp, vertical = 8.dp)
                            )
                        }
                    }
                }
            },
            content = {
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
//                        Image(
//                            painter = painterResource(id = R.drawable.ic_scan),
//                            contentDescription = "Icon Scanner",
//                            contentScale = ContentScale.Crop,
//                            modifier = modifier
//                                .fillMaxSize()
//                                .clip(CircleShape)
//                        )

                        ///

                        val mCOntext = LocalContext.current
                        Button(
                            onClick = {
                                mCOntext.startActivity(Intent(mCOntext, CameraxActivity::class.java))
                            },
                            shape = CircleShape
                        ) {
//                            Text(text = "start detection")
                            Image(
                                painter = painterResource(id = R.drawable.ic_scan),
                                contentDescription = null ,
                                modifier = Modifier
                                    .fillMaxWidth(2f)
                                    .fillMaxHeight(2f)
                                    .size(100.dp)
                            )

                        }
                        ///

                    }

                }
            }
        )
        LaunchedEffect(appState.drawerState.isOpen) {
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(onMenuClick: () -> Unit, isFull: Boolean, modifier: Modifier = Modifier) {
    val appBarHeight = if (isFull) {
        56.dp
    } else {
        56.dp // Set your desired height when not full
    }

    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = { onMenuClick() }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(R.string.menu)
                )
            }
        },
        modifier = modifier
            .height(appBarHeight)
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FruturityTheme {
        FruturityApp()
    }
}