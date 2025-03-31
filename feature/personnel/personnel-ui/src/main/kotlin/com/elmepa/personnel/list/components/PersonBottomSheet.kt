package com.elmepa.personnel.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.LightModeGray
import com.elmepa.designsystem.theme.spacing
import com.elmepa.personnel.list.PersonnelListView.UIAction
import com.elmepa.personnel.model.Gender
import com.elmepa.personnel.model.Person
import com.elmepa.personnel.ui.R
import com.elmepa.personnel.util.imageByGender

@Composable
internal fun PersonBottomSheet(person: Person?, onClick: (UIAction) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        person?.let {
            BasicInfo(person)
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium),
                colors = ButtonColors(
                    containerColor = LightModeGray,
                    contentColor = MaterialTheme.colorScheme.background,
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color.White
                ),
                onClick = {
                    onClick(UIAction.EmailOptionTap(person.email))
                },
            ) {
                Text(
                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.small),
                    text = stringResource(R.string.email_option),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(Modifier.height(MaterialTheme.spacing.small))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.medium),
                colors = ButtonColors(
                    containerColor = LightModeGray,
                    contentColor = MaterialTheme.colorScheme.background,
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color.White
                ),
                onClick = {
                    onClick(UIAction.ShareDetailsOptionTap(person))
                },
            ) {
                Text(
                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.small),
                    text = stringResource(R.string.share_option),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(Modifier.height(MaterialTheme.spacing.medium))
        }
    }
}

@Composable
private fun ColumnScope.BasicInfo(person: Person) {
    val placeholderImg = person.imageByGender
    AsyncImage(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape),
        model = person.image,
        placeholder = painterResource(placeholderImg),
        error = painterResource(placeholderImg),
        contentDescription = person.fullName,
        contentScale = ContentScale.FillBounds
    )
    Spacer(Modifier.height(MaterialTheme.spacing.medium))
    Text(
        text = person.fullName,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
    )
    Spacer(Modifier.height(MaterialTheme.spacing.small))
    Text(
        text = person.description,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onBackground,
        textAlign = TextAlign.Center
    )
}

@PreviewLightDark
@Composable
private fun PersonBottomSheetPreview() {
    ElmepaAppTheme {
        PersonBottomSheet(
            Person(
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
