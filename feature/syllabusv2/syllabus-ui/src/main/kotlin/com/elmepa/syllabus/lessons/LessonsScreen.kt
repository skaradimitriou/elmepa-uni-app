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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.elmepa.designsystem.components.cards.InformativeCard
import com.elmepa.designsystem.components.shimmer.ShimmerEffect
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.spacing
import com.elmepa.syllabus.lessons.components.RibbonLessonCard
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

private const val SHIMMER_ITEMS_COUNT = 8
private val SHIMMER_ITEM_LOADING = 80.dp
private val SHIMMER_BOTTOM_PADDING = 40.dp

@Composable
internal fun LessonsScreen(state: LessonsView.State, onClick: (LessonsView.UIAction) -> Unit) {
    Scaffold(
        topBar = {
            //
        },
        content = { paddingValues ->
            when (state) {
                is LessonsView.State.Loading -> ShimmerLoading(paddingValues)
                is LessonsView.State.Content -> LessonsScreenContent(
                    paddingValues = paddingValues,
                    lessons = state.lessons,
                    onClick = onClick
                )

                is LessonsView.State.Error -> Unit
            }
        }
    )
}

@Composable
private fun LessonsScreenContent(
    paddingValues: PaddingValues,
    lessons: ImmutableList<String>,
    onClick: (LessonsView.UIAction) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .consumeWindowInsets(paddingValues)
            .padding(MaterialTheme.spacing.small)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        item {
            InformativeCard {
                //FIXME: This will come from VM later on
                Text(
                    text = "Όλα τα μαθήματα είναι υποχρεωτικά σε αυτό το εξάμηνο.",
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        }

        items(lessons) { lesson ->
            RibbonLessonCard(
                lessonName = lesson,
                color = Color.Red,
                onClick = { onClick(LessonsView.UIAction.LessonTap(lesson)) }
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
        LessonsView.State.Loading,
        LessonsView.State.Content(
            lessons = persistentListOf(
                "Εισαγωγή στην Οικονιμική Θεωρία",
                "Μαθηματική Ανάλυση",
                "Οργάνωση και Διοίκηση Επιχειρήσεων"
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
            onClick = {}
        )
    }
}
