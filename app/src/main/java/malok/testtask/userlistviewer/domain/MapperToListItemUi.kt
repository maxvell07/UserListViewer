package malok.testtask.userlistviewer.domain

import malok.testtask.userlistviewer.data.UserListItemUi

fun User.mapToListItemUi(): UserListItemUi = UserListItemUi(
    id = id,
    fullName = fullName,
    phone = phone,
    avatarUrl = avatarUrl,
    adress = address
)