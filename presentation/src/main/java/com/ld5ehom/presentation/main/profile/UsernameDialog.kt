package com.ld5ehom.presentation.main.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.ld5ehom.presentation.theme.SNSTheme

@OptIn(ExperimentalMaterial3Api::class)
// Composable function for showing a username update dialog
// (사용자 이름 변경 다이얼로그를 표시하는 컴포저블 함수)
@Composable
fun UsernameDialog(
    visible: Boolean = false,
    initialUsername: String,  // Initial username to display
    onUsernameChange: (String) -> Unit,  // Callback for when the username is changed (사용자 이름이 변경되었을 때 호출되는 콜백 함수)
    onDismissRequest: () -> Unit  // Callback for dismissing the dialog
) {
    if (visible) {
        var username by remember {
            mutableStateOf(initialUsername) // State for the current username
        }

        Dialog(onDismissRequest = onDismissRequest) {
            Surface {
                Column(modifier = Modifier.fillMaxWidth(0.8f)) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),  // Full-width text field for username input
                        value = username,
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        ),
                        onValueChange = {
                            username = it  // Updates the username state on text change (텍스트가 변경될 때 사용자 이름 상태를 업데이트)
                        }
                    )
                    Row {
                        TextButton(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                onUsernameChange(username)  // Triggers the username change callback (사용자 이름 변경 콜백 호출)
                                onDismissRequest()  // Closes the dialog
                            }
                        ) {
                            Text(text = "Change")
                        }
                        TextButton(
                            modifier = Modifier.weight(1f),
                            onClick = onDismissRequest  // Closes the dialog
                        ) {
                            Text(text = "Cancel")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun UsernameDialogPreview() {
    SNSTheme {
        UsernameDialog(
            visible = true,
            initialUsername = "Taewook",
            onUsernameChange = {},
            onDismissRequest = {}
        )
    }
}
