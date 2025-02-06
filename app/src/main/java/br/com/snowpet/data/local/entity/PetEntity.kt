package br.com.snowpet.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "pet",
    indices = [
        Index(value = ["internal_id"]),
        Index(value = ["pet_id"], unique = true)
    ]
)
data class PetEntity(
    @ColumnInfo(name = "pet_id")
    val petId: Int = 0,
    @ColumnInfo(name = "nome")
    val nome: String = "",
    @ColumnInfo(name = "idade")
    val idade: Int = 0,
    @ColumnInfo(name = "raca")
    val raca: String = "",
    @ColumnInfo(name = "porte")
    val porte: String = "",
    @ColumnInfo(name = "sexo")
    val sexo: String = "",
    @ColumnInfo(name = "problemasSaude")
    val problemasSaude: String = "",
    @ColumnInfo(name = "alergias")
    val alergias: String = "",
    @ColumnInfo(name = "informacoesAdicionais")
    val informacoesAdicionais: String? = "",
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "internal_id")
    var internalId: Int = 0
}