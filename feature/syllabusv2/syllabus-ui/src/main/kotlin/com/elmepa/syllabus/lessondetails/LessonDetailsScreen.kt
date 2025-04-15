package com.elmepa.syllabus.lessondetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.Petrol
import com.elmepa.designsystem.theme.Petrol15Opacity
import com.elmepa.designsystem.theme.spacing
import com.stathis.common.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LessonDetailsScreen(state: LessonDetailsView.State, onAction: (LessonDetailsView.UIAction) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.lesson_information),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                colors = TopAppBarColors(
                    containerColor = Petrol,
                    scrolledContainerColor = Color.Red,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { onAction(LessonDetailsView.UIAction.GoBack) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            when (state) {
                is LessonDetailsView.State.Loading -> Unit
                is LessonDetailsView.State.Content -> LessonDetailsContent(
                    paddingValues = paddingValues,
                    lessonName = state.lessonName,
                    commitment = state.commitment,
                    credits = state.credits,
                    lessonDescription = state.description,
                )

                is LessonDetailsView.State.Error -> Unit
            }
        }
    )
}

@Composable
private fun LessonDetailsContent(
    paddingValues: PaddingValues,
    lessonName: String,
    commitment: String,
    credits: Int,
    lessonDescription: AnnotatedString
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .padding(top = MaterialTheme.spacing.small)
            .padding(horizontal = MaterialTheme.spacing.small)
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState),
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(all = MaterialTheme.spacing.medium)) {
                BasicLessonDetails(
                    lessonName = lessonName,
                    commitment = commitment,
                    credits = credits
                )
                Spacer(Modifier.height(MaterialTheme.spacing.xLarge))
                Text(
                    text = lessonDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Composable
private fun BasicLessonDetails(lessonName: String, commitment: String, credits: Int) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Icon(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Petrol15Opacity),
            painter = painterResource(R.drawable.book),
            tint = Color.Unspecified,
            contentDescription = null
        )
        Spacer(Modifier.width(MaterialTheme.spacing.medium))
        Column {
            Text(
                text = lessonName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(MaterialTheme.spacing.medium))
            Text(
                text = commitment,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stringResource(R.string.lesson_ects, credits),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun LessonDetailsScreenPreview() {
    ElmepaAppTheme {
        val state = LessonDetailsView.State.Content(
            lessonName = "Εισαγωγή στην Πληροφορική",
            commitment = "ΘΕΩΡΙΑ: 4 ώρες",
            credits = 5,
            lessonDescription = LoremIpsum(30).values.joinToString()
        )

        LessonDetailsScreen(state = state, onAction = {})
    }
}
