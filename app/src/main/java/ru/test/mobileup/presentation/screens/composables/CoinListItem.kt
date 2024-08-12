package ru.test.mobileup.presentation.screens.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import ru.test.mobileup.data.dto.Coin
import ru.test.mobileup.presentation.screens.halfMargin
import ru.test.mobileup.presentation.screens.margin
import ru.test.mobileup.presentation.ui.theme.Green
import ru.test.mobileup.presentation.ui.theme.GreyCoinName
import ru.test.mobileup.presentation.ui.theme.GreyShortName
import ru.test.mobileup.presentation.ui.theme.Red
import ru.test.mobileup.presentation.util.DataFormatter
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun CoinListItem(
    coin: Coin,
    navController: NavHostController,
    isUsdSelected: MutableState<Boolean>
) {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .width(400.dp)
            .height(56.dp)
    ) {
        val (logo, name, shortName, price, change) = createRefs()
        AsyncImage(model = coin.image,
            contentDescription = "Логотип",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .constrainAs(logo) {
                    top.linkTo(parent.top, halfMargin)
                    start.linkTo(parent.start, margin)
                    bottom.linkTo(parent.bottom, halfMargin)
                })
        Text(
            text = DataFormatter.ellipsize(coin.name.toString()),
            color = GreyCoinName,
            fontWeight = FontWeight(500),
            fontSize = 16.sp,
            modifier = Modifier.constrainAs(name) {
                top.linkTo(parent.top, halfMargin)
                start.linkTo(logo.end, halfMargin)
            })
        if (coin.current_price != null) {
            Text(
                text = if (isUsdSelected.value) DataFormatter.formatPrice(
                    coin.current_price,
                    "$"
                ) else DataFormatter.formatPrice(
                    coin.current_price,
                    "₽ "
                ),
                color = GreyCoinName,
                fontWeight = FontWeight(600),
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(price) {
                    top.linkTo(parent.top, halfMargin)
                    end.linkTo(parent.end, margin)
                })
        }
        Text(
            text = coin.symbol.toString().uppercase(),
            color = GreyShortName,
            fontWeight = FontWeight(400),
            fontSize = 14.sp,
            modifier = Modifier.constrainAs(shortName) {
                top.linkTo(name.bottom, 3.dp)
                start.linkTo(logo.end, halfMargin)
            })


        if (coin.price_change_percentage_24h.toString().startsWith("-")) {
            Text(
                text = coin.price_change_percentage_24h?.let {
                    BigDecimal(it).setScale(
                        2,
                        RoundingMode.HALF_UP
                    ).toString()
                } + "%",
                color = Red,
                fontWeight = FontWeight(400),
                fontSize = 14.sp,
                modifier = Modifier.constrainAs(change) {
                    top.linkTo(price.bottom, 4.dp)
                    end.linkTo(parent.end, margin)
                })
        } else {
            Text(
                text = "+" + coin.price_change_percentage_24h?.let {
                    BigDecimal(it).setScale(
                        2,
                        RoundingMode.HALF_UP
                    ).toString()
                } + "%",
                color = Green,
                fontWeight = FontWeight(400),
                fontSize = 14.sp,
                modifier = Modifier.constrainAs(change) {
                    top.linkTo(price.bottom, 4.dp)
                    end.linkTo(parent.end, margin)
                })
        }
    }
}