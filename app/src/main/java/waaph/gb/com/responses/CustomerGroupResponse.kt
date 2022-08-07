package waaph.gb.com.responses

data class CustomerGroupResponse(
    val Code : Int,
    val Message : String,
    val Result : Boolean,
    val Data : ArrayList<CustomerGroupListData>
)

data class CustomerGroupListData(
    val Title : String,
    val Description : String,
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