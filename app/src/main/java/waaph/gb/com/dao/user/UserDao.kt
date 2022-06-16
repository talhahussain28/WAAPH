package waaph.gb.com.dao.user

import androidx.lifecycle.LiveData
import androidx.room.*
import waaph.gb.com.entities.user.UserEnt

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(userEnt: UserEnt)

    @Update
    suspend fun updateUser(userEnt: UserEnt)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getAllUsers(): LiveData<List<UserEnt>>

    @Query("SELECT * FROM user_table WHERE id =:email and (:password)")
    fun loginUser(email: String, password: String): UserEnt

    @Delete
    suspend fun deleteUser(user: UserEnt)

}