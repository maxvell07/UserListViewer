package malok.testtask.userlistviewer.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/")
    suspend fun getUsers(
        @Query("results") resultsCount: Int = 20
    ): UserResponseDto
}
