package com.ardine.fruturity

import Screen
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
import com.ardine.fruturity.ui.screen.bookmark.BookmarkScreen
import com.ardine.fruturity.ui.screen.detail.DetailScreen
import com.ardine.fruturity.ui.screen.history.HistoryScreen
import com.ardine.fruturity.ui.screen.Home.HomeScreen
import com.ardine.fruturity.ui.screen.splash.SplashScreen
import com.ardine.fruturity.ui.theme.FruturityTheme

@Composable
fun FruturityApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val isSplashScreen = currentRoute?.startsWith("splash") == true

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar(currentRoute, isSplashScreen)) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = modifier
                .padding(innerPadding)
        ) {
            composable(Screen.Splash.route){
                SplashScreen(navController)
            }
            composable(Screen.Home.route){
                HomeScreen()
            }
            composable(Screen.History.route) {
                HistoryScreen(
                    navigateToDetail = { fruitId ->
                        navController.navigate(Screen.createRoute(Screen.ItemType.HISTORY, fruitId))
                    }
                )
            }
            composable(Screen.Bookmark.route) {
                BookmarkScreen(
                    navigateToDetail = { fruitId ->
                        navController.navigate(Screen.createRoute(Screen.ItemType.BOOKMARK, fruitId))
                    }
                )
            }
            composable(
                route = "${Screen.ItemType.HISTORY.route}/detail/{fruitId}",
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
                route = "${Screen.ItemType.BOOKMARK.route}/detail/{fruitId}",
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
                title = stringResource(R.string.home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.history),
                icon = Icons.Default.Inventory,
                screen = Screen.History
            ),
            NavigationItem(
                title = stringResource(R.string.bookmark),
                icon = Icons.Default.Bookmark,
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

private fun shouldShowBottomBar(currentRoute: String?, isSplashScreen: Boolean): Boolean {
    return currentRoute != Screen.Detail.route && !isSplashScreen
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FruturityTheme {
        FruturityApp()
    }
}