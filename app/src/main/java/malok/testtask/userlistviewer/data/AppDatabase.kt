package malok.testtask.userlistviewer.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserDbo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}