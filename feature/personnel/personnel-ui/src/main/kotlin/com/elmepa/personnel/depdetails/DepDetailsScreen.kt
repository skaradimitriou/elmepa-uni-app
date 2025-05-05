package com.elmepa.personnel.depdetails

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.elmepa.designsystem.components.cards.CardWithPrompt
import com.elmepa.designsystem.components.topbar.TopBarWithTitleAndBackAction
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.spacing
import com.elmepa.personnel.depdetails.components.PersonalOverviewCard
import com.elmepa.personnel.depdetails.components.SkillCard
import com.elmepa.personnel.ui.R
import com.stathis.model.common.Link
import com.stathis.model.common.LinkType
import com.stathis.model.department.DepMember
import com.stathis.model.department.Skill

@Composable
internal fun DepDetailsScreen(state: DepDetailsView.State, onAction: (DepDetailsView.UIAction) -> Unit) {
    Scaffold(
        topBar = {
            TopBarWithTitleAndBackAction(
                title = stringResource(R.string.dep_details_screen_title),
                onBackActionClick = {

                }
            )
        },
        content = { paddingValues ->
            when (state) {
                is DepDetailsView.State.Loading -> Unit
                is DepDetailsView.State.Content -> DepDetailsContent(
                    paddingValues = paddingValues,
                    depMember = state.depMember,
                    onAction = onAction
                )
            }
        }
    )
}

@Composable
private fun DepDetailsContent(
    paddingValues: PaddingValues,
    depMember: DepMember,
    onAction: (DepDetailsView.UIAction) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(
            top = 10.dp,
            start = 10.dp,
            end = 10.dp,
            bottom = 40.dp
        ),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        item {
            PersonalOverviewCard(
                imageUrl = depMember.image,
                fullName = depMember.fullName,
                jobTitle = depMember.profession,
                description = depMember.description
            )
        }

        header(title = R.string.sectors)
        items(items = depMember.skills, key = { it.title }) { skill ->
            SkillCard(
                name = skill.title,
                percent = skill.value
            )
        }

        header(title = R.string.links)
        items(items = depMember.links, key = { it.openUrl }) { link ->
            CardWithPrompt(
                text = link.title,
                onClick = { onAction(DepDetailsView.UIAction.OpenLink(link)) }
            )
        }
    }
}

private fun LazyListScope.header(@StringRes title: Int) {
    item {
        Spacer(Modifier.height(MaterialTheme.spacing.large))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(MaterialTheme.spacing.small))
    }
}

@PreviewLightDark
@Composable
private fun DepDetailsScreenPreview() {
    val skills = listOf(
        Skill(
            title = "Skill #1",
            value = 100
        ),
        Skill(
            title = "Skill #2",
            value = 33
        ),
        Skill(
            title = "Skill #3",
            value = 50
        )
    )

    val links = listOf(
        Link(
            title = "Resume",
            openUrl = "link.com",
            type = LinkType.CV
        ),
        Link(
            title = "E-mail",
            openUrl = "email@domain.com",
            type = LinkType.MAIL
        ),
        Link(
            title = "Linkedin",
            openUrl = "link.com",
            type = LinkType.LINKEDIN
        ),
        Link(
            title = "Google Scholar",
            openUrl = "link.com",
            type = LinkType.GOOGLE_SCHOLAR
        ),
    )
    ElmepaAppTheme {
        DepDetailsScreen(
            state = DepDetailsView.State.Content(
                depMember = DepMember(
                    image = "https://firebasestorage.googleapis.com/v0/b/elmepa-univ-app.appspot.com/o/professors%2Fgvasileiadis.png?alt=media&token=b3518f7c-d952-4284-84f8-75d5f719e14f",
                    fullName = "Test Testopoulos",
                    profession = LoremIpsum(3).values.joinToString(),
                    description = LoremIpsum(15).values.joinToString(),
                    linkToResume = "abcd",
                    skills = skills,
                    links = links
                )
            ),
            onAction = {}
        )
    }
}
