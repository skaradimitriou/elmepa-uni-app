package com.elmepa.syllabus.programmes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.spacing
import com.elmepa.syllabus.programmes.ProgrammesView.State
import com.elmepa.syllabus.programmes.components.ExpandableProgrammeCard

@Composable
internal fun ProgrammesScreen(state: State) {
    Scaffold(
        topBar = {
            //
        },
        content = { paddingValues ->
            when (state) {
                is State.Loading -> Unit
                is State.Content -> ProgrammesScreenContent(
                    paddingValues = paddingValues
                )

                is State.Error -> Unit
            }
        }
    )
}

@Composable
private fun ProgrammesScreenContent(paddingValues: PaddingValues) {
    val tabTitles = listOf("Tab One", "Tab Two")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .consumeWindowInsets(paddingValues)
            .background(MaterialTheme.colorScheme.background)
    ) {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) }
                )
            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            val programmes = listOf("Programme One", "Programme Two", "Programme Three")
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

            items(programmes) { programme ->
                ExpandableProgrammeCard(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
                    programme = programme,
                    semesters = semesters,
                    onSemesterClick = {}
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun ProgrammesScreenPreview() {
    ElmepaAppTheme {
        ProgrammesScreen(state = State.Content)
    }
}
