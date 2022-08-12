package cn.shanyaliux.composedemo.permission

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties


@Composable
fun PermissionDemo() {
    val context = LocalContext.current
    Button(onClick = {
        context.startActivity(Intent(context, PermissionActivity::class.java))
    }) {
        Text(text = "请求权限")
    }
}

@Composable
fun RequestFailedDialog(permissions: List<String>) {
    val activity = LocalContext.current as? Activity
    AlertDialog(onDismissRequest = {  },
        buttons = {
            Row {
                Button(
                    onClick = {
                        activity?.finish()
                    },
                    modifier = Modifier.weight(1f,true),
                    shape = RoundedCornerShape(bottomStart = 8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                ) {
                    Text(text = "取消")
                }
                Button(
                    onClick = {
                        val intent = Intent()
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
                        intent.data = Uri.fromParts("package", activity?.packageName, null)
                        activity?.startActivity(intent)
                    },
                    modifier = Modifier.weight(1f,true),
                    shape = RoundedCornerShape(bottomEnd = 8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                ) {
                    Text(text = "确定")
                }
            }
        },
        title = {
            Text(text = "提示")
        },
        text = {
            Surface {
                LazyColumn() {
                    items(permissions) { permission ->
                        Text(text = permission)
                    }
                }
            }
        },
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        contentColor = Color.Black,
        properties = DialogProperties()

    )
}