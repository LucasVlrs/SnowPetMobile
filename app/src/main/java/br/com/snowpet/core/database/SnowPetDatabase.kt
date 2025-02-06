package br.com.snowpet.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.snowpet.core.database.SnowPetDatabase.Companion.DATABASE_VERSION
import br.com.snowpet.data.local.dao.AtendimentoDao
import br.com.snowpet.data.local.dao.ClienteDao
import br.com.snowpet.data.local.dao.ClientePetDao
import br.com.snowpet.data.local.dao.PetDao
import br.com.snowpet.data.local.entity.AtendimentoEntity
import br.com.snowpet.data.local.entity.BanhoETosaEntity
import br.com.snowpet.data.local.entity.ClienteEntity
import br.com.snowpet.data.local.entity.ClientePetEntity
import br.com.snowpet.data.local.entity.PetEntity

@Database(
    entities = [
        ClienteEntity::class,
        PetEntity::class,
        ClientePetEntity::class,
        AtendimentoEntity::class,
        BanhoETosaEntity::class,
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class SnowPetDatabase : RoomDatabase() {
    abstract val clienteDao: ClienteDao
    abstract val petDao: PetDao
    abstract val clientepetDao: ClientePetDao
    abstract val atendimentoDao: AtendimentoDao

    companion object {
        private lateinit var INSTANCE: SnowPetDatabase
        const val DATABASE_VERSION = 1

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
                                .fallbackToDestructiveMigration()
                                .build()
                    }
                }
            return INSTANCE
        }
    }
}