package com.example.testapppattern.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testapppattern.core.navigation.LocalAppNavigator
import com.example.testapppattern.core.ui.viewmodel.FunctionViewModelFactory
import com.example.testapppattern.feature.settings.graph.SettingsGraphHolderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ParametersScreen(
    graphHolder: SettingsGraphHolderViewModel,
) {
    val navigator = LocalAppNavigator.current
    val vmFactory = remember(graphHolder) {
        FunctionViewModelFactory { graphHolder.component.parametersViewModel() }
    }
    val viewModel: ParametersViewModel = viewModel(factory = vmFactory)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.parameters_screen_title)) },
                navigationIcon = {
                    TextButton(onClick = { navigator.back() }) {
                        Text(stringResource(R.string.parameters_back))
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.parameters_screen_subtitle),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
            )
            Text(
                text = stringResource(R.string.parameters_detail_level, uiState.detailLevel),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 16.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}
