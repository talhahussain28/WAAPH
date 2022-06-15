package waaph.gb.com.entities.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEnt(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val fullName: String,
    val email: String,
    val password: String
)
