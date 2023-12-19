package com.ardine.fruturity

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ardine.fruturity.ui.navigation.NavigationItem
import com.ardine.fruturity.ui.screen.Home.HomeScreen
import com.ardine.fruturity.ui.screen.bookmark.BookmarkScreen
import com.ardine.fruturity.ui.screen.detail.DetailScreen
import com.ardine.fruturity.ui.screen.history.HistoryScreen
import com.ardine.fruturity.ui.theme.FruturityTheme

@Composable
fun FruturityApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isDetailScreen = currentRoute?.startsWith(Screen.ItemType.HISTORY.route) == true ||
            currentRoute?.startsWith(Screen.ItemType.BOOKMARK.route) == true

    Scaffold(
        topBar = {

        },
        bottomBar = {
//            if (!isDetailScreen) {
                BottomBar(navController)
//            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier
                .padding(innerPadding)
        ) {
            composable(Screen.Home.route){
                HomeScreen()
            }
            composable(Screen.History.route){
                HistoryScreen(
                    navigateToDetail = { fruitId ->
                        navController.navigate(Screen.Detail.createRoute(Screen.ItemType.HISTORY, fruitId))
                    }
                )
            }
            composable(Screen.Bookmark.route){
                BookmarkScreen(
                    navigateToDetail = { fruitId ->
                        navController.navigate(Screen.Detail.createRoute(Screen.ItemType.BOOKMARK, fruitId))
                    }
                )
            }
            composable(
                route = "${Screen.ItemType.HISTORY.route}/{fruitId}",
                arguments = listOf(navArgument("fruitId") { type = NavType.StringType }),
            ) {
                val id = it.arguments?.getString("fruitId") ?: ""
                DetailScreen(
                    fruitId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable(
                route = "${Screen.ItemType.BOOKMARK.route}/{fruitId}",
                arguments = listOf(navArgument("fruitId") { type = NavType.StringType }),
            ) {
                val id = it.arguments?.getString("fruitId") ?: ""
                DetailScreen(
                    fruitId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar (
        modifier = modifier
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = "History",
                icon = Icons.Default.ShoppingCart,
                screen = Screen.History
            ),
            NavigationItem(
                title = "Bookmark",
                icon = Icons.Default.AccountCircle,
                screen = Screen.Bookmark
            )
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FruturityTheme {
        FruturityApp()
    }
}