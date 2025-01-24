package br.com.snowpet.presentation.pet.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.snowpet.databinding.CardItemPetBinding
import br.com.snowpet.domain.model.PetModel

class ListaPetsAdapter : ListAdapter<PetModel, ListaPetsAdapter.ViewHolder>(diffCallback) {
    var onClickItem: (PetModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardItemPetBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val equipment = getItem(position)
        holder.bind(equipment)
    }

    inner class ViewHolder(private val binding: CardItemPetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cliente: PetModel) {
            with(binding) {

                nomeCliente.text = cliente.nome
                telefoneCliente.text = cliente.raca

                root.setOnClickListener {
                    onClickItem(cliente)
                }
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<PetModel>() {
            override fun areItemsTheSame(
                oldItem: PetModel,
                newItem: PetModel
            ): Boolean {
                return oldItem.nome == newItem.nome
            }

            override fun areContentsTheSame(
                oldItem: PetModel,
                newItem: PetModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}