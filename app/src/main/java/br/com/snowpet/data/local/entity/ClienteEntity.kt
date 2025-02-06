package br.com.snowpet.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "cliente",
    indices = [
        Index(value = ["internal_id"]),
        Index(value = ["cpf"], unique = true),
    ]
)
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
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "internal_id")
    var internalId: Int = 0
}