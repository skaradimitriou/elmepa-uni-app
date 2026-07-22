package com.elmepa.students.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.findNavController
import com.elmepa.designsystem.components.list.ListItemWithIconTitleAndSubtitle
import com.elmepa.designsystem.components.shimmer.ShimmerEffect
import com.elmepa.designsystem.components.topbar.TopBarWithTitleAndBackAction
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.spacing
import com.elmepa.students.presentation.R
import com.elmepa.students.presentation.list.StudentsView.Effect
import com.elmepa.students.presentation.list.StudentsView.State
import com.elmepa.students.presentation.list.StudentsView.UIAction
import com.stathis.common.R as commonRes

private const val SHIMMER_COUNT: Int = 3

@Composable
internal fun StudentsScreen() {
    //TODO VM will be moved to comp fun once the app is migrated fully to compose
    val viewModel: StudentsViewModel = hiltViewModel()

    val state by viewModel.state.collectAsStateWithLifecycle()

    //TODO navController will be removed once nav3 is introduced to project
    val navController = LocalView.current.findNavController()

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                Effect.Back -> navController.popBackStack()
            }
        }
    }

    ElmepaAppTheme {
        Scaffold(
            topBar = {
                TopBarWithTitleAndBackAction(
                    title = stringResource(R.string.students_title),
                    onBackActionClick = { viewModel.onAction(UIAction.Back) }
                )
            },
            content = { paddingValues ->
                when (state) {
                    State.Loading -> StudentsLoadingScreen(paddingValues)
                    is State.Content -> StudentsContentScreen(paddingValues)
                    State.Error -> StudentsErrorScreen(
                        paddingValues = paddingValues,
                        onClick = viewModel::onAction
                    )
                }
            }
        )
    }
}

@Composable
private fun StudentsLoadingScreen(paddingValues: PaddingValues = PaddingValues()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(top = MaterialTheme.spacing.small)
            .padding(horizontal = MaterialTheme.spacing.small),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        repeat(SHIMMER_COUNT) {
            ShimmerEffect(
                modifier = Modifier
                    .height(MaterialTheme.spacing.xLarge)
                    .fillMaxWidth(),
                shimmerShape = RoundedCornerShape(MaterialTheme.spacing.xxLarge)
            )

            repeat(SHIMMER_COUNT) {
                ShimmerEffect(
                    modifier = Modifier
                        .height(MaterialTheme.spacing.xxxLarge)
                        .fillMaxWidth(),
                    shimmerShape = RoundedCornerShape(MaterialTheme.spacing.large)
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        }
    }
}

@Composable
private fun StudentsContentScreen(paddingValues: PaddingValues = PaddingValues()) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(scrollState)
    ) {
        repeat(SHIMMER_COUNT) {
            Text(
                modifier = Modifier.padding(all = MaterialTheme.spacing.small),
                text = "Header",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            repeat(SHIMMER_COUNT) {
                ListItemWithIconTitleAndSubtitle(
                    iconRes = commonRes.drawable.book,
                    title = "Hey there",
                    subtitle = "subtitle",
                    onAction = {
                        // will be added later on
                    }
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        }
    }
}

@Composable
private fun StudentsErrorScreen(paddingValues: PaddingValues = PaddingValues(), onClick: (UIAction) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(commonRes.string.something_went_wrong),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
        Text(
            text = stringResource(commonRes.string.info_error_text),
            style = MaterialTheme.typography.bodySmall,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

        Button(
            onClick = { onClick(UIAction.Retry) }
        ) {
            Text(text = stringResource(commonRes.string.retry))
        }
    }
}

@PreviewLightDark
@Composable
private fun StudentsScreenPreview() {
    ElmepaAppTheme {
        StudentsContentScreen(paddingValues = PaddingValues())
    }
}
