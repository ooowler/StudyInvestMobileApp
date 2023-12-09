package com.example.mytestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mytestapp.presentation.home.HomeScreen
import com.example.mytestapp.presentation.investments.AddOrSellStockScreen
import com.example.mytestapp.presentation.main.StockBlock
import com.example.mytestapp.ui.theme.MyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") { HomeScreen(navController) }
                    composable("investments") { StockBlock(navController) }
                    composable("buy_or_sell") { AddOrSellStockScreen(navController = navController) }

                    composable("buy_or_sell?name={name}&price={price}&quantity={quantity}") { navBackStackEntry ->
                        val name = navBackStackEntry.arguments?.getString("name") ?: ""
                        val price = navBackStackEntry.arguments?.getString("price")?.toDoubleOrNull() ?: 0.0
                        val quantity = navBackStackEntry.arguments?.getString("quantity")?.toIntOrNull() ?: 0
                        AddOrSellStockScreen(name, price, quantity, navController)
                    }

                }
            }
        }
    }
}
