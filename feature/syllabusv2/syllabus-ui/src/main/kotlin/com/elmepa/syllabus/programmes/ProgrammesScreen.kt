package com.elmepa.syllabus.programmes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.elmepa.designsystem.components.topbar.TopBarWithTitle
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.spacing
import com.elmepa.syllabus.programmes.ProgrammesView.State
import com.elmepa.syllabus.programmes.ProgrammesView.UIAction
import com.elmepa.syllabus.programmes.components.ExpandableProgrammeCard
import com.elmepa.syllabus.ui.R
import com.stathis.model.syllabus.OrientationType
import com.stathis.model.syllabus.Programme
import com.stathis.model.syllabus.ProgrammeType
import com.stathis.model.syllabus.Semester

@Composable
internal fun ProgrammesScreen(state: State, onAction: (UIAction) -> Unit) {
    Scaffold(
        topBar = {
            TopBarWithTitle(title = stringResource(com.stathis.common.R.string.syllabus))
        },
        content = { paddingValues ->
            when (state) {
                is State.Loading -> Unit
                is State.Content -> ProgrammesScreenContent(
                    paddingValues = paddingValues,
                    selectedTabPosition = state.selectedTabPosition,
                    programmes = state.programmes,
                    onAction = onAction
                )

                is State.Error -> Unit
            }
        }
    )
}

@Composable
private fun ProgrammesScreenContent(
    selectedTabPosition: Int? = null,
    programmes: List<Programme>,
    paddingValues: PaddingValues,
    onAction: (UIAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .consumeWindowInsets(paddingValues)
            .padding(top = paddingValues.calculateTopPadding())
            .background(MaterialTheme.colorScheme.background)
    ) {
        TabRow(
            selectedTabPosition = selectedTabPosition,
            onAction = onAction
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            contentPadding = PaddingValues(vertical = MaterialTheme.spacing.medium)
        ) {

            items(programmes) { programme ->
                val semesters = programme.semesters.map { it.name }

                ExpandableProgrammeCard(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
                    programme = programme.title,
                    isExpanded = programme.isExpanded,
                    semesters = semesters,
                    onSemesterClick = { semester -> onAction(UIAction.OnSemesterClick(semester)) }
                )
            }
        }
    }
}

@Composable
private fun TabRow(selectedTabPosition: Int? = null, onAction: (UIAction) -> Unit) {
    val tabTitles = listOf(
        stringResource(R.string.undergraduate_syllabus),
        stringResource(R.string.postgraduate_syllabus)
    )

    var selectedTabIndex by remember { mutableIntStateOf(selectedTabPosition ?: 0) }

    TabRow(selectedTabIndex = selectedTabIndex) {
        tabTitles.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    onAction(UIAction.OnTabSelection(selectedTabIndex))
                },
                text = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun ProgrammesScreenPreview() {
    ElmepaAppTheme {
        val state = State.Content(
            programmes = listOf(
                Programme(
                    title = LoremIpsum(3).values.joinToString(),
                    type = ProgrammeType.UNDERGRADUATE_MST,
                    orientationType = OrientationType.DATA,
                    semesters = listOf(
                        Semester(name = "Εξάμηνο Α'"),
                        Semester(name = "Εξάμηνο Β'"),
                        Semester(name = "Εξάμηνο Γ'"),
                        Semester(name = "Εξάμηνο Δ'"),
                        Semester(name = "Εξάμηνο Ε'"),
                        Semester(name = "Εξάμηνο ΣΤ'"),
                        Semester(name = "Εξάμηνο Η'"),
                        Semester(name = "Εξάμηνο Ζ'")
                    )
                )
            )
        )
        ProgrammesScreen(
            state = state,
            onAction = {}
        )
    }
}
