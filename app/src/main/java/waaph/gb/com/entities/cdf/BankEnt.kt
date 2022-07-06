package waaph.gb.com.entities.cdf

data class BankEnt(
    var id: Int = 0,
    var customerIDFK: Int = 0,
    var accountTitle: String = "",
    var accountNo: String = "",
    var bank: String = "",
    var branch: String = "",
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
