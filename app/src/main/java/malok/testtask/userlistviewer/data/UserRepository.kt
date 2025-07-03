package malok.testtask.userlistviewer.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import malok.testtask.userlistviewer.domain.User
import malok.testtask.userlistviewer.domain.UserRepositoryInterface
import malok.testtask.userlistviewer.domain.toDbo
import malok.testtask.userlistviewer.domain.toDomain
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) : UserRepositoryInterface {

    override suspend fun getUsers(count: Int): List<User> = withContext(Dispatchers.IO) {
        val cached = userDao.getAll()
        if (cached.isNotEmpty()) {
            cached.map { it.toDomain() }
        } else {
            refreshUsers(count)
        }
    }

    override suspend fun refreshUsers(count: Int): List<User> = withContext(Dispatchers.IO) {
        val response = apiService.getUsers(resultsCount = count)
        val users = response.results.map { it.mapToDomain() }

        userDao.clear()
        userDao.insertAll(users.map { it.toDbo() })

        users
    }
}