package com.elmepa.personnel.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetDefaults.DragHandle
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elmepa.designsystem.components.shimmer.ShimmerEffect
import com.elmepa.designsystem.components.topbar.TopBarWithTitleAndSearchInput
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.spacing
import com.elmepa.personnel.list.PersonnelListView.State
import com.elmepa.personnel.list.PersonnelListView.UIAction
import com.elmepa.personnel.list.PersonnelListView.UIAction.SearchPersonByName
import com.elmepa.personnel.list.components.PersonBottomSheet
import com.elmepa.personnel.list.components.PersonCard
import com.elmepa.personnel.model.Gender
import com.elmepa.personnel.model.Person
import com.elmepa.personnel.ui.R
import com.stathis.common.R as commonRes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PersonnelScreen(state: State, onAction: (UIAction) -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedPerson: Person? by remember { mutableStateOf(null) }

    Scaffold(
        topBar = {
            TopBarWithTitleAndSearchInput(
                title = stringResource(commonRes.string.personnel),
                hint = stringResource(commonRes.string.search_in_personnel),
                onSearchInputChanged = { query -> onAction(SearchPersonByName(query)) }
            )
        },
        content = { paddingValues ->
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState,
                    dragHandle = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.background),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            DragHandle()
                        }
                    }
                ) {
                    PersonBottomSheet(
                        person = selectedPerson,
                        onClick = { action ->
                            showBottomSheet = false
                            onAction(action)
                        }
                    )
                }
            }

            when (state) {
                is State.Loading -> PersonnelLoadingScreen(paddingValues)

                is State.Content -> PersonnelContent(
                    paddingValues = paddingValues,
                    personnel = state.personnel,
                    onClick = { person ->
                        showBottomSheet = true
                        selectedPerson = person
                    }
                )

                is State.Error -> ErrorScreen(paddingValues, onAction)
            }
        }
    )
}

@Composable
private fun PersonnelContent(
    paddingValues: PaddingValues,
    personnel: List<Person>,
    onClick: (Person) -> Unit
) {
    if (personnel.isEmpty()) {
        EmptyPersonnelInfoBox(paddingValues)
    } else {
        PersonnelList(paddingValues, personnel, onClick)
    }
}

@Composable
private fun PersonnelList(
    paddingValues: PaddingValues,
    personnel: List<Person>,
    onClick: (Person) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(MaterialTheme.spacing.small),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        items(personnel, key = { it.id }) { person ->
            PersonCard(
                person = person,
                onClick = { person ->
                    onClick(person)
                }
            )
        }
    }
}

@Composable
private fun EmptyPersonnelInfoBox(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .padding(all = MaterialTheme.spacing.medium)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.empty_personnel_results))
    }
}

@Composable
private fun ErrorScreen(paddingValues: PaddingValues, onClick: (UIAction) -> Unit) {
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

        Button(onClick = { onClick(UIAction.Retry) }) {
            Text(text = stringResource(commonRes.string.retry))
        }
    }
}

@Composable
fun PersonnelLoadingScreen(paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(MaterialTheme.spacing.small),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        items(10) {
            ShimmerEffect(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun PersonCardPreview() {
    ElmepaAppTheme {
        PersonnelScreen(
            state = State.Content(
                listOf(
                    Person(
                        fullName = "Test Testopoulos",
                        description = "Έκτακτο Εκπαιδευτικό Προσωπικό",
                        image = "https://firebasestorage.googleapis.com/v0/b/elmepa-univ-app.appspot.com/o/professors%2Fgvasileiadis.png?alt=media&token=b3518f7c-d952-4284-84f8-75d5f719e14f",
                        gender = Gender.MALE,
                        email = "t.testopoulos@gmail.com",
                        vocative = "Test Testopoulos",
                    ), Person(
                        fullName = "Test Testopoulou",
                        description = "Έκτακτο Εκπαιδευτικό Προσωπικό",
                        image = "https://firebasestorage.googleapis.com/v0/b/elmepa-univ-app.appspot.com/o/professors%2Fgvasileiadis.png?alt=media&token=b3518f7c-d952-4284-84f8-75d5f719e14f",
                        gender = Gender.FEMALE,
                        email = "t.testopoulos@gmail.com",
                        vocative = "Test Testopoulou",
                    )
                )
            ),
            onAction = {}
        )
    }
}

@Preview
@Composable
private fun EmptyQueryPreview() {
    ElmepaAppTheme {
        EmptyPersonnelInfoBox(PaddingValues(MaterialTheme.spacing.small))
    }
}

@Preview
@Composable
private fun ErrorScreenPreview() {
    ElmepaAppTheme {
        ErrorScreen(paddingValues = PaddingValues(MaterialTheme.spacing.small), onClick = {})
    }
}

