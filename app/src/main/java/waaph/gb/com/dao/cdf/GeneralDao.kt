package waaph.gb.com.dao.cdf

import androidx.room.*
import waaph.gb.com.entities.cdf.GeneralEnt
import waaph.gb.com.entities.cdf.UserWithGeneral

@Dao
interface GeneralDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGeneral(generalEnt: GeneralEnt)

    @Transaction
    @Query("SELECT * FROM user_table")
    fun getUsersWithGeneral(): List<UserWithGeneral>

    /*@Query("SELECT * FROM generalTable WHERE id=:id ")
    suspend fun getGeneralSingle(id: Int): GeneralEnt*/

    /*@Update
    suspend fun updateGeneral(generalEnt: GeneralEnt)*/

    /*@Query("SELECT * FROM generalTable ORDER BY id ASC")
    fun getAllGenerals(): LiveData<List<GeneralEnt>>*/

    /*@Query("SELECT * FROM generalTable WHERE id =:email and (:password)")
    fun loginUser(email: String, password: String): UserEnt*/

    /*@Delete
    suspend fun deleteGeneral(generalEnt: GeneralEnt)*/

}