package com.bankcardvalidatorui.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bankcardvalidator.enums.CardType
import com.bankcardvalidatorui.R

@Composable
fun CardBrandIcon(cardType: CardType?) {
    val icon = when (cardType) {
        CardType.VISA -> painterResource(id = R.drawable.visa)
        CardType.MASTERCARD -> painterResource(R.drawable.mastercard)
        CardType.AMERICAN_EXPRESS -> painterResource(R.drawable.american_express)
        CardType.DISCOVER -> painterResource(R.drawable.discover)
        CardType.JCB -> painterResource(R.drawable.jcb)
        CardType.DINERS_CLUB -> painterResource(R.drawable.diners_club)
        CardType.UNION_PAY -> painterResource(R.drawable.union_pay)
        null -> null
    }
    icon?.let {
        Icon(
            painter = it,
            contentDescription = "Card brand icon",
            modifier = Modifier
                .padding(start = 8.dp)
                .size(26.dp),
            tint = Color.Unspecified
        )
    }
}