package com.example.sampleproject.features.main.data.mapper

import com.example.sampleproject.features.main.domain.model.CardItems
import com.example.sampleproject.features.main.domain.model.CardMetadata

object CardItemsMapper {
    fun allItems(): List<CardMetadata> = listOf(
        CardMetadata(0, CardItems.PURCHASE),
        CardMetadata(1, CardItems.BALANCE),
        CardMetadata(2, CardItems.CHARGE),
        CardMetadata(3, CardItems.RECEIPT),
        CardMetadata(4, CardItems.PAY_WITH_ID),
        CardMetadata(5, CardItems.OTHER),
        CardMetadata(4, CardItems.PAY_WITH_ID),
        CardMetadata(4, CardItems.PAY_WITH_ID),
        CardMetadata(4, CardItems.PAY_WITH_ID),
        CardMetadata(4, CardItems.PAY_WITH_ID),
        CardMetadata(4, CardItems.PAY_WITH_ID),
        CardMetadata(4, CardItems.PAY_WITH_ID),
        CardMetadata(4, CardItems.PAY_WITH_ID),
        CardMetadata(4, CardItems.PAY_WITH_ID),
        CardMetadata(4, CardItems.PAY_WITH_ID),
        CardMetadata(4, CardItems.PAY_WITH_ID),
        CardMetadata(4, CardItems.PAY_WITH_ID),
    )
}
