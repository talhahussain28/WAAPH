package waaph.gb.com.responses

data class CustomerGroupResponse(
    val code : Int,
    val message : String,
    val result : Boolean,
    val data : ArrayList<CustomerGroupListData>
)

data class CustomerGroupListData(
    val title : String,
    val description : String,
    val active : Boolean,
    val iD : Int,
    val modified : String,
    val created : String,
    val author : String,
    val editor : String,
    val gUID : String
)