package `in`.surajsau.dynamic.components

import `in`.surajsau.dynamic.util.toImageAsset
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.layout.WithConstraints

@Composable
fun ImageCard(
    imageUrl: String,
    adapter: Adapter,
    modifier: Modifier = Modifier,
) {
    WithConstraints(modifier = modifier) {

        val imageAsset = remember { mutableStateOf<ImageAsset?>(null) }

        onCommit(imageUrl) {

            val imageLoader = ImageLoader.get(adapter = adapter)
                .load(imageUrl)
                .onSuccess {
                    imageAsset.value = it.toImageAsset()
                }
                .onStart {  }
                .onError { _, exception -> exception.printStackTrace() }

            imageLoader.enqueue()

            onDispose {
                imageAsset.value = null
                imageLoader.onDispose()
            }
        }

        imageAsset.value?.let { Image(asset = it, modifier = modifier) }
    }
}