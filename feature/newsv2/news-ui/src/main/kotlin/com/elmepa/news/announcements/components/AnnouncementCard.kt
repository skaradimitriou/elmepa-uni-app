package com.elmepa.news.announcements.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.Petrol
import com.elmepa.designsystem.theme.TextGrey
import com.elmepa.designsystem.theme.spacing
import com.stathis.common.R as common

@Composable
internal fun AnnouncementCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    datePublished: String,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Row(modifier = Modifier.padding(all = MaterialTheme.spacing.medium)) {
            AsyncImage(
                modifier = Modifier
                    .height(200.dp)
                    .width(120.dp),
                model = imageUrl,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(common.drawable.placeholder),
                error = painterResource(common.drawable.placeholder),
                contentDescription = title
            )

            Column(modifier = Modifier.padding(all = MaterialTheme.spacing.medium)) {
                Text(
                    text = datePublished,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = Petrol
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextGrey,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun AnnouncementCardPreview() {
    ElmepaAppTheme {
        AnnouncementCard(
            imageUrl = "https://mst.hmu.gr/wp-content/uploads/2021/04/mst-enimerosi.jpg",
            datePublished = "Απρ 30, 2025",
            title = LoremIpsum(4).values.joinToString(),
            subtitle = LoremIpsum(15).values.joinToString(),
            onClick = {}
        )
    }
}
