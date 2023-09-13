package com.sample.users_list.presentation.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.users_list.presentation.ui.UsersListViewModel
import com.sample.users_list.presentation.ui.data.UserUiModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(usersListViewModel: UsersListViewModel) {

    val usersListState by usersListViewModel.usersList.collectAsState()

    // Simulate loading state
    val isLoading by rememberUpdatedState(newValue = usersListState.isLoading)
    val isError by rememberUpdatedState(newValue = usersListState.isError)
    val errorMessage by rememberUpdatedState(newValue = usersListState.errorMessage)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Users") }
            )
        },
        content = { paddingValue ->
            Column(
                modifier = Modifier
                    .padding(paddingValue)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isLoading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    if (isError) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Snackbar(
                                modifier = Modifier.padding(16.dp),
                                action = {
                                    TextButton(
                                        onClick = {
                                            usersListViewModel.retry()
                                        }
                                    ) {
                                        Text(text = "Retry")
                                    }
                                }
                            ) {
                                Text(text = errorMessage ?: "An error occurred")
                            }
                        }
                    } else {
                        UsersList(recipes = usersListState.users)
                    }
                }
            }
        }
    )
}

@Composable
fun UsersList(recipes: List<UserUiModel>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(items = recipes) { item ->
            UserCard(recipe = item)
        }
    }
}

@Composable
fun UserCard(recipe: UserUiModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = recipe.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Start)
            )
            Text(
                text = recipe.email,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Start)
            )
        }
    }
}