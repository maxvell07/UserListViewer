package malok.testtask.userlistviewer.domain


data class User(
    val id: String,
    val fullName: String,
    val gender: String,
    val email: String,
    val phone: String,
    val cell: String,
    val address: String,
    val dateOfBirth: String,
    val age: Int,
    val registeredDate: String,
    val nationality: String,
    val avatarUrl: String
)