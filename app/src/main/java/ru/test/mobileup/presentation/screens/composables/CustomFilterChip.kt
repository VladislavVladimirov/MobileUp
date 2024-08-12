package ru.test.mobileup.presentation.screens.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FilterChipDefaults.filterChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.test.mobileup.presentation.ui.theme.LightGrey
import ru.test.mobileup.presentation.ui.theme.LightOrange
import ru.test.mobileup.presentation.ui.theme.Orange

@Composable
fun CustomFilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: String
) {
    FilterChip(
        border = FilterChipDefaults.filterChipBorder(
            borderColor = Color.Transparent, selected = true, enabled = true
        ),
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        selected = selected,
        label = {
            Text(
                text = label, fontWeight = FontWeight(400),
                color = if (selected) Orange else Color.Black,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        colors = filterChipColors(
            selectedContainerColor = LightOrange,
            containerColor = LightGrey,
        ),
        modifier = Modifier
            .border(1.dp, Color.Transparent)
            .height(32.dp)
            .width(89.dp)
    )
}