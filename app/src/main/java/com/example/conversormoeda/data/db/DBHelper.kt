package com.example.conversormoeda.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(contexto: Context) :
    SQLiteOpenHelper(contexto, NOME_BANCO, null, VERSAO_BANCO) {
    companion object {
        const val NOME_BANCO = "carteira.db"
        const val VERSAO_BANCO = 1
        const val NOME_TABELA = "carteira"
    }
    override fun onCreate(banco: SQLiteDatabase) {
        val criarTabela = """
            CREATE TABLE $NOME_TABELA (
                id INTEGER PRIMARY KEY,
                BRL REAL NOT NULL DEFAULT 0,
                USD REAL NOT NULL DEFAULT 0,
                EUR REAL NOT NULL DEFAULT 0,
                BTC REAL NOT NULL DEFAULT 0,
                ETH REAL NOT NULL DEFAULT 0
            )
        """.trimIndent()
        banco.execSQL(criarTabela)
        banco.execSQL("INSERT INTO $NOME_TABELA (id) VALUES (1)")
    }

    override fun onUpgrade(
        banco: SQLiteDatabase,
        versaoAntiga: Int,
        versaoNova: Int
    ) {
    }
}
