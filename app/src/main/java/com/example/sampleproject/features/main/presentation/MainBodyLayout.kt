package com.example.sampleproject.features.main.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sampleproject.R
import com.example.sampleproject.features.main.domain.model.CardItems
import com.example.sampleproject.features.main.domain.model.CardModel
import com.example.sampleproject.features.main.presentation.components.CardItem
import com.example.sampleproject.ui.icons.cardIcons.balance
import com.example.sampleproject.ui.icons.cardIcons.charge
import com.example.sampleproject.ui.icons.cardIcons.other
import com.example.sampleproject.ui.icons.cardIcons.payWithId
import com.example.sampleproject.ui.icons.cardIcons.purchase
import com.example.sampleproject.ui.icons.cardIcons.receipt

@Composable
fun MainBodyLayout(innerPadding: PaddingValues, viewModel: MainViewModel) {

    val cardMetas by viewModel.cards.collectAsState()
    val context = LocalContext.current

    val primary = MaterialTheme.colorScheme.onBackground
    val secondary = MaterialTheme.colorScheme.secondary

    val labels = listOf(
        stringResource(R.string.purchase),
        stringResource(R.string.balance),
        stringResource(R.string.charge),
        stringResource(R.string.receipt),
        stringResource(R.string.pay_with_id),
        stringResource(R.string.other),
    )

    val cardModels = remember(cardMetas, labels) {
        cardMetas.map { meta ->
            val icon = when (meta.type) {
                CardItems.PURCHASE -> purchase(primary, secondary)
                CardItems.BALANCE -> balance(primary, secondary)
                CardItems.CHARGE -> charge(primary, secondary)
                CardItems.RECEIPT -> receipt(primary, secondary)
                CardItems.PAY_WITH_ID -> payWithId(primary, secondary)
                CardItems.OTHER -> other(primary, secondary)
            }

            val label = labels[meta.type.id]

            CardModel(
                id = meta.id,
                icon = icon,
                label = label,
                onClick = {
                    Toast.makeText(context, "Clicked: $label", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    val screenWidth = with(density) {
        val fullWidthPx = LocalWindowInfo.current.containerSize.width.toFloat()
        val insets = WindowInsets.systemBars.asPaddingValues()

        val leftInset = insets.calculateLeftPadding(layoutDirection).toPx()
        val rightInset = insets.calculateRightPadding(layoutDirection).toPx()

        val safeWidthPx = fullWidthPx - leftInset - rightInset
        safeWidthPx.toDp()
    }

    val cardSize = screenWidth * 0.25f
    val spacing = (screenWidth - (cardSize * 3)) / 4

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(bottom = 24.dp) // prevent bottom cut-off
    ) {
        // Top UI section (terminal and acquire number)
        item {
            // Container with margin from sides, shadow and rounded corners
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(12.dp),
                        clip = false
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp)  // Inner padding for content inside the container
            ) {
                SelectionContainer {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(id = R.string.terminal_number))
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = stringResource(id = R.string.acquire_number))
                    }
                }
            }
        }

        // Divider
/*        item {
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
        }*/

        // Card Grid Rows
        val rows = cardModels.chunked(3)
        itemsIndexed(rows) { _, rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(spacing)
            ) {
                rowItems.forEach { item ->
                    CardItem(item = item, cardSize = cardSize)
                }
            }
        }
    }
}
