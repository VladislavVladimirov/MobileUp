package ru.test.mobileup.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.test.mobileup.domain.model.CoinModel
import ru.test.mobileup.domain.model.StateModel
import ru.test.mobileup.presentation.screens.composables.CoinListItem
import ru.test.mobileup.presentation.screens.composables.ErrorGroup
import ru.test.mobileup.presentation.screens.composables.TopBar
import ru.test.mobileup.presentation.ui.theme.OrangeIndicator
import ru.test.mobileup.presentation.viewmodel.ViewModel

val margin = 16.dp
val doubleMargin = 32.dp
val halfMargin = 8.dp

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun MainScreen(viewModel: ViewModel, navController: NavHostController) {
    val isUsdSelected = remember { mutableStateOf(true) }
    val coinsModel: CoinModel by viewModel.data.observeAsState(CoinModel())
    val stateModel: StateModel by viewModel.state.observeAsState(StateModel())
    val coins = coinsModel.coins
    val isLoading = stateModel.loading
    val isError = stateModel.error
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopBar(isUsdSelected)
        }
    ) { innerPadding ->
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            if (isLoading) {
                CircularProgressIndicator(color = OrangeIndicator)
            } else {
                if (isError) {
                    ErrorGroup(isUsdSelected)
                }
                if (coins.isNotEmpty()) {
                    LazyColumn(state = listState, contentPadding = innerPadding) {
                        items(coins) { coin ->
                            CoinListItem(coin, navController, isUsdSelected)
                        }
                    }
                }
            }
        }
    }
}
