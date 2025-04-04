package com.elmepa.syllabus.lessons.components

import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.spacing

@Composable
internal fun RibbonLessonCard(
    modifier: Modifier = Modifier,
    lessonName: String,
    @ColorRes color: Color,
    onClick: () -> Unit
) {
    val localDensity = LocalDensity.current
    var ribbonHeight: Dp by remember { mutableStateOf(0.dp) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {

        Row {
            Spacer(
                modifier = Modifier
                    .height(ribbonHeight)
                    .width(MaterialTheme.spacing.small)
                    .background(color)
            )
            Row(
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        with(localDensity) {
                            ribbonHeight = coordinates.size.height.toDp()
                        }
                    },
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = MaterialTheme.spacing.small)
                        .padding(vertical = MaterialTheme.spacing.medium),
                    text = lessonName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun RibbonLessonCardPreview() {
    ElmepaAppTheme {
        val lessonName = LoremIpsum(2).values.joinToString()
        RibbonLessonCard(
            lessonName = lessonName,
            color = Color.Red,
            onClick = {}
        )
    }
}
