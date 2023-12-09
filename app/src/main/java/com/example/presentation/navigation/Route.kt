sealed class Route (val route: String){

    object StartPage: Route("StartPage")
    object Login: Route("Login")
    object Register: Route("Register")
    object Home: Route("Home")
    object Dummy: Route("Dummy")

}