sealed class Route (val route: String){

    object StartPage: Route("StartPage")
    object Login: Route("Login")
    object Register: Route("Register")
    object Home: Route("Home")
    object Dummy: Route("Dummy")
    object Search: Route("Search")
    object Calendar: Route("Calendar")
    object Profile: Route("Profile")

}