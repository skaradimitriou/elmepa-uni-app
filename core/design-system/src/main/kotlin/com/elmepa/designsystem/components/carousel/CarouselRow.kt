package com.elmepa.designsystem.components.carousel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.spacing
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun CarouselRow(
    modifier: Modifier = Modifier,
    carouselItems: ImmutableList<CarouselDisplayItem>,
    onClick: (CarouselDisplayItem) -> Unit
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(MaterialTheme.spacing.small),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        items(carouselItems) { item ->
            CarouselEntry(
                title = item.title,
                image = item.image,
                onClick = { onClick(item) }
            )
        }
    }
}

@Composable
private fun CarouselEntry(
    image: String,
    title: String,
    onClick: () -> Unit
) {
    val screenWidth = LocalWindowInfo.current.containerSize.width.dp

    Card(
        modifier = Modifier
            .width(screenWidth)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillBounds,
                model = image,
                contentDescription = title
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp) // Height of the gradient overlay
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f))
                        )
                    )
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }

}

@Composable
@Preview
private fun CarouselRowPreview() {
    ElmepaAppTheme {
        val items = persistentListOf(
            CarouselDisplayItem(
                image = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/32/Googleplex_HQ_%28cropped%29.jpg/1200px-Googleplex_HQ_%28cropped%29.jpg",
                title = "My title",
            ), CarouselDisplayItem(
                image = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/32/Googleplex_HQ_%28cropped%29.jpg/1200px-Googleplex_HQ_%28cropped%29.jpg",
                title = "My title",
            ), CarouselDisplayItem(
                image = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/32/Googleplex_HQ_%28cropped%29.jpg/1200px-Googleplex_HQ_%28cropped%29.jpg",
                title = "My title",
            ), CarouselDisplayItem(
                image = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/32/Googleplex_HQ_%28cropped%29.jpg/1200px-Googleplex_HQ_%28cropped%29.jpg",
                title = "My title",
            ), CarouselDisplayItem(
                image = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/32/Googleplex_HQ_%28cropped%29.jpg/1200px-Googleplex_HQ_%28cropped%29.jpg",
                title = "My title",
            ), CarouselDisplayItem(
                image = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/32/Googleplex_HQ_%28cropped%29.jpg/1200px-Googleplex_HQ_%28cropped%29.jpg",
                title = "My title",
            )
        )
        CarouselRow(
            carouselItems = items,
            onClick = {}
        )
    }
}
