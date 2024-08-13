@file:Suppress("KotlinConstantConditions")

package ru.test.mobileup.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.test.mobileup.R
import ru.test.mobileup.domain.model.StateModel
import ru.test.mobileup.presentation.screens.composables.coinScreen.CoinScreenErrorGroup
import ru.test.mobileup.presentation.screens.composables.coinScreen.CoinScreenTopBar
import ru.test.mobileup.presentation.screens.composables.coinScreen.HyperlinkText
import ru.test.mobileup.presentation.ui.theme.OrangeIndicator
import ru.test.mobileup.presentation.util.DataFormatter
import ru.test.mobileup.presentation.viewmodel.DetailedCoinsViewModel

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun CoinScreen(
    id: String?,
    viewModel: DetailedCoinsViewModel = viewModel(),
    navController: NavHostController
) {
    val stateModel: StateModel by viewModel.state.observeAsState(StateModel())
    val isLoading = stateModel.loading
    val isError = stateModel.error
    val coin = viewModel.detailedCoin.value?.coin
    val coinText = coin?.description?.en.toString()
    val formattedText = DataFormatter.removeLinks(coin?.description?.en.toString())
    val linksMap = DataFormatter.extractLinks(coinText)
    val scrollState = rememberScrollState()

    LaunchedEffect(id) {
        viewModel.getCoinById(id)
    }
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = { CoinScreenTopBar(coin, navController) },
        content = {
            if (isLoading) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        color = OrangeIndicator,
                    )
                }
            }
            if (isError) {
                CoinScreenErrorGroup(id)
            }
            if (coin != null) {
                ConstraintLayout(
                    Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                        .padding(it)
                        .verticalScroll(scrollState)
                ) {
                    val (logo, descriptionHeader, description, categoriesHeader, categories) = createRefs()

                    AsyncImage(model = coin.image.large,
                        contentDescription = stringResource(R.string.logo_description),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape)
                            .constrainAs(logo) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top, 10.dp)
                            })
                    Text(
                        text = stringResource(R.string.description),
                        fontWeight = FontWeight(500),

                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.constrainAs(descriptionHeader) {
                            start.linkTo(parent.start, space)
                            top.linkTo(logo.bottom, space)
                        }
                    )
                    if (coinText.isNotEmpty()) {
                        HyperlinkText(
                            modifier = Modifier
                                .constrainAs(description) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(descriptionHeader.bottom, halfSpace)
                                }
                                .padding(horizontal = space),
                            fullText = formattedText,
                            linkText = linksMap.keys.toList(),
                            hyperlinks = linksMap.values.toList(),
                            fontSize = 16.sp
                        )
                        Text(
                            text = stringResource(R.string.categories),
                            fontWeight = FontWeight(500),
                            fontSize = 20.sp,
                            color = Color.Black,
                            modifier = Modifier.constrainAs(categoriesHeader) {
                                start.linkTo(parent.start, space)
                                top.linkTo(description.bottom, space)
                            }
                        )
                        Text(
                            text = coin.categories.joinToString(),
                            fontWeight = FontWeight(400),
                            fontSize = 16.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .constrainAs(categories) {
                                    start.linkTo(parent.start)
                                    top.linkTo(categoriesHeader.bottom, halfSpace)
                                    end.linkTo(parent.end)
                                }
                                .padding(start = space, end = 18.dp)
                        )
                    } else {
                        Text(
                            stringResource(R.string.no_description),
                            fontSize = 16.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .constrainAs(description) {
                                    start.linkTo(parent.start)
                                    top.linkTo(descriptionHeader.bottom, halfSpace)
                                }
                                .padding(horizontal = space))
                        Text(
                            text = stringResource(R.string.categories),
                            fontWeight = FontWeight(500),
                            fontSize = 20.sp,
                            color = Color.Black,
                            modifier = Modifier.constrainAs(categoriesHeader) {
                                start.linkTo(parent.start, space)
                                top.linkTo(description.bottom, space)
                            }
                        )
                        Text(
                            text = coin.categories.joinToString(),
                            fontWeight = FontWeight(400),
                            fontSize = 16.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .constrainAs(categories) {
                                    start.linkTo(parent.start)
                                    top.linkTo(categoriesHeader.bottom, halfSpace)
                                    end.linkTo(parent.end)
                                }
                                .padding(start = space, end = 18.dp)
                        )
                    }
                }
            }
        }
    )
}




