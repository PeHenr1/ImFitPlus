package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.databinding.ActivityHistoricoBinding
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model.UsuarioSqlite

class HistoricoActivity : AppCompatActivity() {

    private val binding: ActivityHistoricoBinding by lazy {
        ActivityHistoricoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dao = UsuarioSqlite(this)
        val historico = dao.recuperarHistorico()

        binding.rvHistorico.layoutManager = LinearLayoutManager(this)
        binding.rvHistorico.adapter = HistoricoAdapter(historico)

        binding.btnVoltarHistorico.setOnClickListener {
            finish()
        }
    }
}
