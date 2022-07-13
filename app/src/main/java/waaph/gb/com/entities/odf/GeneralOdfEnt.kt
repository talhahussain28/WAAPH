package waaph.gb.com.entities.odf

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "generalTable")
data class GeneralOdfEnt(
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    var orderBookerName: String = "",
    var customerName: String = "",
    var customerCode: String = "",
    var businessAddress : String = "",
    var currency: String = "",
    var mode: String = "",
    var dealForBranch :String = "",
    var remarks : String = "",
    var amount : Int = 0
    )