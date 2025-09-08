package com.example.sampleproject.features.main.domain.model

enum class CardItems(val id: Int) {
    PURCHASE(0),
    BALANCE(1),
    CHARGE(2),
    RECEIPT(3),
    PAY_WITH_ID(4),
    OTHER(5);

    companion object {
        fun fromItem(id: Int) = entries.find { it.id == id } ?: PURCHASE
    }
}
