package malok.testtask.userlistviewer.domain

class GetUsersUseCase(private val userRepository: UserRepositoryInterface) {

    suspend fun execute(count: Int = 20): List<User> {
        return userRepository.getUsers(count)
    }
}