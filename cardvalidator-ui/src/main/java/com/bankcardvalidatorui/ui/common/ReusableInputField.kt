package com.bankcardvalidatorui.ui.common

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bankcardvalidatorui.R
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
    errorMessageFontSize: Float = 12f,
    onClearCardNumberClick: () -> Unit,
    clearIcon:Painter? = null
) {
    val textFieldValue = value.toTextFieldValue()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 35.dp)
    ) {
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
            trailingIcon = {
                Icon(
                    painter = clearIcon ?: painterResource(id = R.drawable.ic_cancel),
                    contentDescription = "Error icon",
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            onClearCardNumberClick()
                        }
                )
            },
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
