package com.example.draganddroplazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.draganddroplazycolumn.ui.theme.DragAndDropLazyColumnTheme

class MainActivity : ComponentActivity() {

    private val screenViewModel: ScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DragAndDropLazyColumnTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    val list = screenViewModel.list.collectAsState()
                    DragAndDropLazyColumnScreen(
                        list = list.value,
                        onMoved = { updatedList ->
                            screenViewModel.updateList(updatedList)
                        }
                    )
                }
            }
        }
    }
}