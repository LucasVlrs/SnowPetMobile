package br.com.snowpet.activity.di.module

import android.content.Context
import androidx.room.Room
import br.com.snowpet.core.database.SnowPetDatabase
import br.com.snowpet.data.local.dao.ClienteDao
import br.com.snowpet.data.local.dao.PetDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun databaseSnowPetProvider(@ApplicationContext context: Context): SnowPetDatabase {
        return Room.databaseBuilder(
            context,
            SnowPetDatabase::class.java,
            "snow_pet_database"
        ).build()
    }

    @Provides
    fun provideClienteDao(database: SnowPetDatabase): ClienteDao {
        return database.clienteDao
    }

    @Provides
    fun providePetDao(database: SnowPetDatabase): PetDao {
        return database.petDao
    }
}