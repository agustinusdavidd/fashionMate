package startpage

import Route
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fashion_mate.R
import com.example.presentation.ui.theme.Primary
import com.example.presentation.ui.theme.White

@Composable
fun StartPage(
    navHostController: NavHostController
) {
    val backgroundId = R.drawable.ralph_lauren_on_x
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = backgroundId) ,
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column (
            modifier = Modifier
                .padding(top = 600.dp)
                .padding(horizontal = 54.dp)
        ){
            Button(
                onClick = {
                    navHostController.navigate(Route.Login.route){
                        popUpTo("root"){
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary,
                    contentColor = White
                )
            ) {
                Text(text = stringResource(R.string.sign_in))
            }
            
            Spacer(modifier = Modifier.padding(top = 16.dp))
            
            Button(
                onClick = {
                    navHostController.navigate(Route.Register.route){
                        popUpTo("root"){
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = White,
                    contentColor = Primary
                )
            ) {
                Text(text = stringResource(R.string.register))
            }
        }
    }
}