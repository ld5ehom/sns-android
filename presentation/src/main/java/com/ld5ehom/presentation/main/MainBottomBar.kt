package com.ld5ehom.presentation.main

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ld5ehom.presentation.main.post.PostActivity
import com.ld5ehom.presentation.theme.SNSTheme

// Composable function for rendering the Main Bottom Bar with navigation
// (네비게이션 기능을 가진 메인 바텀 바를 렌더링하는 컴포저블 함수)
@Composable
fun MainBottomBar(
    navController: NavController
) {
    val context = LocalContext.current  // Retrieves the current context (현재 컨텍스트 가져오기)
    val navBackStackEntry by navController.currentBackStackEntryAsState()  // Gets the current back stack entry (현재 백 스택 항목 가져오기)

    // Determines the current route based on the back stack entry
    // (백 스택 항목을 기반으로 현재 경로 결정)
    val currentRoute: MainRoute = navBackStackEntry
        ?.destination
        ?.route
        ?.let {
            currentRoute -> MainRoute.values().find {
                route -> route.route == currentRoute
            }
        }
        ?: MainRoute.HOME  // Default route is set to Home

    // Calls the MainBottomBar composable to render the bottom bar
    // (메인 바텀 바를 렌더링하는 함수 호출)
    MainBottomBar(
        currentRoute = currentRoute,
        onItemClick = { newRoute ->
            // If the new route is Post (formerly Writing), navigate to WritingActivity
            // (새 경로가 포스트일 경우 WritingActivity로 이동)
            if (newRoute == MainRoute.POST) {
                context.startActivity(
                    Intent(context, PostActivity::class.java)
                )
            } else {
                // Navigates to the selected route and pops up to the start destination
                // (선택한 경로로 이동하고 시작 화면으로 되돌아감)
                navController.navigate(route = newRoute.route) {
                    navController.graph.startDestinationRoute?.let {
                        popUpTo(it) {
                            saveState = true  // Saves the state
                        }
                    }
                    this.launchSingleTop = true  // Ensures only one instance of the route is launched (경로의 인스턴스가 하나만 생성되도록 보장)
                    this.restoreState = true  // Restores the previous state
                }
            }
        }
    )
}

// Composable function that renders the Main Bottom Bar UI with navigation icons
// (네비게이션 아이콘을 가진 메인 바텀 바 UI를 렌더링하는 컴포저블 함수)
@Composable
private fun MainBottomBar(
    currentRoute: MainRoute,
    onItemClick: (MainRoute) -> Unit
) {
    Column {
        Divider()  // Renders a divider above the bottom bar (바텀 바 위에 구분선 렌더링)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Loop through each MainRoute and render corresponding icons
            // (각 MainRoute를 순회하면서 해당 아이콘을 렌더링)
            MainRoute.values().forEach { route ->
                IconButton(onClick = { onItemClick(route) }) {
                    Icon(
                        imageVector = route.icon,
                        contentDescription = route.contentDescription,
                        tint = if (currentRoute == route) {
                            MaterialTheme.colorScheme.primary  // Highlights selected route icon (선택된 아이콘을 강조)
                        } else {
                            Color.White  // Non-selected icons remain white (선택되지 않은 아이콘은 흰색 유지)
                        }
                    )
                }
            }
        }
    }
}

// Preview function for MainBottomBar
@Preview
@Composable
private fun MainBottomBarPreview() {
    SNSTheme {
        Surface {
            var currentRoute by remember {
                mutableStateOf(MainRoute.HOME) // Sets default route to Home
            }
            MainBottomBar(
                currentRoute = currentRoute,
                onItemClick = { newRoute -> currentRoute = newRoute }
            )
        }
    }
}
