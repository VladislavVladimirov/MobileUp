package ru.test.mobileup.presentation.screens.composables.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.test.mobileup.R
import ru.test.mobileup.presentation.ui.theme.OrangeIndicator
import ru.test.mobileup.presentation.viewmodel.CoinsViewModel

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun MainScreenErrorGroup(isUsdSelected: MutableState<Boolean>) {
    val viewModel: CoinsViewModel = viewModel()
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (icon, text, button) = createRefs()

        Image(painterResource(id = R.drawable.ic_error_title_icon),
            contentDescription = stringResource(R.string.icon_description),
            modifier = Modifier.constrainAs(icon) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            })
        Text(
            text = "Произошла какая-то ошибка :(\nПопробуем снова?",
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(400),
            fontSize = 16.sp,
            modifier = Modifier.constrainAs(text) {
                top.linkTo(icon.bottom, 13.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        IconButton(
            onClick = {
                if (isUsdSelected.value) {
                    viewModel.getCoinsVsUsd()
                } else {
                    viewModel.getCoinsVsRub()
                }
            },
            modifier = Modifier
                .constrainAs(button) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(text.bottom, 30.dp)
                }
                .height(36.dp)
                .width(175.dp)
                .background(OrangeIndicator, RoundedCornerShape(4.dp))

        ) {
            Text(
                text = "ПОПРОБОВАТЬ",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight(500)
            )
        }
    }
}