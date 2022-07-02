package waaph.gb.com.entities.cdf

data class ContactEnt(
    var id: Int,

    var customerIDFK: Int,
    var customerAddressIDFK: Int,
    var personName: String = "",
    var designation: String = "",
    var purpose: String = "",
    var phone: String = "",
    var email: String = "",
    var mobilePrimary: String = "",
    var mobileSecondary: String = "",
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
