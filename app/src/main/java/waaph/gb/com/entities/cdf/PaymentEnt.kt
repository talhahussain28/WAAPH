package waaph.gb.com.entities.cdf

data class PaymentEnt(
    var cash  : Boolean,
    var cheque : Boolean,
    var bankTransfer : Boolean,
    var payOrder : Boolean,
    var creditOrDebitCard : Boolean,
    var personName : String
)
