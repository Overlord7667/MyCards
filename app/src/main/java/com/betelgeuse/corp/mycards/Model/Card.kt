package com.betelgeuse.corp.mycards.Model

data class Card (
    var quantityPoints: String,
    var cashBackProcent: String,
    var levelPerson: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Card

        if (quantityPoints != other.quantityPoints) return false
        if (cashBackProcent != other.cashBackProcent) return false
        if (levelPerson != other.levelPerson) return false

        return true
    }

    override fun hashCode(): Int {
        var result = quantityPoints.hashCode()
        result = 31 * result + cashBackProcent.hashCode()
        result = 31 * result + levelPerson.hashCode()
        return result
    }
}