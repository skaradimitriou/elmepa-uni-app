package com.elmepa.designsystem.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.elmepa.designsystem.theme.ElmepaAppTheme

@Composable
fun CardWithTitleAndSubtitle(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    onAction: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onAction() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(
            modifier = Modifier
                .padding(all = 16.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(1f),
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(16.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview
@Composable
private fun CardWithTitleAndSubtitlePreview() {
    ElmepaAppTheme {
        CardWithTitleAndSubtitle(
            title = LoremIpsum(5).values.joinToString(),
            subtitle = LoremIpsum(15).values.joinToString(),
            onAction = {}
        )
    }
}
