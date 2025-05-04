package com.elmepa.designsystem.components.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.Petrol
import com.elmepa.designsystem.theme.spacing

private const val EMPTY: String = ""

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithTitleAndSearchInput(
    title: String,
    hint: String,
    onBackActionClick: () -> Unit
) {
    var isSearching by remember { mutableStateOf(false) }
    var textState by remember { mutableStateOf(TextFieldValue(EMPTY)) }

    TopAppBar(
        title = {
            SearchBox(
                title = title,
                hint = hint,
                textState = textState,
                isSearching = isSearching,
                onValueChange = { textState = it }
            )
        },
        actions = {
            SearchEndIcon(
                isSearching = isSearching,
                onSearchCancelled = {
                    isSearching = !isSearching
                    textState = TextFieldValue(EMPTY)
                },
                onClearText = { textState = TextFieldValue(EMPTY) }
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
                onClick = {
                    if (isSearching) {
                        isSearching = false
                    } else {
                        onBackActionClick()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
private fun SearchBox(
    title: String,
    hint: String,
    textState: TextFieldValue,
    isSearching: Boolean,
    onValueChange: (TextFieldValue) -> Unit
) {

    if (isSearching) {
        BasicTextField(
            value = textState,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium),
            cursorBrush = SolidColor(Color.White),
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (textState.text.isEmpty()) {
                        Text(text = hint, color = Color.Gray)
                    }
                    innerTextField()
                }
            }
        )
    } else {
        Text(title)
    }
}

@Composable
private fun SearchEndIcon(
    isSearching: Boolean,
    onSearchCancelled: () -> Unit,
    onClearText: () -> Unit
) {
    if (isSearching) {
        IconButton(onClick = onClearText) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = Icons.Default.Close.name
            )
        }
    } else {
        IconButton(onClick = onSearchCancelled) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = Icons.Default.Close.name
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun TopBarWithTitleAndSearchInputPreview() {
    ElmepaAppTheme {
        TopBarWithTitleAndSearchInput(
            title = LoremIpsum(3).values.joinToString(),
            hint = "Searching...",
            onBackActionClick = {}
        )
    }
}
