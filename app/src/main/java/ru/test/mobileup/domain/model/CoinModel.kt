package ru.test.mobileup.domain.model

import ru.test.mobileup.data.dto.Coin

data class CoinModel(
    val coins: List<Coin> = emptyList(),
)

