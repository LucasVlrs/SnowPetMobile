package br.com.snowpet.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    tableName = "atendimento",
    foreignKeys = [
        ForeignKey(
            entity = BanhoETosaEntity::class,
            parentColumns = ["id_banho_tosa"],
            childColumns = ["banho_tosa_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AtendimentoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_atendimento")
    val idAtendimento: Int = 0,

    @ColumnInfo(name = "banho_tosa_id")
    val banhoETosaId: Int,

    @ColumnInfo(name = "data_atendimento")
    val data: String,

    @ColumnInfo(name = "valor_total")
    val valorTotal: Double
)

data class AtendimentoWithBanhoETosa(
    @Embedded
    val atendimento: AtendimentoEntity,

    @Relation(
        parentColumn = "banho_tosa_id",
        entityColumn = "id_banho_tosa"
    )
    val banhoETosa: BanhoETosaEntity
)