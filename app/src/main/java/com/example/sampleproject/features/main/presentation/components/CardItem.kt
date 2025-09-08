package com.example.sampleproject.features.main.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sampleproject.features.main.domain.model.CardModel

@Composable
fun CardItem(item: CardModel, cardSize: Dp) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .size(cardSize)
                .clip(MaterialTheme.shapes.medium)
                .clickable(
                    onClick = item.onClick,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple()
                ),
            shape = MaterialTheme.shapes.medium,
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = rememberVectorPainter(item.icon),
                    contentDescription = item.label,
                    modifier = Modifier.size(cardSize / 3)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.label,
            maxLines = Int.MAX_VALUE,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .width(cardSize)       // Limit width to card width
                .align(Alignment.CenterHorizontally)
        )
    }
}
