package cn.shanyaliux.composedemo.permission

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cn.shanyaliux.composedemo.permission.ui.theme.ComposeDemoTheme

class PermissionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val requestList =
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)

        requestPermissions(requestList) {
            setContent {
                if (it.isEmpty()) {
                    Text(text = "以获取全部权限.")
                } else {
                    RequestFailedDialog(it)
                }
            }
        }
    }

    private fun requestPermissions(permissions: Array<String>, onResult: (List<String>) -> Unit) {
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            val failed = result.filter { !it.value }.keys
            onResult(failed.toList())
        }.launch(permissions)
    }
}

