package malok.testtask.userlistviewer.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import malok.testtask.userlistviewer.data.*
import malok.testtask.userlistviewer.domain.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService = RetrofitClient.apiService

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "user_db"
        ).build()

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()


    @Provides
    @Singleton
    fun provideUserRepository(api: ApiService, dao: UserDao): UserRepositoryInterface =
        UserRepository(apiService = api, userDao = dao)

    @Provides
    @Singleton
    fun provideGetUsersUseCase(repo: UserRepositoryInterface): GetUsersUseCase =
        GetUsersUseCase(repo)
}
