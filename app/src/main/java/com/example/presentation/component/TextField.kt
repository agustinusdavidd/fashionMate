package com.example.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fashion_mate.R
import com.example.presentation.ui.theme.Label

@Composable
fun PlainTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    label: String = "",
    placeHolder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    onValueChange: (String) -> Unit
) {
    BaseTextField(
        modifier = modifier,
        label = label,
        value = value,
        placeholder = placeHolder,
        keyboardOptions = keyboardOptions,
        onValueChange = onValueChange
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
    onValueChange: (String) -> Unit
) {
    BaseTextField(
        modifier = modifier,
        label = label,
        value = value,
        placeholder = placeHolder,
        leadingIcon = leadingIcon,
        keyboardOptions = keyboardOptions,
        onValueChange = onValueChange
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    leadingIcon: (@Composable () -> Unit)? = null,
    label: String = "",
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = modifier
    ){
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = Label,
            modifier = Modifier.fillMaxWidth()
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
            keyboardOptions = keyboardOptions,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LeadingTextFieldPreview() {
    LeadingTextField(
        modifier = Modifier.padding(6.dp),
        value = "",
        label = "Email",
        placeHolder = "contoh@gmail.com",
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_email),
                contentDescription = ""
            )
        },
        onValueChange = {}
    )
}