package ru.test.mobileup.presentation.screens.composables.coinScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.test.mobileup.R
import ru.test.mobileup.data.dto.DetailedCoin
import ru.test.mobileup.presentation.screens.doubleSpace
import ru.test.mobileup.presentation.screens.space
import ru.test.mobileup.presentation.ui.theme.DarkGrey


@Composable
fun CoinScreenTopBar(coin: DetailedCoin?, navController: NavHostController) {
    val padding = WindowInsets.statusBars.asPaddingValues()

    Surface(shadowElevation = 3.dp) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(padding),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                contentDescription = stringResource(R.string.back_icon_description),
                painter = painterResource(id = R.drawable.ic_back),
                modifier = Modifier
                    .padding(
                        start = space,
                        end = doubleSpace,
                        top = space,
                        bottom = space
                    )
                    .clickable {
                        navController.popBackStack()
                    })
            if (coin?.name != null) {
                Text(
                    text = coin.name.toString(),
                    color = DarkGrey,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(500),
                    modifier = Modifier.padding(
                        top = space,
                        bottom = space
                    )
                )
            }

        }
    }
}