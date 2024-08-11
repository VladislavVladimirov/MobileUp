package ru.test.mobileup.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.test.mobileup.presentation.screens.CoinScreen
import ru.test.mobileup.presentation.screens.MainScreen
import ru.test.mobileup.presentation.ui.theme.MobileUpTheme
import ru.test.mobileup.presentation.viewmodel.ViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileUpTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "MainScreen") {
                        composable("MainScreen") {
                            val viewModel = hiltViewModel<ViewModel>()
                            MainScreen(viewModel, navController)
                        }
                        composable("MainScreen/{id}") { backStackEntry ->
                            val viewModel = hiltViewModel<ViewModel>()
                            val id = backStackEntry.arguments?.getString("id")
                            CoinScreen(id, viewModel, navController)
                        }
                    }
                }
            }
        }
    }
}



