package waaph.gb.com.entities.cdf

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "generalTable")
data class GeneralEnt(
    @PrimaryKey(autoGenerate = false)
    val generalId: Int,

    var creatorId: Int,
    var spRefID: Int = 0,
    var businessName: String = "",
    var customerCode: String = "",
    var customerGroup: String = "",
    var businessType: String = "",
    var cnic: String = "",
    var ntn: String = "",
    var strn: String = "",
    var region: String = "",
    var phone: String = "",
    var fax: String = "",
    var mobile: String = "",
    var whatsApp: String = "",
    var webSiteURL: String = "",
    var email: String = "",
    var associatedWithOtherAccount: Boolean,
    var otherOrganization: String = "",
    var paymentMode: String = "",
    var paymentTerms: String = "",
    var crMRefID: String = "",
    var spCreatedDate: Long = 0L,
    var spCreatedBy: String = "",
    var spModifiedDate: Long = 0L,
    var spModifiedBy: String = "",
    var appCreatedDate: Long = 0L,
    var appCreatedBy: String = "",
    var appModifiedDate: Long = 0L,
    var appModifiedBy: String = "",
    var syncReqdToServer: Boolean,
    var syncReqdFromServer: Boolean

)

// Input
data class GetAllRegionResponse(
    val Data: ArrayList<RegionData>
)

// Output
data class RegionData(
    val Title: String,
    val Code: String,
    val ASM: Boolean,
    val RSM: Boolean,
    val Active: String,
    val ID: String,
    val Modified: String,
    val Created: String,
    val Author: String,
    val Editor: String,
    val GUID: String

)

/*
data class RegionData(
    val Title: String,
    val Code: Long,
    val ASM: String,
    val RSM: String,
    val Active: Boolean,
    val ID: Long,
    val Modified: String,
    val Created: String,
    val Author: String,
    val Editor: String,
    val GUID: String

)
*/
