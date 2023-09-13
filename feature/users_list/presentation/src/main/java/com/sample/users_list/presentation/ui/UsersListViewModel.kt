package com.sample.users_list.presentation.ui

import androidx.lifecycle.viewModelScope
import com.sample.common.BaseViewModel
import com.sample.users_list.api.usecase.UsersListUseCase
import com.sample.users_list.api.usecase.UsersListUseCaseResult
import com.sample.users_list.presentation.ui.data.UserUiModel
import com.sample.users_list.presentation.ui.data.toListUserUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsersListViewModel(val getUsersListUseCase: UsersListUseCase): BaseViewModel() {

    private val _usersListState = MutableStateFlow(UsersListState())
    val usersList: StateFlow<UsersListState> = _usersListState.asStateFlow()

    init {
        fetchData()
    }

    fun retry() {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _usersListState.update { usersListState ->
                usersListState.copy(isLoading = true)
            }
            viewModelScope.launch {
                when(val usersListData = getUsersListUseCase()) {
                    is UsersListUseCaseResult.Success -> {
                        _usersListState.update { usersListState ->
                            usersListState.copy(isLoading = false, users = usersListData.user.toListUserUiModel())
                        }
                    }
                    is UsersListUseCaseResult.Error -> {
                        _usersListState.update { usersListState ->
                            usersListState.copy(isLoading = false, isError = true, errorMessage = usersListData.throwable?.message)
                        }
                    }
                    else -> {
                        _usersListState.update { usersListState ->
                            usersListState.copy(isLoading = false, isError = true)
                        }
                    }
                }
            }
        }
    }
}

data class UsersListState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val users: List<UserUiModel> = emptyList()
)