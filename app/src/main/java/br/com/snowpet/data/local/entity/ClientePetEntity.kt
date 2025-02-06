package br.com.snowpet.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "cliente_pet",
    primaryKeys = ["cliente_cpf", "pet_id"],
    foreignKeys = [
        ForeignKey(
            entity = ClienteEntity::class,
            parentColumns = ["cpf"],
            childColumns = ["cliente_cpf"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PetEntity::class,
            parentColumns = ["internal_id"],
            childColumns = ["pet_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ClientePetEntity(
    @ColumnInfo(name = "cliente_cpf")
    val clienteCpf: String,

    @ColumnInfo(name = "pet_id")
    val petId: Int
)