package malok.testtask.userlistviewer.domain

interface UserRepositoryInterface {
    suspend fun getUsers(count: Int = 20): List<User>
    suspend fun refreshUsers(count: Int = 20): List<User>
}