//package com.ardine.fruturity.ui.navigation

//import androidx.compose.runtime.Composable
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.ardine.fruturity.ui.screen.myStuff.detail.DetailScreen
//import com.ardine.fruturity.ui.screen.myStuff.history.HistoryScreen

//@Composable
//fun AppNavigation() {
//    val navController = rememberNavController()
//
//    NavHost(
//        navController = navController,
//        startDestination = "history"
//    ) {
//        composable("history") {
//            HistoryScreen(
//                navigateToDetail = { fruitId ->
//                    navController.navigate("detail/$fruitId")
//                }
//            )
//        }
//        composable("detail/{fruitId}") { backStackEntry ->
//            val fruitId = backStackEntry.arguments?.getString("fruitId")
//
//            DetailScreen(
//                fruitId = fruitId,
//                navigateBack = { navController.popBackStack() }
//            )
//        }
//    }
//}
