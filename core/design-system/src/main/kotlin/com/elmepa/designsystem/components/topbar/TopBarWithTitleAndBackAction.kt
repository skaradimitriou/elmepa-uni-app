package com.elmepa.designsystem.components.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.elmepa.designsystem.theme.ElmepaAppTheme

@Composable
fun TopBarWithTitleAndBackAction(
    title: String,
    onBackActionClick: () -> Unit
) {
    AppTopBar(
        title = title,
        navigationIcon = {
            IconButton(onClick = onBackActionClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun TopBarWithTitleAndBackActionPreview() {
    ElmepaAppTheme {
        TopBarWithTitleAndBackAction(
            title = LoremIpsum(3).values.joinToString(),
            onBackActionClick = {}
        )
    }
}
