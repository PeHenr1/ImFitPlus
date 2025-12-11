package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model

interface UsuarioDao {
    fun inserirUsuario(usuario: Usuario): Long
    fun recuperarUsuario(id: Int): Usuario?
    fun recuperarHistorico(): MutableList<Usuario>
    fun deletarUsuario(id: Int): Int
}