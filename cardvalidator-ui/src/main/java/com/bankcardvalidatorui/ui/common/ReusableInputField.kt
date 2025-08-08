package com.bankcardvalidatorui.ui.common


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bankcardvalidatorui.R
import com.bankcardvalidator.enums.CardType
import com.bankcardvalidatorui.ui.inputTypes.InputFieldValue
import com.bankcardvalidatorui.ui.inputUtils.toTextFieldValue
import com.bankcardvalidatorui.ui.inputUtils.updateWith

@Composable
fun ReusableInputField(
    modifier: Modifier = Modifier,
    label: String = "Enter text",
    value: InputFieldValue,
    onValueChange: (InputFieldValue) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    cardBrandIcon: @Composable (() -> Unit)? = null,
    errorMessageFontSize: Float = 12f
) {
    val textFieldValue = value.toTextFieldValue()

    Column(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 15.dp, vertical = 35.dp)) {
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { newValue ->
                onValueChange(value.updateWith(newValue))
            },
            label = { Text(label) },
            isError = isError,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            leadingIcon = cardBrandIcon,
            modifier = modifier.fillMaxWidth()
        )
        if (isError && !errorMessage.isNullOrBlank()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = TextStyle(fontSize = errorMessageFontSize.sp),
                modifier = modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

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
