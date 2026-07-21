package com.elmepa.designsystem.components.list

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.Petrol
import com.elmepa.designsystem.theme.Petrol35Opacity
import com.elmepa.designsystem.theme.spacing

private const val DUMMY_TITLE_WORDS: Int = 5
private const val DUMMY_SUBTITLE_WORDS: Int = 5
private const val MAX_DESCRIPTION_LINES: Int = 3

@Composable
fun ListItemWithIconTitleAndSubtitle(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    title: String,
    subtitle: String,
    onAction: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onAction() },
    ) {
        Row {
            ListItemIcon(iconRes = iconRes)
            ListItemText(title, subtitle)
        }
    }
}

@Composable
fun ListItemIcon(
    @DrawableRes iconRes: Int,
    contentDescription: String? = null
) {
    Column(
        modifier = Modifier
            .padding(top = MaterialTheme.spacing.small)
            .padding(start = MaterialTheme.spacing.small)
            .size(MaterialTheme.spacing.xxLarge)
            .background(color = Petrol35Opacity, shape = CircleShape),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.padding(all = MaterialTheme.spacing.xSmall),
            painter = painterResource(iconRes),
            tint = Petrol,
            contentDescription = contentDescription
        )
    }
}

@Composable
private fun ListItemText(title: String, subtitle: String) {
    Column(
        modifier = Modifier.padding(MaterialTheme.spacing.small)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(1f),
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(MaterialTheme.spacing.small))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = MAX_DESCRIPTION_LINES,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
private fun ListItemWithIconTitleAndSubtitlePreview() {
    ElmepaAppTheme(darkTheme = true) {
        ListItemWithIconTitleAndSubtitle(
            iconRes = com.stathis.common.R.drawable.application_forms,
            title = LoremIpsum(DUMMY_TITLE_WORDS).values.joinToString(),
            subtitle = LoremIpsum(DUMMY_SUBTITLE_WORDS).values.joinToString(),
            onAction = {}
        )
    }
}
