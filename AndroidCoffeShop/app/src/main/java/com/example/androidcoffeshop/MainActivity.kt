package com.example.androidcoffeshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.androidcoffeshop.ui.theme.AndroidCoffeShopTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidCoffeShopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Screen2") {

        composable("Screen2") {
            Screen2 { id ->
                navController.navigate("Screen3/$id") // Correctly pass the id
            }
        }

        composable(
            route = "Screen3/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType }) // Enforce integer argument
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            Screen3(
                id = id,
                navToScreen2 = { navController.navigate("Screen2") }
            )
        }
    }
}
