package com.example.conversormoeda.data.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException
import com.example.conversormoeda.data.db.DBHelper
import com.example.conversormoeda.model.moedas

class CarteiraDAO(contexto: Context) {
    private val auxiliar = DBHelper(contexto)
    fun consultarSaldos(): Map<String, Double> {
        val banco = auxiliar.readableDatabase
        val saldos = mutableMapOf<String, Double>()
        banco.query(DBHelper.NOME_TABELA, null, "id = 1", null, null, null, null)
            .use { cursor ->
                if (!cursor.moveToFirst()) {
                    throw SQLiteException("Carteira não encontrada")
                }
                for (moeda in moedas) {
                    val coluna = cursor.getColumnIndexOrThrow(moeda.codigo)
                    saldos[moeda.codigo] = cursor.getDouble(coluna)
                }
            }
        return saldos
    }

    fun salvarSaldos(saldos: Map<String, Double>) {
        val valores = ContentValues().apply {
            for ((codigo, saldo) in saldos) {
                put(codigo, saldo)
            }
        }
        val banco = auxiliar.writableDatabase
        val linhas = banco.update(DBHelper.NOME_TABELA, valores, "id = 1", null)
        if (linhas != 1) throw SQLiteException("Carteira não atualizada")
    }

    fun fechar() {
        auxiliar.close()
    }
}
