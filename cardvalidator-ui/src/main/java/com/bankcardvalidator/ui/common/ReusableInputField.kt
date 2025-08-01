package com.bankcardvalidator.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.bankcardvalidator.ui.inputTypes.InputFieldValue

@Composable
fun ReusableInputField(
    modifier: Modifier = Modifier,
    label: String = "Enter text",
    value: InputFieldValue,
    onValueChange: (InputFieldValue) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val textFieldValue = when (value) {
        is InputFieldValue.Plain -> TextFieldValue(value.text)
        is InputFieldValue.WithSelection -> value.value
    }
    Column(modifier = modifier.padding(horizontal = 15.dp, vertical = 25.dp)) {
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { newValue ->
                val wrapped = when (value) {
                    is InputFieldValue.Plain -> InputFieldValue.Plain(newValue.text)
                    is InputFieldValue.WithSelection -> InputFieldValue.WithSelection(newValue)
                }
                onValueChange(wrapped)
            },
            label = { Text(label) },
            isError = isError,
            modifier = modifier
                .fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            visualTransformation = visualTransformation
        )
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}
