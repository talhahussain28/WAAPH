package waaph.gb.com.dao.cdf

import androidx.lifecycle.LiveData
import androidx.room.*
import waaph.gb.com.entities.cdf.GeneralEnt
import waaph.gb.com.entities.user.UserEnt

@Dao
interface GeneralDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGeneral(generalEnt: GeneralEnt)

    @Update
    suspend fun updateGeneral(generalEnt: GeneralEnt)

    @Query("SELECT * FROM generalTable ORDER BY id ASC")
    fun getAllGenerals(): LiveData<List<GeneralEnt>>

    /*@Query("SELECT * FROM generalTable WHERE id =:email and (:password)")
    fun loginUser(email: String, password: String): UserEnt*/

    @Delete
    suspend fun deleteGeneral(generalEnt: GeneralEnt)

}