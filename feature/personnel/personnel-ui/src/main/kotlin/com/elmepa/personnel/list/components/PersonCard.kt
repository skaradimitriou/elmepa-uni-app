package com.elmepa.personnel.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.spacing
import com.elmepa.personnel.model.Gender
import com.elmepa.personnel.model.Person
import com.elmepa.personnel.util.imageByGender

@Composable
internal fun PersonCard(person: Person, onClick: (Person) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(person)
            },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = MaterialTheme.spacing.medium)
        ) {
            val placeholderImg = person.imageByGender
            AsyncImage(
                modifier = Modifier.size(85.dp),
                model = person.image,
                placeholder = painterResource(placeholderImg),
                error = painterResource(placeholderImg),
                contentDescription = person.fullName
            )
            PersonDetails(
                fullName = person.fullName,
                jobTitle = person.description,
                email = person.email
            )
        }
    }
}

@Composable
private fun RowScope.PersonDetails(fullName: String, jobTitle: String, email: String) {
    Column(
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .padding(start = MaterialTheme.spacing.medium)
    ) {
        Text(
            text = fullName,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

        if (jobTitle.isNotEmpty()) {
            Text(
                text = jobTitle,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        }

        Text(
            text = email,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
private fun PersonCardPreview() {
    ElmepaAppTheme {
        PersonCard(
            person = Person(
                fullName = "Test Testopoulos",
                description = "Έκτακτο Εκπαιδευτικό Προσωπικό",
                image = "https://firebasestorage.googleapis.com/v0/b/elmepa-univ-app.appspot.com/o/professors%2Fgvasileiadis.png?alt=media&token=b3518f7c-d952-4284-84f8-75d5f719e14f",
                gender = Gender.MALE,
                email = "t.testopoulos@gmail.com",
                vocative = "Test Testopoulos"
            ),
            onClick = {}
        )
    }
}
