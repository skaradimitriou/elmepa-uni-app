package com.elmepa.departmentv2.presentation.list

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.findNavController
import com.elmepa.designsystem.components.topbar.TopBarWithTitleAndBackAction
import com.elmepa.designsystem.theme.ElmepaAppTheme
import com.stathis.common.MainViewModel

@Composable
fun DepartmentScreen() {
    //TODO VM will be moved to comp fun once the app is migrated fully to compose
    val viewModel: DepartmentViewModel = hiltViewModel()

    //val state by viewModel.state.collectAsStateWithLifecycle()
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
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // TODO 557 will add the new screen UI
                    Text(text = "New dept screen yay!")
                }
            }
        )
    }
}
