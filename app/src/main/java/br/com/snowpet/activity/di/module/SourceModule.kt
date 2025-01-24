package br.com.snowpet.activity.di.module

import br.com.snowpet.core.database.SnowPetDatabase
import br.com.snowpet.data.repository.ClienteRepositoryImpl
import br.com.snowpet.data.repository.PetRepositoryImpl
import br.com.snowpet.domain.repository.ClienteRepository
import br.com.snowpet.domain.repository.PetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SourceModule {

    @Provides
    fun provideClienteRepository(snowPetDatabase: SnowPetDatabase): ClienteRepository =
        ClienteRepositoryImpl(snowPetDatabase)

    @Provides
    fun providePetRepository(snowPetDatabase: SnowPetDatabase): PetRepository =
        PetRepositoryImpl(snowPetDatabase)

}