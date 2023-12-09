package com.example.mytestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mytestapp.presentation.investments.ShowInvestmentsScreen
import com.example.mytestapp.presentation.investments.ShowInvestmentsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ShowInvestmentsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { ShowInvestmentsScreen(navController, viewModel) }
            }
        }
    }
}


//@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MyAppTheme {
//                val navController = rememberNavController()
//                NavHost(navController = navController, startDestination = "home") {
//                    composable("home") { HomeScreen(navController) }
////                    composable("investments") { MainScreen(navController) }
//                    composable("buy_or_sell") { AddOrSellStockScreen(navController = navController) }
//
//                    composable("buy_or_sell?name={name}&price={price}&quantity={quantity}") { navBackStackEntry ->
//                        val name = navBackStackEntry.arguments?.getString("name")
//                        val price = navBackStackEntry.arguments?.getString("price")
//                        val quantity = navBackStackEntry.arguments?.getString("quantity")
//                        AddOrSellStockScreen(name, price, quantity, navController)
//                    }
//
//                }
//            }
//        }
//    }
//}
