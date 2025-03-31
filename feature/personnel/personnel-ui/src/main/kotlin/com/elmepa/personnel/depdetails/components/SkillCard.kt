package com.elmepa.personnel.depdetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.elmepa.designsystem.theme.ChampagneYellow
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.spacing

@Composable
internal fun SkillCard(
    modifier: Modifier = Modifier,
    name: String,
    percent: Int
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = name.uppercase(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
                Text(
                    text = "$percent%",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
            Spacer(Modifier.height(MaterialTheme.spacing.medium))
            LinearProgressIndicator(
                progress = { (percent.toFloat() / 100) },
                modifier = Modifier
                    .height(MaterialTheme.spacing.xLarge)
                    .fillMaxWidth(),
                color = ChampagneYellow,
                trackColor = MaterialTheme.colorScheme.background,
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun SkillCardPreview() {
    ElmepaAppTheme {
        SkillCard(
            name = "Business Intelligence",
            percent = 100
        )
    }
}
