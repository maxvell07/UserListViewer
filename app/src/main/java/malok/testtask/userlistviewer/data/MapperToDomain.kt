package malok.testtask.userlistviewer.data
import malok.testtask.userlistviewer.domain.User

fun UserResponseDto.UserDto.mapToDomain(): User {
    val id = login.uuid
    val fullName = "${name.title} ${name.first} ${name.last}"
    val address = buildString {
        append(location.street.number)
        append(" ")
        append(location.street.name)
        append(", ")
        append(location.city)
        append(", ")
        append(location.state)
        append(", ")
        append(location.country)
        append(", ")
        append(location.postcode.toString())
    }

    return User(
        id = id,
        fullName = fullName,
        gender = gender,
        email = email,
        phone = phone,
        cell = cell,
        address = address,
        dateOfBirth = dob.date,
        age = dob.age,
        registeredDate = registered.date,
        nationality = nat,
        avatarUrl = picture.medium
    )
}
