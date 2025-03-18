package com.elmepa.personnel.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.elmepa.personnel.list.PersonnelListView.State
import com.elmepa.personnel.model.Person

@Composable
internal fun PersonnelScreen(state: State) {
    Scaffold(
        topBar = {
            //
        },
        content = { paddingValues ->
            when (state) {
                is State.Loading -> Unit

                is State.Content -> PersonnelList(
                    paddingValues = paddingValues,
                    personnel = state.personnel
                )

                is State.Error -> Unit
            }
        }
    )
}

@Composable
private fun PersonnelList(paddingValues: PaddingValues, personnel: List<Person>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .consumeWindowInsets(paddingValues)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(personnel, key = { it.id }) { person ->
            Text(text = person.fullName)
        }
    }
}
