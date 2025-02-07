package br.com.snowpet.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import br.com.snowpet.data.local.entity.AtendimentoEntity
import br.com.snowpet.data.local.entity.BanhoETosaEntity
import br.com.snowpet.data.local.entity.ClienteEntity
import br.com.snowpet.data.local.entity.ClientePetEntity
import br.com.snowpet.data.local.entity.PetEntity

@Dao
interface AtendimentoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(atendimento: AtendimentoEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplace(atendimento: AtendimentoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(atendimento: List<AtendimentoEntity>)

    @Update
    fun update(atendimento: AtendimentoEntity)

    @Delete()
    fun delete(atendimento: AtendimentoEntity)

    @RawQuery(observedEntities = [AtendimentoEntity::class])
    fun getListAtendimentos(query: SupportSQLiteQuery): List<AtendimentoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBanhoETosa(banhoETosa: BanhoETosaEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplaceBanhoETosa(banhoETosa: BanhoETosaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBanhoETosa(banhoETosa: List<BanhoETosaEntity>)

    @Update
    fun updateBanhoETosa(banhoETosa: BanhoETosaEntity)

    @Delete()
    fun deleteBanhoETosa(banhoETosa: BanhoETosaEntity)

    @RawQuery(observedEntities = [BanhoETosaEntity::class])
    fun getLastBanhoETosa(query: SupportSQLiteQuery): BanhoETosaEntity

    @RawQuery(observedEntities = [BanhoETosaEntity::class])
    fun getListBanhoETosa(query: SupportSQLiteQuery): List<BanhoETosaEntity>
}