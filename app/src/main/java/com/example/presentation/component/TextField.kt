package com.example.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.ui.theme.Label

@Composable
fun PlainTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = "",
    placeHolder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    onValueChange: (String) -> Unit,
    errorStatus: Boolean = false
) {
    BaseTextField(
        modifier = modifier,
        label = label,
        value = value,
        placeholder = placeHolder,
        keyboardOptions = keyboardOptions,
        onValueChange = onValueChange,
        errorStatus = errorStatus
    )
}

@Composable
fun LeadingTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = "",
    placeHolder: String,
    leadingIcon: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
    errorStatus: Boolean = false
) {
    BaseTextField(
        modifier = modifier,
        label = label,
        value = value,
        placeholder = placeHolder,
        leadingIcon = leadingIcon,
        keyboardOptions = keyboardOptions,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        errorStatus = errorStatus
    )
}

@Composable
fun LeadingTrailingTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = "",
    placeHolder: String,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
    errorStatus: Boolean = false
) {
    BaseTextField(
        modifier = modifier,
        label = label,
        value = value,
        placeholder = placeHolder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        errorStatus = errorStatus
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    label: String = "",
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
    errorStatus: Boolean = false
) {
    Column(
        modifier = modifier
    ){
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = Label,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        )
        OutlinedTextField(
            value = value ,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(12.dp),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.labelMedium
                )
            },
            leadingIcon = {
                leadingIcon?.invoke()
            },
            trailingIcon = {
                trailingIcon?.invoke()
            },
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            isError = !errorStatus,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(12.dp))
        )
    }
}

@Composable
fun LeadingTrailingTextFieldPassword(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = "",
    placeHolder: String,
    leadingIcon: (@Composable () -> Unit)? = null,
    onValueChange: (String) -> Unit,
    errorStatus: Boolean = false
) {
    var showPass by remember {
        mutableStateOf(false)
    }

    LeadingTrailingTextField(
        modifier = modifier,
        label = label,
        value = value,
        placeHolder = placeHolder,
        leadingIcon = leadingIcon,
        trailingIcon = {
            Icon(modifier = Modifier.clickable {
                showPass = !showPass
            },
                imageVector = if(showPass) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                contentDescription = ""
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if(showPass) VisualTransformation.None else PasswordVisualTransformation(),
        onValueChange = onValueChange,
        errorStatus = errorStatus
    )
}

@Composable
fun SmallTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    placeholder: String,
    trailingIcon: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
    errorStatus: Boolean = false,
) {
    TextField(
        value = value ,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(12.dp),
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.labelMedium
            )
        },
        trailingIcon = {
            trailingIcon?.invoke()
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        isError = errorStatus,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(12.dp))
    )
}