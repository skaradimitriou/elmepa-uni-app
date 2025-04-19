package com.elmepa.syllabus.lessons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.navigation.findNavController
import com.elmepa.designsystem.components.cards.InformativeCard
import com.elmepa.designsystem.components.shimmer.ShimmerEffect
import com.elmepa.designsystem.components.topbar.TopBarWithTitleAndBackAndCustomAction
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.spacing
import com.elmepa.syllabus.lessons.components.RibbonLessonCard
import com.elmepa.syllabus.ui.R
import com.stathis.model.syllabus.Lesson
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import com.stathis.common.R as commonR

private const val SHIMMER_ITEMS_COUNT = 8
private val SHIMMER_ITEM_LOADING = 80.dp
private val SHIMMER_BOTTOM_PADDING = 40.dp

@Composable
internal fun LessonsScreen(state: LessonsView.State, onAction: (LessonsView.UIAction) -> Unit) {
    val navController = LocalView.current.findNavController()
    var showModal by remember { mutableStateOf(false) }

    if (showModal) {
        LessonScreenModal(
            onDismiss = { showModal = false }
        )
    }

    Scaffold(
        topBar = {
            val title = when (state) {
                is LessonsView.State.Loading -> state.semester
                is LessonsView.State.Content -> state.semester
                else -> null
            }

            LessonsToolbar(
                title = title.orEmpty(),
                onBackActionClick = { navController.navigateUp() },
                onInfoIconClick = { showModal = true }
            )
        },
        content = { paddingValues ->
            when (state) {
                is LessonsView.State.Loading -> ShimmerLoading(paddingValues)
                is LessonsView.State.Content -> LessonsScreenContent(
                    paddingValues = paddingValues,
                    informativeText = state.informativeText,
                    lessons = state.lessons,
                    onAction = onAction
                )

                else -> Unit
            }
        }
    )
}

@Composable
private fun LessonsToolbar(
    title: String,
    onBackActionClick: () -> Unit,
    onInfoIconClick: () -> Unit
) {
    TopBarWithTitleAndBackAndCustomAction(
        title = title,
        onBackActionClick = onBackActionClick,
        actions = {
            IconButton(onClick = onInfoIconClick) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
private fun LessonScreenModal(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = stringResource(R.string.modal_title)) },
        text = { Text(text = stringResource(R.string.modal_body)) },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(R.string.modal_btn_txt))
            }
        }
    )
}

@Composable
private fun LessonsScreenContent(
    paddingValues: PaddingValues,
    informativeText: String,
    lessons: ImmutableList<Lesson>,
    onAction: (LessonsView.UIAction) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .consumeWindowInsets(paddingValues)
            .padding(top = paddingValues.calculateTopPadding())
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        contentPadding = PaddingValues(all = MaterialTheme.spacing.small)
    ) {
        item {
            InformativeCard {
                Text(text = informativeText, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        }

        items(lessons) { lesson ->
            val ribbonColor = if (lesson.mandatory) commonR.color.lesson_blue else commonR.color.lesson_orange

            RibbonLessonCard(
                lessonName = lesson.name,
                color = colorResource(ribbonColor),
                onClick = { onAction(LessonsView.UIAction.LessonTap(lesson.name)) }
            )
        }
    }
}

@Composable
private fun ShimmerLoading(paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .consumeWindowInsets(paddingValues)
            .padding(top = MaterialTheme.spacing.small)
            .padding(horizontal = MaterialTheme.spacing.small),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        items(count = SHIMMER_ITEMS_COUNT) {
            ShimmerEffect(
                modifier = Modifier
                    .height(SHIMMER_ITEM_LOADING)
                    .fillMaxWidth()
            )
        }

        item {
            Spacer(modifier = Modifier.height(SHIMMER_BOTTOM_PADDING))
        }
    }
}

private class LessonsScreenProvider : PreviewParameterProvider<LessonsView.State> {

    override val values = sequenceOf(
        LessonsView.State.Loading(semester = LoremIpsum(2).values.joinToString()),
        LessonsView.State.Content(
            semester = LoremIpsum(2).values.joinToString(),
            informativeText = LoremIpsum(5).values.joinToString(),
            lessons = persistentListOf(
                Lesson(
                    name = "Εισαγωγή στην Οικονομική Θεωρία",
                    description = LoremIpsum(5).values.joinToString(),
                    hours = "5",
                    mandatory = false,
                    orientation = listOf(),
                    semester = "A",
                    ects = "5"
                )
            )
        ),
        LessonsView.State.Error
    )
}

@PreviewLightDark
@Composable
private fun UserProfilePreview(@PreviewParameter(LessonsScreenProvider::class) state: LessonsView.State) {
    ElmepaAppTheme {
        LessonsScreen(
            state = state,
            onAction = {}
        )
    }
}
