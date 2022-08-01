package waaph.gb.com.entities.cdf

import androidx.room.Embedded
import androidx.room.Relation
import waaph.gb.com.entities.user.UserEnt

// 1-1 relation
data class UserWithGeneral(
    @Embedded
    val user: UserEnt,

    @Relation(
        parentColumn = "userId",
        entityColumn = "creatorId"
    )
    val generalForms: GeneralEnt
)
