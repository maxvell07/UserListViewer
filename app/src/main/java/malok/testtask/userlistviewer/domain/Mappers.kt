package malok.testtask.userlistviewer.domain

import malok.testtask.userlistviewer.data.UserDbo

fun UserDbo.toDomain(): User = User(
    id, fullName, gender, email, phone, cell, address,
    dateOfBirth, age, registeredDate, nationality, avatarUrl
)

fun User.toDbo(): UserDbo = UserDbo(
    id, fullName, gender, email, phone, cell, address,
    dateOfBirth, age, registeredDate, nationality, avatarUrl
)

