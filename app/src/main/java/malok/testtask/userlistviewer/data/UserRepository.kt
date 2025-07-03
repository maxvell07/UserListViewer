package malok.testtask.userlistviewer.data

import malok.testtask.userlistviewer.domain.User
import malok.testtask.userlistviewer.domain.UserRepositoryInterface

class UserRepository(private val apiService: ApiService) : UserRepositoryInterface {

    override suspend fun getUsers(count: Int): List<User> {
        val response = apiService.getUsers(resultsCount = count)
        return response.results.map { dto ->
            dto.mapToDomain()
        }
    }
}
