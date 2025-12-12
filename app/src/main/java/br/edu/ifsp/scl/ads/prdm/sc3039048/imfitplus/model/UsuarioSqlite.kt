package br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.model

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.sql.SQLException
import br.edu.ifsp.scl.ads.prdm.sc3039048.imfitplus.R

class UsuarioSqlite(context: Context) : UsuarioDao {

    companion object {
        private const val DATABASE_NAME = "imfitplus.db"
        private const val TABLE_USUARIO = "usuario"

        private const val COL_ID = "id"
        private const val COL_NOME = "nome"
        private const val COL_DATA_NASC = "dataNasc"
        private const val COL_IDADE = "idade"
        private const val COL_SEXO = "sexo"
        private const val COL_ALTURA = "altura"
        private const val COL_PESO = "peso"
        private const val COL_ATIVIDADE = "nivelAtividade"
        private const val COL_IMC = "imc"
        private const val COL_CATEGORIA = "categoria"
        private const val COL_TMB = "tmb"
        private const val COL_GASTO = "gastoCalorico"
        private const val COL_PESO_IDEAL = "pesoIdeal"

        private const val COL_FREQ_CARD = "freqCard"

        val CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS $TABLE_USUARIO (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_NOME TEXT,
                $COL_IDADE INTEGER,
                $COL_DATA_NASC TEXT,
                $COL_SEXO TEXT,
                $COL_ALTURA REAL,
                $COL_PESO REAL,
                $COL_ATIVIDADE TEXT,
                $COL_IMC REAL,
                $COL_CATEGORIA TEXT,
                $COL_TMB REAL,
                $COL_GASTO REAL,
                $COL_PESO_IDEAL REAL,
                $COL_FREQ_CARD REAL
            );
        """
    }

    private val database: SQLiteDatabase = context.openOrCreateDatabase(
        DATABASE_NAME,
        MODE_PRIVATE,
        null
    )

    init {
        try {
            database.execSQL(CREATE_TABLE)
        } catch (e: SQLException) {
            Log.e(context.getString(R.string.app_name), e.message.toString())
        }
    }

    override fun inserirUsuario(usuario: Usuario): Long {
        return database.insert(TABLE_USUARIO, null, usuario.toContentValues())
    }

    override fun recuperarUsuario(id: Int): Usuario? {
        val cursor = database.query(
            TABLE_USUARIO,
            null,
            "$COL_ID = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        return if (cursor.moveToFirst()) cursor.toUsuario() else null
    }

    override fun recuperarHistorico(): MutableList<Usuario> {
        val lista = mutableListOf<Usuario>()
        val cursor = database.rawQuery("SELECT * FROM $TABLE_USUARIO ORDER BY $COL_ID DESC", null)

        while (cursor.moveToNext()) {
            lista.add(cursor.toUsuario())
        }

        return lista
    }

    override fun deletarUsuario(id: Int): Int {
        return database.delete(
            TABLE_USUARIO,
            "$COL_ID = ?",
            arrayOf(id.toString())
        )
    }

    private fun Usuario.toContentValues() = ContentValues().apply {
        put(COL_NOME, nome)
        put(COL_IDADE, idade)
        put(COL_DATA_NASC, dataNasc)
        put(COL_SEXO, sexo)
        put(COL_ALTURA, altura)
        put(COL_PESO, peso)
        put(COL_ATIVIDADE, nivelAtividade)
        put(COL_IMC, imc)
        put(COL_CATEGORIA, categoria)
        put(COL_TMB, tmb)
        put(COL_GASTO, gastoCaloricoDiario)
        put(COL_PESO_IDEAL, pesoIdeal)
        put(COL_FREQ_CARD, freqCard)
    }

    private fun Cursor.toUsuario() = Usuario(
        id = getInt(getColumnIndexOrThrow(COL_ID)),
        nome = getString(getColumnIndexOrThrow(COL_NOME)),
        idade = getInt(getColumnIndexOrThrow(COL_IDADE)),
        dataNasc = getString(getColumnIndexOrThrow(COL_DATA_NASC)),
        sexo = getString(getColumnIndexOrThrow(COL_SEXO)),
        altura = getDouble(getColumnIndexOrThrow(COL_ALTURA)),
        peso = getDouble(getColumnIndexOrThrow(COL_PESO)),
        nivelAtividade = getString(getColumnIndexOrThrow(COL_ATIVIDADE)),
        imc = getDouble(getColumnIndexOrThrow(COL_IMC)),
        categoria = getString(getColumnIndexOrThrow(COL_CATEGORIA)),
        tmb = getDouble(getColumnIndexOrThrow(COL_TMB)),
        gastoCaloricoDiario = getDouble(getColumnIndexOrThrow(COL_GASTO)),
        pesoIdeal = getDouble(getColumnIndexOrThrow(COL_PESO_IDEAL)),
        freqCard = getDouble(getColumnIndexOrThrow(COL_FREQ_CARD))
    )
}