package com.elmepa.designsystem.components.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.elmepa.designsystem.theme.ElmepaAppTheme

@Composable
fun TopBarWithTitle(title: String) {
    AppTopBar(title = title)
}

@PreviewLightDark
@Composable
private fun TopBarWithTitlePreview() {
    ElmepaAppTheme {
        TopBarWithTitle(title = LoremIpsum(3).values.joinToString())
    }
}
