package com.elmepa.designsystem.components.topbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.elmepa.designsystem.theme.ElmepaAppTheme

@Composable
fun TopBarWithTitleAndBackAndCustomAction(
    title: String,
    onBackActionClick: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
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
        },
        actions = actions
    )
}

@PreviewLightDark
@Composable
private fun TopBarWithTitleAndBackAndCustomActionPreview() {
    ElmepaAppTheme {
        TopBarWithTitleAndBackAndCustomAction(
            title = LoremIpsum(3).values.joinToString(),
            onBackActionClick = {},
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = null
                    )
                }
            }
        )
    }
}
