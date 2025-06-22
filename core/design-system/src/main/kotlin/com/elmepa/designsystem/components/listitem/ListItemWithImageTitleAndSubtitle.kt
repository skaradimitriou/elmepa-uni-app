package com.elmepa.designsystem.components.listitem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.spacing

@Composable
fun ListItemWithImageTitleAndSubTitle(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon = icon,
            contentDescription = title
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
        TitleAndSubTitle(
            title = title,
            subtitle = subtitle
        )
    }
}

@Composable
private fun RowScope.Icon(icon: ImageVector, contentDescription: String) {
    Column(
        modifier = Modifier
            .padding(all = MaterialTheme.spacing.small)
            .clip(RoundedCornerShape(MaterialTheme.spacing.small))
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            modifier = Modifier
                .height(60.dp)
                .width(60.dp)
                .padding(all = MaterialTheme.spacing.medium),
            imageVector = icon,
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
    }
}

@Composable
private fun RowScope.TitleAndSubTitle(title: String, subtitle: String) {
    Column(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(1f),
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(0.5f)
        )
    }
}

@Preview
@Composable
private fun ListItemWithImageTitleAndSubTitlePreview() {
    ElmepaAppTheme {
        ListItemWithImageTitleAndSubTitle(
            modifier = Modifier.background(Color.White),
            icon = Icons.Default.Share,
            title = LoremIpsum(2).values.joinToString(),
            subtitle = LoremIpsum(5).values.joinToString(),
            onClick = {}
        )
    }
}
