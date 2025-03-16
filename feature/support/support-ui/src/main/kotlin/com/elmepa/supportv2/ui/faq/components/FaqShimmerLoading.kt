package com.elmepa.support.ui.faq.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elmepa.designsystem.components.shimmer.ShimmerEffect
import com.elmepa.designsystem.theme.ElmepaAppTheme

@Composable
internal fun FaqShimmerLoading(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .padding(top = 8.dp)
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(count = 10) {
            ShimmerEffect(
                modifier = Modifier
                    .height(70.dp)
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
private fun FaqShimmerLoadingPreview() {
    ElmepaAppTheme {
        FaqShimmerLoading(modifier = Modifier.padding(all = 8.dp))
    }
}
