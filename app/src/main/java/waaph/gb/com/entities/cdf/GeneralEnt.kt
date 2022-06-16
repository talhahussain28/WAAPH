package waaph.gb.com.entities.cdf

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "generalTable")
data class GeneralEnt(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

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
