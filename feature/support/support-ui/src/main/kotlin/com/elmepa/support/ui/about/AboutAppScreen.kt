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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.findNavController
import com.elmepa.designsystem.components.cards.InformativeCard
import com.elmepa.designsystem.components.cards.TimelineCard
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.Petrol
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
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.about_app_title),
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
                        IconButton(
                            onClick = { navController.popBackStack() }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                                contentDescription = null
                            )
                        }
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
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.background),
        contentPadding = paddingValues
    ) {
        item {
            InformativeCard(modifier = Modifier.padding(vertical = 16.dp)) {
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
    Spacer(Modifier.height(16.dp))
    Text(
        text = newsPublisher,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold
    )
    Spacer(Modifier.height(4.dp))
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
