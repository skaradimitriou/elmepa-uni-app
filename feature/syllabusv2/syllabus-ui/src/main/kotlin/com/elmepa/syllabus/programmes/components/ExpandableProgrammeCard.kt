package com.elmepa.syllabus.programmes.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.Petrol15Opacity
import com.elmepa.designsystem.theme.spacing

@Composable
internal fun ExpandableProgrammeCard(
    modifier: Modifier = Modifier,
    text: String,
    semesters: List<String>,
    onSemesterClick: (String) -> Unit
) {
    var isExpanded by rememberSaveable { mutableStateOf(true) }
    val rotationAngle by animateFloatAsState(targetValue = if (isExpanded) 90f else 0f, label = "Rotation")

    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.medium,
                    end = MaterialTheme.spacing.xSmall
                )
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            IconButton(
                onClick = { isExpanded = !isExpanded }
            ) {
                Icon(
                    modifier = Modifier.rotate(rotationAngle),
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        if (isExpanded) {
            HorizontalDivider()
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.medium),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
            ) {
                items(semesters) { semester ->
                    SemesterItem(semester, onSemesterClick)
                }
            }
        }
    }
}

@Composable
fun SemesterItem(semester: String, onSemesterClick: (String) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(MaterialTheme.spacing.small))
            .background(Petrol15Opacity)
            .clickable { onSemesterClick(semester) }
    ) {
        Text(
            modifier = Modifier.padding(all = MaterialTheme.spacing.medium),
            text = semester
        )
    }
}

@Preview
@Composable
private fun ExpandableProgrammeCardPreview() {
    ElmepaAppTheme {
        val semesters = listOf(
            "Εξάμηνο Α'",
            "Εξάμηνο Β'",
            "Εξάμηνο Γ'",
            "Εξάμηνο Δ'",
            "Εξάμηνο Ε'",
            "Εξάμηνο ΣΤ'",
            "Εξάμηνο Ζ'",
            "Εξάμηνο Η'",
        )
        ExpandableProgrammeCard(
            text = "Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής",
            semesters = semesters,
            onSemesterClick = {}
        )
    }
}
