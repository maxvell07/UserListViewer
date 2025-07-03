package malok.testtask.userlistviewer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import malok.testtask.userlistviewer.domain.GetUsersUseCase
import malok.testtask.userlistviewer.domain.User
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val _navigateToUserDetail = MutableLiveData<String?>()
    val navigateToUserDetail: LiveData<String?> = _navigateToUserDetail

    init {
        fetchUsers()
    }

    fun onUserClicked(userId: String) {
        _navigateToUserDetail.value = userId
    }

    fun onNavigated() {
        _navigateToUserDetail.value = null
    }

    fun refreshUsers() {
        viewModelScope.launch {
            try {
                val refreshedUsers = getUsersUseCase.execute(forceRefresh = true)
                _users.postValue(refreshedUsers)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val userList = getUsersUseCase.execute()
                _users.postValue(userList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}