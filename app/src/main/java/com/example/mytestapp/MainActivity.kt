package com.example.mytestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mytestapp.presentation.home.HomeScreen
import com.example.mytestapp.presentation.investments.AddOrSellStockScreen
import com.example.mytestapp.presentation.investments.ShowInvestmentsViewModel
import com.example.mytestapp.presentation.main.MainScreen
import com.example.mytestapp.ui.theme.MyAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: ShowInvestmentsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") { HomeScreen(navController, viewModel) }
                    composable("investments") { MainScreen(navController, viewModel) }
                    composable("buy_or_sell?uid={uid}&name={name}&price={price}&quantity={quantity}") { navBackStackEntry ->
                        val uid = navBackStackEntry.arguments?.getString("uid")?.toLongOrNull() ?: 0
                        val name = navBackStackEntry.arguments?.getString("name")
                        val price = navBackStackEntry.arguments?.getString("price")
                        val quantity = navBackStackEntry.arguments?.getString("quantity")
                        AddOrSellStockScreen(uid, name, price, quantity, navController, viewModel)
                    }

                }
            }
        }
    }
}
