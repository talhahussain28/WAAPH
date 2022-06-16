package waaph.gb.com.database.cdf

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import waaph.gb.com.dao.cdf.GeneralDao
import waaph.gb.com.dao.user.UserDao
import waaph.gb.com.entities.cdf.GeneralEnt


@Database(entities = [GeneralEnt::class], version = 1)
abstract class GeneralDatabase : RoomDatabase() {

    abstract val generalDao: GeneralDao

    companion object {
        @Volatile
        private var INSTANCE: GeneralDatabase? = null

        fun getInstance(context: Context): GeneralDatabase {
            synchronized(this) {
                var instance: GeneralDatabase? = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GeneralDatabase::class.java,
                        "generalDatabase"
                    ).build()
                }

                return instance
            }
        }

    }

}