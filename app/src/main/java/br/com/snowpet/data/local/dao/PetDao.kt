package br.com.snowpet.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import br.com.snowpet.data.local.entity.ClienteEntity
import br.com.snowpet.data.local.entity.PetEntity

@Dao
interface PetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cliente: PetEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplace(cliente: PetEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listaclientes: List<PetEntity>)

    @Update
    fun update(cliente: PetEntity)

    @Delete()
    fun delete(cliente: PetEntity)

    @RawQuery(observedEntities = [PetEntity::class])
    fun getListPets(query: SupportSQLiteQuery): List<PetEntity>

}