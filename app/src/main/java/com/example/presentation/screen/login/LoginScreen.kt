package login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavHostController
import com.example.fashion_mate.R
import com.example.presentation.ui.theme.Black
import com.example.presentation.ui.theme.Primary

@Composable
fun LoginScreen(
    navHostController: NavHostController
) {
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val notHaveAccount = "" + stringResource(R.string.spacer)
    val registerText = stringResource(R.string.register)
    val registerString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Black)) {
            pushStringAnnotation(tag = notHaveAccount, annotation = notHaveAccount)
            append(notHaveAccount)
        }
        withStyle(style = SpanStyle(color = Primary, fontWeight = FontWeight.SemiBold)) {
            pushStringAnnotation(tag = registerText, annotation = registerText)
            append(registerText)
        }
    }
}