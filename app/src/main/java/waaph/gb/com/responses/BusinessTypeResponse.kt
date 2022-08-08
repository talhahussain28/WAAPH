package waaph.gb.com.responses

data class BusinessTypeResponse (
    val Code : Int,
    val Message : String,
    val Result : Boolean,
    val Data : ArrayList<BusinessTypeData>
    )

data class BusinessTypeData(
    val Title : String,
    val Active : Boolean,
    val ID : Int,
    val Modified : String,
    val Created : String,
    val Author : String,
    val Editor : String,
    val GUID : String
){
    override fun toString(): String {
        return Title
    }
}