package br.com.snowpet.presentation.atendimento.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.snowpet.databinding.CardItemAtendimentoBinding
import br.com.snowpet.domain.model.AtendimentoModel

class ListaAtendimentosAdapter: ListAdapter<AtendimentoModel, ListaAtendimentosAdapter.ViewHolder>(diffCallback){

    var onClickItem: (AtendimentoModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardItemAtendimentoBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cliente = getItem(position)
        holder.bind(cliente)
    }

    inner class ViewHolder(private val binding: CardItemAtendimentoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(atendimento: AtendimentoModel) {
            with(binding) {

                idAtendimento.text = idAtendimento.toString()
                nomeCliente.text = atendimento.banhoETosa.cliente
                nomePet.text = atendimento.banhoETosa.pet.toString()
                dataAtendimento.text = atendimento.data
                tipoAtendimento.text = atendimento.banhoETosa.tipoBanho

                valorTotal.text = buildString {
                    append("R$")
                    append(atendimento.valorTotal)
                }

                root.setOnClickListener {
                    detailsCard.isVisible = !detailsCard.isVisible
                    onClickItem(atendimento)
                }

            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<AtendimentoModel>() {
            override fun areItemsTheSame(
                oldItem: AtendimentoModel,
                newItem: AtendimentoModel
            ): Boolean {
                return oldItem.idAtendimento == newItem.idAtendimento
            }

            override fun areContentsTheSame(
                oldItem: AtendimentoModel,
                newItem: AtendimentoModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}