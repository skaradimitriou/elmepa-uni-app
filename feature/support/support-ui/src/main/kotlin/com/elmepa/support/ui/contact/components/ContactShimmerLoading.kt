package com.elmepa.support.ui.contact.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elmepa.designsystem.components.shimmer.ShimmerEffect
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.elmepa.designsystem.theme.spacing

@Composable
internal fun ContactShimmerLoading(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .padding(top = MaterialTheme.spacing.small)
            .padding(horizontal = MaterialTheme.spacing.small),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        items(count = 4) {
            ShimmerEffect(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactShimmerLoadingPreview() {
    ElmepaAppTheme {
        ContactShimmerLoading(modifier = Modifier.padding(MaterialTheme.spacing.small))
    }
}
