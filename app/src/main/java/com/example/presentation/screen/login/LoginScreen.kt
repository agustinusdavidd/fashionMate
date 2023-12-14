package login

import Route
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import androidx.navigation.compose.rememberNavController
import com.example.fashion_mate.R
import com.example.presentation.component.LeadingTextField
import com.example.presentation.component.LeadingTrailingTextFieldPassword
import com.example.presentation.component.TextHeader
import com.example.presentation.data.Login.LoginUiEvent
import com.example.presentation.data.Login.LoginViewModel
import com.example.presentation.graphs.HomeNavGraph
import com.example.presentation.navigation.Auth
import com.example.presentation.ui.theme.Black
import com.example.presentation.ui.theme.Primary
import com.example.presentation.ui.theme.White

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    navHostController: NavHostController
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val notHaveAccount = stringResource(R.string.dont_have_account) + stringResource(R.string.spacer)
    val registerHere = stringResource(R.string.sign_up)
    val forgetPass = stringResource(R.string.forget_password)
    val registerString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Black)) {
            pushStringAnnotation(tag = notHaveAccount, annotation = notHaveAccount)
            append(notHaveAccount)
        }
        withStyle(style = SpanStyle(color = Primary, fontWeight = FontWeight.SemiBold)) {
            pushStringAnnotation(tag = registerHere, annotation = registerHere)
            append(registerHere)
        }
    }
    val forgetPassword = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Black, fontWeight = FontWeight.SemiBold)) {
            pushStringAnnotation(tag = forgetPass, annotation = forgetPass)
            append(forgetPass)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            TextHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 55.dp),
                headerText = stringResource(R.string.login_title),
                supportText = stringResource(R.string.login_suppport_text)
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
                    loginViewModel.onEvent(LoginUiEvent.emailChanged(it))
                    email = it
                },
                errorStatus = loginViewModel.loginUiState.value.emailError
            )

            LeadingTrailingTextFieldPassword(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                value = password,
                label = stringResource(R.string.label_password),
                placeHolder = stringResource(R.string.placeholder_input_password),
                onValueChange = {
                    loginViewModel.onEvent(LoginUiEvent.passwordChanged(it))
                    password = it
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = "Password Icon"
                    )
                },
                errorStatus = loginViewModel.loginUiState.value.passwordError
            )

            ClickableText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp, top = 24.dp),
                text = forgetPassword,
                style = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.Right
                ),
                onClick = { offset ->
                    forgetPassword.getStringAnnotations(offset, offset)
                        .firstOrNull()?.let { span ->
                            if (span.item == forgetPass) {
                                /*
                                * TODO Navigate To Forget Password
                                * */
                            }
                        }
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    loginViewModel.onEvent(LoginUiEvent.loginButtonClicked)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary,
                    contentColor = White
                ),
                enabled = loginViewModel.validationStatus.value
            ) {
                Text(text = stringResource(R.string.login))
            }

            ClickableText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 30.dp, top = 24.dp),
                text = registerString,
                style = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.Center
                ),
                onClick = { offset ->
                    registerString.getStringAnnotations(offset, offset)
                        .firstOrNull()?.let { span ->
                            if (span.item == registerHere) {
                                navHostController.navigate(Auth.Register.route)
                            }
                        }
                }
            )
        }

        if(loginViewModel.progress.value) {
            CircularProgressIndicator()
        }

    }
}