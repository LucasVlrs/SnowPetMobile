package br.com.snowpet.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "banho_e_tosa")
data class BanhoETosaEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_banho_tosa")
    val idBanhoETosa: Int = 0,

    @ColumnInfo(name = "cliente")
    val cliente: String,

    @ColumnInfo(name = "pet_id")
    val pet: Int,

    @ColumnInfo(name = "tipo_banho")
    val tipoBanho: String,

    @ColumnInfo(name = "valor")
    val valor: Double,

    @ColumnInfo(name = "observacoes")
    val observacoes: String
)