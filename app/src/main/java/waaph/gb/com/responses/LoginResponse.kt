package waaph.gb.com.responses

//Input
data class LoginRequest(
    val Username : String,
    val Password : String
)
//Output
data class LoginResponse (
    val Code : Int,
    val Data : String,
    val Message : String,
    val Result : Boolean
)