package br.com.snowpet.presentation.cliente.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.snowpet.databinding.CardItemClienteBinding
import br.com.snowpet.domain.model.ClienteModel

class ListaClientesAdapter: ListAdapter<ClienteModel, ListaClientesAdapter.ViewHolder>(diffCallback){

    var onClickItem: (ClienteModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardItemClienteBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val equipment = getItem(position)
        holder.bind(equipment)
    }

    inner class ViewHolder(private val binding: CardItemClienteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cliente: ClienteModel) {
            with(binding) {

                nomeCliente.text = cliente.nome
                telefoneCliente.text = cliente.telefone

                root.setOnClickListener {
                    onClickItem(cliente)
                }
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ClienteModel>() {
            override fun areItemsTheSame(
                oldItem: ClienteModel,
                newItem: ClienteModel
            ): Boolean {
                return oldItem.cpf == newItem.cpf
            }

            override fun areContentsTheSame(
                oldItem: ClienteModel,
                newItem: ClienteModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}