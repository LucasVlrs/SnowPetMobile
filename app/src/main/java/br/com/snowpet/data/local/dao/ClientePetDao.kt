package br.com.snowpet.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import br.com.snowpet.data.local.entity.ClientePetEntity

@Dao
interface ClientePetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setDonoPet(clientePetEntity: ClientePetEntity): Long
}