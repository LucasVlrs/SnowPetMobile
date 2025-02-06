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
import br.com.snowpet.data.local.entity.ClientePetEntity
import br.com.snowpet.data.local.entity.PetEntity

@Dao
interface ClienteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cliente: ClienteEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplace(cliente: ClienteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listaclientes: List<ClienteEntity>)

    @Update
    fun update(cliente: ClienteEntity)

    @Delete()
    fun delete(cliente: ClienteEntity)

    @RawQuery(observedEntities = [ClienteEntity::class])
    fun getListClientes(query: SupportSQLiteQuery): List<ClienteEntity>

    @Query("""
        SELECT * FROM pet 
        INNER JOIN cliente_pet ON pet.internal_id = cliente_pet.pet_id 
        WHERE cliente_pet.cliente_cpf = :cpf
    """)
    fun getPetsByClienteCpf(cpf: String): List<PetEntity>
}