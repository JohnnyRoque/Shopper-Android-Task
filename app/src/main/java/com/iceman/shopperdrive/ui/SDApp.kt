package com.iceman.shopperdrive.ui

import RouteDetailScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iceman.home.HomeScreen
import com.iceman.shopperdrive.R
import com.iceman.shopperdrive.navigation.SdScreens
import org.koin.androidx.compose.koinViewModel


const val TASK_USER = "Catarina"
const val TASK_ID = "CT01"


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopperDriveAppBar(
    currentScreen: SdScreens,
    canNavigateUp: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {

    LargeTopAppBar(
        title = {
            Column {
                if (currentScreen == SdScreens.Home) {
                    Text(
                        text = stringResource(id = R.string.hello_title, TASK_USER),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = stringResource(id = R.string.tag_text, TASK_ID),
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                } else if (currentScreen == SdScreens.Detail) {
                    Text(
                        text = stringResource(id = R.string.detail_title),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors()
            .copy(containerColor = MaterialTheme.colorScheme.primary),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateUp) {
                IconButton(onClick = navigateUp) {
                    androidx.compose.material3.Icon(
                        modifier = Modifier.size(50.dp),
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = stringResource(id = R.string.back_button),
                    )
                }
            }
        },
    )
}


@Composable
fun SDApp(
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel = koinViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val screenState by appViewModel.screenState.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen =
        SdScreens.valueOf(backStackEntry?.destination?.route ?: SdScreens.Home.name)

    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        ShopperDriveAppBar(
            currentScreen = currentScreen,
            canNavigateUp = navController.previousBackStackEntry != null,
            navigateUp = { navController.navigateUp() })
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SdScreens.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(SdScreens.Home.name) {
                HomeScreen {
                    appViewModel.updateScreenState(it)
                    if (it.origin.isNotEmpty() && it.destination.isNotEmpty()){
                        navController.navigate((SdScreens.Detail.name))
                    }
                }

            }

            composable(SdScreens.Detail.name) {
                RouteDetailScreen(screenState = screenState){
                    navController.navigate(SdScreens.Home.name)
                }
            }

            composable(SdScreens.History.name){

            }
        }
    }
}


