package com.example.conversormoeda.model
import java.util.Locale
data class Moeda(
    val codigo: String,
    val nome: String,
    val simbolo: String,
    val casas: Int
)

val moedas = listOf(
    Moeda("BRL", "Real", "R$", 2),
    Moeda("USD", "Dólar", "\$", 2),
    Moeda("EUR", "Euro", "€", 2),
    Moeda("BTC", "Bitcoin", "BTC", 8),
    Moeda("ETH", "Ethereum", "ETH", 8)
)

val real = moedas[0]
fun lerValor(texto: String, moeda: Moeda): Double? {
    val entrada = texto.trim()
    val formato = Regex("[0-9]+([.,][0-9]{1,${moeda.casas}})?")
    if (!entrada.matches(formato)) return null

    val valor = entrada.replace(',', '.').toDoubleOrNull() ?: return null
    return if (valor.isFinite() && valor > 0.0) valor else null
}

fun arredondarValor(valor: Double, moeda: Moeda): Double {
    return String.format(Locale.US, "%.${moeda.casas}f", valor).toDouble()
}

fun formatarValor(valor: Double, moeda: Moeda): String {
    val numero = String.format(Locale.forLanguageTag("pt-BR"), "%,.${moeda.casas}f", valor)
    return "${moeda.simbolo} $numero"
}