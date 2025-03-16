package com.elmepa.designsystem.components.cards

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun TimelineCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
) {
    var cardHeight: Dp by remember { mutableStateOf(0.dp) }
    val localDensity = LocalDensity.current

    Row(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            VerticalDivider(
                modifier = Modifier.height(16.dp),
                thickness = 3.dp,
                color = Color.LightGray
            )
            Canvas(modifier = Modifier.size(16.dp)) {
                drawCircle(color = Color.LightGray)
            }

            VerticalDivider(
                modifier = Modifier.height(cardHeight + 16.dp),
                thickness = 3.dp,
                color = Color.LightGray
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            MainContent(
                title = title,
                description = description,
                onGloballyPositioned = { intSize ->
                    with(localDensity) {
                        cardHeight = intSize.height.toDp()
                    }
                }
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun MainContent(title: String, description: String, onGloballyPositioned: (IntSize) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(all = 16.dp)
            .onGloballyPositioned { coordinates ->
                onGloballyPositioned(coordinates.size)
            }
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Preview
@Composable
fun TimelineScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        TimelineCard(
            title = LoremIpsum(3).values.joinToString(),
            description = LoremIpsum(15).values.joinToString()
        )
    }
}
