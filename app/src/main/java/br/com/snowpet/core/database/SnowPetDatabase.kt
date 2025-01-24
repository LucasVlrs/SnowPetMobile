package br.com.snowpet.core.database

import android.content.Context
import android.preference.PreferenceManager
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.snowpet.core.database.SnowPetDatabase.Companion.DATABASE_VERSION
import br.com.snowpet.data.local.dao.ClienteDao
import br.com.snowpet.data.local.dao.PetDao
import br.com.snowpet.data.local.entity.ClienteEntity
import br.com.snowpet.data.local.entity.PetEntity

@Database(
    entities = [
        ClienteEntity::class,
        PetEntity::class,
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class SnowPetDatabase : RoomDatabase() {
    abstract val clienteDao: ClienteDao
    abstract val petDao: PetDao

    companion object {
        private lateinit var INSTANCE: SnowPetDatabase
        const val DATABASE_VERSION = 3

        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): SnowPetDatabase {
            if (Companion::INSTANCE.isInitialized.not()) {
                    synchronized(SnowPetDatabase::class.java) {
                        INSTANCE =
                            Room.databaseBuilder(
                                context,
                                SnowPetDatabase::class.java,
                                "snow_pet.db"
                            )
                                .fallbackToDestructiveMigration().build()
                    }
                }
            return INSTANCE
        }
    }
}