package waaph.gb.com.responses

//Input
data class LoginRequest(
    val username : String,
    val password : Int
)
//Output
data class LoginResponse (
    val code : Int,
    val data : String,
    val message : String,
    val result : Boolean
)