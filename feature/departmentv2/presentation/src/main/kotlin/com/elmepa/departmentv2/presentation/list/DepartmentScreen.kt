package com.elmepa.departmentv2.presentation.list

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.findNavController
import coil3.compose.AsyncImage
import com.elmepa.departmentv2.domain.model.DepartmentMember
import com.elmepa.departmentv2.domain.model.DepartmentModule
import com.elmepa.departmentv2.domain.model.DepartmentProgramme
import com.elmepa.departmentv2.domain.model.DepartmentResponse
import com.elmepa.departmentv2.domain.model.FeaturedItem
import com.elmepa.departmentv2.domain.model.SocialLink
import com.elmepa.designsystem.components.list.ListItemWithIconTitleAndSubtitle
import com.elmepa.designsystem.components.topbar.TopBarWithTitleAndBackAction
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.Petrol
import com.elmepa.designsystem.theme.spacing
import com.stathis.common.MainViewModel
import com.stathis.common.R

@SuppressWarnings("UnusedPrivateProperty")
@Composable
fun DepartmentScreen() {
    //TODO VM will be moved to comp fun once the app is migrated fully to compose
    val viewModel: DepartmentViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val activity = LocalContext.current as ComponentActivity
    val activityViewModel: MainViewModel = hiltViewModel(activity)

    //TODO navController will be removed once nav3 is introduced to project
    val navController = LocalView.current.findNavController()

    ElmepaAppTheme {
        Scaffold(
            topBar = {
                TopBarWithTitleAndBackAction(
                    title = "Department",
                    onBackActionClick = { navController.popBackStack() }
                )
            },
            content = { paddingValues ->
                when (val state = state) {
                    DepartmentView.State.Loading -> LoadingScreen(paddingValues)
                    is DepartmentView.State.Content -> DepartmentContent(paddingValues, state.data)
                    DepartmentView.State.Error -> ErrorScreen(paddingValues)
                }
            }
        )
    }
}

@Composable
private fun DepartmentContent(
    paddingValues: PaddingValues = PaddingValues(),
    departmentInfo: DepartmentResponse
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(scrollState),
    ) {
        FeaturedItems(departmentInfo.featured)
        Programmes(departmentInfo.programmes)
        Modules(departmentInfo.modules)
        DepartmentMembers(departmentInfo.members)
        Text("Connect with the department")
    }
}

@Composable
private fun FeaturedItems(featuredItems: List<FeaturedItem>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        contentPadding = PaddingValues(all = MaterialTheme.spacing.small)
    ) {
        items(featuredItems) { featuredItem ->
            FeaturedCard(
                imageUrl = featuredItem.imageUrl,
                title = featuredItem.title,
                subtitle = featuredItem.description
            )
        }
    }

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
}

@Composable
private fun Programmes(programmes: List<DepartmentProgramme>) {
    Text(
        modifier = Modifier.padding(all = MaterialTheme.spacing.small),
        text = "Προγράμματα Σπουδών",
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface
    )

    Spacer(modifier = Modifier.height(12.dp))

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        contentPadding = PaddingValues(all = MaterialTheme.spacing.small)
    ) {
        items(programmes) { programme ->
            ProgrammeCard(
                title = programme.title,
                description = programme.description
            )
        }
    }
}

@Composable
private fun DepartmentMembers(members: List<DepartmentMember>) {
    Text(
        modifier = Modifier.padding(all = MaterialTheme.spacing.small),
        text = "Μέλη Δ.Ε.Π.",
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        contentPadding = PaddingValues(all = MaterialTheme.spacing.small)
    ) {
        items(members) { member ->
            DepartmentMemberCard(
                imageUrl = member.imageUrl,
                title = member.fullName,
                subtitle = member.profession
            )
        }
    }

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
}

@Composable
private fun FeaturedCard(imageUrl: String, title: String, subtitle: String) {
    val screenWidth = with(LocalDensity.current) {
        (LocalWindowInfo.current.containerSize.width * 0.92f).toDp()
    }

    Card(modifier = Modifier.width(screenWidth)) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(300.dp),
                model = imageUrl,
                contentDescription = title,
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(MaterialTheme.spacing.small))
                    .background(Color.Red)
                    .padding(all = MaterialTheme.spacing.medium),
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun ProgrammeCard(
    title: String,
    description: String
) {
    val screenWidth = with(LocalDensity.current) {
        (LocalWindowInfo.current.containerSize.width * 0.92f).toDp()
    }

    Card(
        modifier = Modifier.width(screenWidth),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = MaterialTheme.spacing.medium)
        ) {
            Icon(
                modifier = Modifier.padding(all = MaterialTheme.spacing.xSmall),
                painter = painterResource(R.drawable.ic_rocket),
                tint = Petrol,
                contentDescription = title
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                modifier = Modifier.fillMaxWidth(1f),
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(MaterialTheme.spacing.xSmall))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = description,
                style = MaterialTheme.typography.bodySmall.copy(
                    lineBreak = LineBreak.Paragraph,
                    hyphens = Hyphens.Auto
                ),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Justify,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(MaterialTheme.spacing.medium))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Διάβασε Περισσότερα",
                style = MaterialTheme.typography.bodySmall.copy(
                    lineBreak = LineBreak.Paragraph,
                    hyphens = Hyphens.Auto
                ),
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Composable
private fun DepartmentMemberCard(
    imageUrl: String,
    title: String,
    subtitle: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = MaterialTheme.spacing.medium),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .fillMaxWidth()
                    .clip(CircleShape),
                model = imageUrl,
                contentDescription = title,
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun Modules(modules: List<DepartmentModule>) {
    Text(
        modifier = Modifier.padding(all = MaterialTheme.spacing.small),
        text = "Γνωστικά Αντικείμενα",
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            modules.forEach { module ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ListItemWithIconTitleAndSubtitle(
                        iconRes = R.drawable.ic_link,
                        title = module.title,
                        subtitle = module.description,
                        onAction = {
                            //
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadingScreen(paddingValues: PaddingValues = PaddingValues()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TODO 557 will add the new screen UI
        Text(text = "Loading now...")
    }
}

@Composable
private fun ErrorScreen(paddingValues: PaddingValues = PaddingValues()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TODO 557 will add the new screen UI
        Text(text = "Error now...")
    }
}

@PreviewLightDark
@Composable
private fun DepartmentScreenPreview(modifier: Modifier = Modifier) {
    val dummyDepartmentInfoResponse = DepartmentResponse(
        featured = listOf(
            FeaturedItem(
                imageUrl = "https://picsum.photos/seed/research/1200/700",
                title = "Innovating for the Future",
                description = "Discover how our department is shaping the future through research & collaboration."
            ),
            FeaturedItem(
                imageUrl = "https://picsum.photos/seed/students/1200/700",
                title = "Student Life",
                description = "Explore the opportunities, activities, and experiences available to our students."
            ),
            FeaturedItem(
                imageUrl = "https://picsum.photos/seed/campus/1200/700",
                title = "Our Community",
                description = "Learn more about our vibrant academic community and the people who make it special."
            )
        ),
        members = listOf(
            DepartmentMember(
                imageUrl = "https://picsum.photos/seed/john/400/400",
                fullName = "Dr. John Doe",
                profession = "Professor"
            ),
            DepartmentMember(
                imageUrl = "https://picsum.photos/seed/jane/400/400",
                fullName = "Dr. Jane Smith",
                profession = "Associate Professor"
            ),
            DepartmentMember(
                imageUrl = "https://picsum.photos/seed/alex/400/400",
                fullName = "Alex Brown",
                profession = "Lecturer"
            ),
            DepartmentMember(
                imageUrl = "https://picsum.photos/seed/emma/400/400",
                fullName = "Emma Wilson",
                profession = "Research Fellow"
            )
        ),
        modules = listOf(
            DepartmentModule(
                title = "Introduction to Computer Science",
                description = "An introduction to fundamental concepts in computer science, &computational thinking."
            ),
            DepartmentModule(
                title = "Software Engineering",
                description = "Learn the principles and practices involved in designing, & maintaining software systems."
            ),
            DepartmentModule(
                title = "Data Structures and Algorithms",
                description = "Explore fundamental data structures and algorithms ."
            ),
            DepartmentModule(
                title = "Artificial Intelligence",
                description = "An introduction to artificial intelligence, machine learning, and intelligent systems."
            )
        ),
        programmes = listOf(
            DepartmentProgramme(
                title = "BSc Computer Science",
                description = "A comprehensive undergraduate programme covering the foundations and applications of CS."
            ),
            DepartmentProgramme(
                title = "MSc Software Engineering",
                description = "An advanced programme focused on modern software engineering practices."
            ),
            DepartmentProgramme(
                title = "PhD in Computer Science",
                description = "A research-focused programme for students pursuing advanced study to computer science."
            )
        ),

        social = listOf(
            SocialLink(name = "Facebook"),
            SocialLink(name = "Instagram"),
            SocialLink(name = "LinkedIn"),
            SocialLink(name = "YouTube")
        )
    )

    ElmepaAppTheme {
        DepartmentContent(departmentInfo = dummyDepartmentInfoResponse)
    }
}
