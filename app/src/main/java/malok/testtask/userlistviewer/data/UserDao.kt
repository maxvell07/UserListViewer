package malok.testtask.userlistviewer.data

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend fun getAll(): List<UserDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserDbo>)

    @Query("DELETE FROM users")
    suspend fun clear()
}