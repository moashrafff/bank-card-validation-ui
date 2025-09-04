package com.bankcardvalidatorui.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bankcardvalidatorui.R
import com.bankcardvalidatorui.ui.inputTypes.InputFieldValue
import com.bankcardvalidatorui.ui.inputUtils.toTextFieldValue
import com.bankcardvalidatorui.ui.inputUtils.updateWith

@Composable
internal fun ReusableInputField(
    modifier: Modifier = Modifier,
    label: String = stringResource(R.string.enter_text),
    value: InputFieldValue,
    onValueChange: (InputFieldValue) -> Unit,
    isError: Boolean = false,
    tooFarMessage: String? = null,
    errorMessage: String? = null,
    cardBrandIcon: @Composable (() -> Unit)? = null,
    errorMessageFontSize: Float = 12f,
    onClearCardNumberClick: () -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    clearIcon: ImageVector? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val textFieldValue = value.toTextFieldValue()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
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
                    if (textFieldValue.text.isNotEmpty() || textFieldValue.text.isNotBlank()) {
                        Icon(
                            imageVector = clearIcon ?: Icons.Default.Clear,
                            contentDescription = "Error icon",
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    onClearCardNumberClick()
                                }
                        )
                    }
                },
                modifier = modifier,
                visualTransformation = visualTransformation
            )
            if (isError && !errorMessage.isNullOrBlank()) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = TextStyle(fontSize = errorMessageFontSize.sp),
                    modifier = modifier.padding(start = 16.dp, top = 4.dp)
                )
            }
            if (tooFarMessage != null) {
                Text(
                    text = tooFarMessage,
                    color = Color.Gray,
                    style = TextStyle(fontSize = errorMessageFontSize.sp),
                    modifier = modifier.padding(start = 16.dp, top = 4.dp)
                )
            }
        }
    }
}
