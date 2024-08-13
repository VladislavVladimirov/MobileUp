package ru.test.mobileup.presentation.screens.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.test.mobileup.presentation.screens.doubleMargin
import ru.test.mobileup.presentation.screens.margin
import ru.test.mobileup.presentation.ui.theme.DarkGrey
import ru.test.mobileup.presentation.viewmodel.CoinsViewModel


@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun TopBar(isUsdSelected: MutableState<Boolean>) {
    val padding = WindowInsets.statusBars.asPaddingValues()
    val viewModel: CoinsViewModel = viewModel()
    Surface(shadowElevation = 3.dp) {
        ConstraintLayout(
            Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            val (title, chips) = createRefs()
            Text(
                text = "Cписок криптовалют",
                color = DarkGrey, fontWeight = FontWeight(500),
                fontSize = 20.sp,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top, margin)
                    start.linkTo(parent.start, margin)
                    bottom.linkTo(chips.top)
                }
            )
            Row(
                modifier = Modifier
                    .constrainAs(chips) {
                        start.linkTo(parent.start, margin)
                        top.linkTo(title.bottom, doubleMargin)
                        bottom.linkTo(parent.bottom, 13.dp)
                    },

                ) {
                CustomFilterChip(
                    selected = isUsdSelected.value,
                    onClick = {
                        isUsdSelected.value = true
                        viewModel.clearList()
                        viewModel.getCoinsVsUsd()
                    },
                    label = "USD"
                )
                Spacer(modifier = Modifier.width(8.dp))
                CustomFilterChip(
                    selected = !isUsdSelected.value,
                    onClick = {
                        isUsdSelected.value = false
                        viewModel.clearList()
                        viewModel.getCoinsVsRub()
                    },
                    label = "RUB"
                )
            }
        }
    }

}










