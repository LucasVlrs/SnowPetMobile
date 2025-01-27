package br.com.snowpet.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import br.com.snowpet.data.local.converter.Converters

@Entity(
    tableName = "cliente",
    indices = [Index(value = ["internal_id"])]
)
@TypeConverters(Converters::class)
data class ClienteEntity(
    @ColumnInfo(name = "nome")
    val nome: String = "",
    @ColumnInfo(name = "cpf")
    val cpf: String = "",
    @ColumnInfo(name = "telefone")
    val telefone: String = "",
    @ColumnInfo(name = "endereco")
    val endereco: String = "",
    @ColumnInfo(name = "email")
    val email: String = "",
    @ColumnInfo(name = "redes_sociais")
    val redesSociais: String = "",
    @ColumnInfo(name = "pets")
    val pets: List<PetEntity>? = listOf()
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "internal_id")
    var internalId: Int = 0
}