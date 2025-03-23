package com.elmepa.personnel.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elmepa.designsystem.components.shimmer.ShimmerEffect
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.personnel.list.PersonnelListView.State
import com.elmepa.personnel.list.components.PersonCard
import com.elmepa.personnel.model.Gender
import com.elmepa.personnel.model.Person

@Composable
internal fun PersonnelScreen(state: State, onClick: (Person) -> Unit) {
    Scaffold(
        topBar = {
            //
        },
        content = { paddingValues ->
            when (state) {
                is State.Loading -> PersonnelLoadingScreen(paddingValues)

                is State.Content -> PersonnelList(
                    paddingValues = paddingValues,
                    personnel = state.personnel,
                    onClick = onClick
                )

                is State.Error -> Unit
            }
        }
    )
}

@Composable
private fun PersonnelList(paddingValues: PaddingValues, personnel: List<Person>, onClick: (Person) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .consumeWindowInsets(paddingValues)
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(personnel, key = { it.id }) { person ->
            PersonCard(person, onClick)
        }
    }
}

@Composable
fun PersonnelLoadingScreen(paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .consumeWindowInsets(paddingValues)
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
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
            onClick = {}
        )
    }
}
