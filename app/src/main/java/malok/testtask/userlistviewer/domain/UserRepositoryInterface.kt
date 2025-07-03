package malok.testtask.userlistviewer.domain

interface UserRepositoryInterface {
    suspend fun getUsers(count: Int): List<User>
}
