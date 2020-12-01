package `in`.surajsau.dynamic.components

import android.content.Context
import android.graphics.drawable.Drawable
import coil.Coil
import coil.request.ImageRequest
import coil.size.Scale
import coil.target.Target

class CoilAdapter(private val context: Context) : Adapter() {

    private val imageLoader = Coil.imageLoader(context = context)

    private val target = object: Target {
        override fun onSuccess(result: Drawable) {
            callback?.onSuccess(result)
        }

        override fun onError(error: Drawable?) {
            callback?.onError(error, Exception("Coil failed to load image"))
        }

        override fun onStart(placeholder: Drawable?) {
            callback?.onStart(placeholder)
        }
    }

    override fun enqueue(imageUrl: String?) {
        val request = ImageRequest.Builder(context = context)
            .scale(scale = Scale.FILL)
            .data(data = imageUrl)
            .target(target = target)
            .build()

        imageLoader.enqueue(request = request)
    }

    override fun dispose() {
        imageLoader.shutdown()
    }

}