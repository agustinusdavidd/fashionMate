package register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fashion_mate.R
import com.example.presentation.component.LeadingTextField
import com.example.presentation.component.LeadingTrailingTextFieldPassword
import com.example.presentation.component.TextHeader
import com.example.presentation.ui.theme.Blue
import com.example.presentation.ui.theme.Primary
import com.example.presentation.ui.theme.White

@Composable
fun RegisterScreen(
    navHostController: NavHostController
) {
    /*
    Buat state untuk menyimpan inputan user
     */

    var name by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    val haveAccount = stringResource(R.string.have_account) + stringResource(R.string.spacer)
    val loginHere = stringResource(R.string.sign_in)

    val loginString = buildAnnotatedString {
        /*
        Menjadikan text bisa di click
         */
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = haveAccount, annotation = haveAccount)
            append(haveAccount)
        }
        withStyle(style = SpanStyle(color = Blue, fontWeight = FontWeight.SemiBold)) {
            pushStringAnnotation(tag = loginHere, annotation = loginHere)
            append(loginHere)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        TextHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 55.dp),
            headerText = stringResource(R.string.register_title),
            supportText = stringResource(R.string.register_suppport_text)
        )

        LeadingTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            value = name,
            label = stringResource(R.string.label_name),
            placeHolder = stringResource(R.string.placeholder_input_name),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_checkbox),
                    contentDescription = "Name Icon"
                )
            },
            onValueChange = {
                name = it
            }
        )

        LeadingTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            value = email,
            label = stringResource(R.string.label_email),
            placeHolder = stringResource(R.string.placeholder_input_email),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_email),
                    contentDescription = "Email Icon"
                )
            },
            onValueChange = {
                email = it
            }
        )

        LeadingTrailingTextFieldPassword(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            value = password,
            label = stringResource(R.string.label_password),
            placeHolder = stringResource(R.string.placeholder_input_password),
            onValueChange = {
                password = it
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_password),
                    contentDescription = "Password Icon"
                )
            }
        )

        LeadingTrailingTextFieldPassword(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            value = confirmPassword,
            label = stringResource(R.string.label_confirm_password),
            placeHolder = stringResource(R.string.placeholder_input_confirm_password),
            onValueChange = {
                confirmPassword = it
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_password),
                    contentDescription = "Password Icon"
                )
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {

            },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary,
                contentColor = White
            )
        ) {
            Text(text = stringResource(R.string.register_title))
        }

        ClickableText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 30.dp, top = 24.dp),
            text = loginString,
            style = MaterialTheme.typography.bodyMedium.copy(
                textAlign = TextAlign.Center
            ),
            onClick = {offset ->
                loginString.getStringAnnotations(offset, offset)
                    .firstOrNull()?.let { span ->
                        if(span.item == loginHere){
                            navHostController.popBackStack()
                        }
                    }
            }
        )

    }
}