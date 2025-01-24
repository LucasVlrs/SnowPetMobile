package br.com.snowpet.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "pet",
    indices = [Index(value = ["internal_id"])]
)
data class PetEntity (
    @ColumnInfo(name = "nome")
    val nome: String = "",
    @ColumnInfo(name = "dono")
    val dono: String = "",
    @ColumnInfo(name = "idade")
    val idade: Int = 0,
    @ColumnInfo(name = "raca")
    val raca: String = "",
    @ColumnInfo(name = "porte")
    val porte: String = "",
    @ColumnInfo(name = "alergias")
    val alergias: String = "",
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "internal_id")
    var internalId: Int = 0
}