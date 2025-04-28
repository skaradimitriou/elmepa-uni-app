package com.elmepa.support.ui.about

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.findNavController
import com.elmepa.designsystem.components.cards.InformativeCard
import com.elmepa.designsystem.components.cards.TimelineCard
import com.elmepa.designsystem.components.topbar.TopBarWithTitleAndBackAction
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.spacing
import com.stathis.common.R
import com.stathis.model.about.AboutAppCard

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutAppScreen() {
    val navController = LocalView.current.findNavController()

    ElmepaAppTheme {
        Scaffold(
            topBar = {
                TopBarWithTitleAndBackAction(
                    title = stringResource(R.string.about_app_title),
                    onBackActionClick = {
                        navController.popBackStack()
                    }
                )
            },
            content = { paddingValues ->
                AboutAppContent(
                    paddingValues = paddingValues,
                    info = getAboutAppInfo()
                )
            }
        )
    }
}

@Composable
internal fun AboutAppContent(paddingValues: PaddingValues, info: List<AboutAppCard>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MaterialTheme.spacing.medium)
            .background(MaterialTheme.colorScheme.background),
        contentPadding = paddingValues
    ) {
        item {
            InformativeCard(modifier = Modifier.padding(vertical = MaterialTheme.spacing.medium)) {
                InformativeCardContent(
                    infoText = stringResource(R.string.about_app_data),
                    newsPublisher = stringResource(R.string.news_publisher_header),
                    contactInfo = stringResource(R.string.news_publisher_desc)
                )
            }
        }

        items(info, key = { it.date }) { data ->
            TimelineCard(
                title = data.date,
                description = data.description
            )
        }
    }
}

@Composable
private fun InformativeCardContent(infoText: String, newsPublisher: String, contactInfo: String) {
    Text(
        text = infoText,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Justify,
        color = MaterialTheme.colorScheme.onSurface
    )
    Spacer(Modifier.height(MaterialTheme.spacing.medium))
    Text(
        text = newsPublisher,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold
    )
    Spacer(Modifier.height(MaterialTheme.spacing.xSmall))
    Text(
        text = contactInfo,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Preview
@Composable
private fun AboutAppScreenPreview() {
    ElmepaAppTheme {
        AboutAppScreen()
    }
}

@Composable
private fun getAboutAppInfo() = listOf(
    AboutAppCard(
        date = stringResource(R.string.new_version_date),
        description = stringResource(R.string.new_version_desc),
    ),
    AboutAppCard(
        date = stringResource(R.string.third_version_date),
        description = stringResource(R.string.third_version_desc)
    ),
    AboutAppCard(
        date = stringResource(R.string.sec_version_date),
        description = stringResource(R.string.sec_version_desc)
    ),
    AboutAppCard(
        date = stringResource(R.string.first_version_date),
        description = stringResource(R.string.first_version_desc)
    )
)
