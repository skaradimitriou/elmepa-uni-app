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
import androidx.compose.ui.unit.dp
import com.elmepa.designsystem.theme.ChampagneYellow
import com.elmepa.designsystem.theme.ElmepaAppTheme

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
        Column(modifier = Modifier.padding(all = 16.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = name.uppercase(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "$percent%",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
            Spacer(Modifier.height(16.dp))
            LinearProgressIndicator(
                progress = { (percent.toFloat() / 100) },
                modifier = Modifier
                    .height(32.dp)
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
