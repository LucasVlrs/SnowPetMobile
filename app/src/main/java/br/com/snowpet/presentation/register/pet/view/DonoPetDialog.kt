package br.com.snowpet.presentation.register.pet.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import br.com.snowpet.databinding.DialogDonoPetBinding
import br.com.snowpet.domain.model.ClienteModel

class DonoPetDialog(
    private var clientes: List<ClienteModel> = emptyList(),
    private var onClienteSelected: (ClienteModel) -> Unit = {}
) : DialogFragment() {

    private var _binding: DialogDonoPetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setCancelable(true)
            setCanceledOnTouchOutside(true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogDonoPetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nomeClientes = clientes.map { it.nome }

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, nomeClientes)
        binding.dropdownDonos.setAdapter(adapter)

        binding.buttonConfirmarDono.setOnClickListener {
            val selectedOwnerName = binding.dropdownDonos.text.toString()
            if (selectedOwnerName.isNotEmpty()) {
                val selectedOwner = clientes.find { it.nome == selectedOwnerName }
                if (selectedOwner != null) {
                    onClienteSelected(selectedOwner)
                    dismiss()
                    Toast.makeText(requireContext(), "Pet Cadastrado com Sucesso! E dono registrado!", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(requireContext(), "Dono não encontrado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Selecione um dono antes de Confirmar!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.closeDialogButton.setOnClickListener {
            dismiss()
            Toast.makeText(requireContext(), "Pet Cadastrado com Sucesso!", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
