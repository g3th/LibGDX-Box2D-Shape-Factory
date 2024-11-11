package com.shapes.factory.com.android

import android.os.Bundle
import android.view.WindowManager
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.shapes.factory.com.Main

// Remember to keep the screen on with "window.addFlags"

class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        val configuration = AndroidApplicationConfiguration()
        initialize(Main(), configuration)
    }
}

