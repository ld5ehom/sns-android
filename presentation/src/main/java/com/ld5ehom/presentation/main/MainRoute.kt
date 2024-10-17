package com.ld5ehom.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class MainRoute(
    val route: String,
    val contentDescription: String,
    val icon: ImageVector
) {
    HOME(route = "HomeScreen", contentDescription = "Post List", icon = Icons.Filled.Home),
    POST(route = "PostScreen", contentDescription = "Create Post", icon = Icons.Filled.AddCircle),
    PROFILE(route = "ProfileScreen", contentDescription = "My Profile", icon = Icons.Filled.AccountCircle),
}
