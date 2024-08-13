package ru.test.mobileup.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import ru.test.mobileup.domain.model.CoinModel
import ru.test.mobileup.domain.model.StateModel
import ru.test.mobileup.presentation.screens.composables.mainScreen.MainScreenErrorGroup
import ru.test.mobileup.presentation.screens.composables.mainScreen.PullToRefreshLazyColumn
import ru.test.mobileup.presentation.screens.composables.mainScreen.TopBar
import ru.test.mobileup.presentation.ui.theme.OrangeIndicator
import ru.test.mobileup.presentation.ui.theme.lightRed
import ru.test.mobileup.presentation.viewmodel.CoinsViewModel

val space = 16.dp
val doubleSpace = 32.dp
val halfSpace = 8.dp

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun MainScreen(viewModel: CoinsViewModel = viewModel(), navController: NavHostController) {
    val isUsdSelected = remember { mutableStateOf(true) }
    val coinsModel: CoinModel by viewModel.coins.observeAsState(CoinModel())
    val stateModel: StateModel by viewModel.state.observeAsState(StateModel())
    val coins = coinsModel.coins
    val isLoading = stateModel.loading
    val isError = stateModel.error
    val listState = rememberLazyListState()
    var isRefreshing = stateModel.refreshing
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(snackbarHost = {
        SnackbarHost(snackBarHostState) {
            Snackbar(containerColor = lightRed, snackbarData = it, contentColor = Color.White)
        }
    }, topBar = {
        TopBar(isUsdSelected)
    }) { innerPadding ->
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            if (isLoading) {
                CircularProgressIndicator(color = OrangeIndicator)
            } else {
                if (isError) {
                    if (coins.isEmpty()) {
                        MainScreenErrorGroup(isUsdSelected)
                    } else {
                        LaunchedEffect(snackBarHostState) { snackBarHostState.showSnackbar("Произошла ошибка при загрузке") }

                    }
                }
                if (coins.isNotEmpty()) {
                    PullToRefreshLazyColumn(
                        list = coins,
                        isRefreshing,
                        onRefresh = {
                            scope.launch {
                                isRefreshing = true
                                if (isUsdSelected.value) {
                                    viewModel.getCoinsVsUsd()
                                } else {
                                    viewModel.getCoinsVsRub()
                                }
                                isRefreshing = false
                            }
                        },
                        lazyListState = listState,
                        contentPaddingValues = innerPadding,
                        navController = navController,
                        isUsdSelected = isUsdSelected
                    )
                }
            }
        }
    }
}
