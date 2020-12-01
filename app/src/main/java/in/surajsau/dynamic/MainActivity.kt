package `in`.surajsau.dynamic

import `in`.surajsau.dynamic.components.CoilAdapter
import `in`.surajsau.dynamic.components.ImageCard
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import `in`.surajsau.dynamic.ui.DynamicTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DynamicTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    ImageCard(
                        imageUrl = "https://i.ytimg.com/vi/G5l6dGa7AXo/maxresdefault.jpg",
                        modifier = Modifier
                            .wrapContentSize()
                            .align(alignment = Alignment.Center),
                        adapter = CoilAdapter(ContextAmbient.current)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DynamicTheme {
        Box(modifier = Modifier.fillMaxSize()) {

        }
    }
}