package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.databinding.ItemHistoricoBinding
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.Usuario

class HistoricoAdapter(
    private val lista: List<Usuario>
) : RecyclerView.Adapter<HistoricoAdapter.HistoricoViewHolder>() {

    inner class HistoricoViewHolder(val binding: ItemHistoricoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricoViewHolder {
        val binding = ItemHistoricoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HistoricoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoricoViewHolder, position: Int) {
        val usuario = lista[position]

        holder.binding.tvNomeHistorico.text = usuario.nome
        holder.binding.tvImcHistorico.text = "IMC: %.2f".format(usuario.imc)
        holder.binding.tvCategoriaHistorico.text = "Categoria: ${usuario.categoria}"
        holder.binding.tvGastoHistorico.text =
            "Gasto diário: %.0f kcal".format(usuario.gastoCaloricoDiario)
    }

    override fun getItemCount() = lista.size
}
