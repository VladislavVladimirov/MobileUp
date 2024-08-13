package ru.test.mobileup.data.dto

data class DetailedCoin(
    val categories: List<String> = emptyList(),
    val description: Description,
    val id: String? = null,
    val image: Image,
    val name: String? = null,
)

data class Description(
    val en: String? = null,
)

data class Image(
    val large: String? = null,
    val small: String? = null,
    val thumb: String? = null
)




