package malok.testtask.userlistviewer.domain

class GetUsersUseCase(private val userRepository: UserRepositoryInterface) {

    suspend fun execute(count: Int = 20, forceRefresh: Boolean = false): List<User> {
        return if (forceRefresh) {
            userRepository.refreshUsers(count)
        } else {
            userRepository.getUsers(count)
        }
    }
}