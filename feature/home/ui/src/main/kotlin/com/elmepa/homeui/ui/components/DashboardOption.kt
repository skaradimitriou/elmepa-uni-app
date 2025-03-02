package com.elmepa.homeui.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elmepa.designsystem.components.cards.CardWithImageAndTitle
import com.stathis.common.R

@Composable
internal fun DashboardOption(
    imageRes: Int,
    title: String,
    onAction: () -> Unit
) {
    CardWithImageAndTitle(
        modifier = Modifier
            .height(180.dp)
            .clickable { onAction() },
        title = title,
        image = imageRes,
        onClick = onAction
    )
}

@Preview(showBackground = false)
@Composable
internal fun DashboardOptionPreview() {
    DashboardOption(
        imageRes = R.drawable.home_faq,
        title = stringResource(R.string.faq_title),
        onAction = {}
    )
}
