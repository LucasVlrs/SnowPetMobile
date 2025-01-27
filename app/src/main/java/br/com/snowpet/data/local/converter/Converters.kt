package br.com.snowpet.data.local.converter

import androidx.room.*
import br.com.snowpet.data.local.entity.PetEntity

@Entity(
    tableName = "dono_pet",
    indices = [Index(value = ["cpf", "pet_internal_id"])],
    primaryKeys = ["cpf", "pet_internal_id"]
)
data class DonoPetEntity(
    @ColumnInfo(name = "cpf")
    val cpf: String,
    @ColumnInfo(name = "pet_internal_id")
    val petInternalId: Int
)

class Converters {

    @TypeConverter
    fun fromPetEntityList(petList: List<PetEntity>?): String {
        return petList?.joinToString(";") { pet ->
            listOf(
                pet.nome,
                pet.idade.toString(),
                pet.raca,
                pet.porte,
                pet.sexo,
                pet.problemasSaude,
                pet.alergias,
                pet.informacoesAdicionais ?: ""
            ).joinToString(",")
        } ?: ""
    }

    @TypeConverter
    fun toPetEntityList(data: String): List<PetEntity> {
        if (data.isBlank()) return emptyList()
        return data.split(";").map { petString ->
            val petData = petString.split(",")
            PetEntity(
                nome = petData.getOrNull(0) ?: "",
                idade = petData.getOrNull(1)?.toIntOrNull() ?: 0,
                raca = petData.getOrNull(2) ?: "",
                porte = petData.getOrNull(3) ?: "",
                sexo = petData.getOrNull(4) ?: "",
                problemasSaude = petData.getOrNull(5) ?: "",
                alergias = petData.getOrNull(6) ?: "",
                informacoesAdicionais = petData.getOrNull(7)
            )
        }
    }
}