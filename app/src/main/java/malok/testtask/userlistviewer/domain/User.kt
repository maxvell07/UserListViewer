package malok.testtask.userlistviewer.domain


data class User(
    val id: String,
    val fullName: String,    // title + first + last
    val gender: String,
    val email: String,
    val phone: String,
    val cell: String,
    val address: String,     // street + city + state + country + postcode
    val dateOfBirth: String, // dob.date
    val age: Int,            // dob.age
    val registeredDate: String, // registered.date
    val nationality: String, // nat
    val avatarUrl: String    // picture.medium
)