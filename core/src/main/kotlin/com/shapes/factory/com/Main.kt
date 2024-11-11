package com.shapes.factory.com

import com.badlogic.gdx.Game

class Main : Game() {
    override fun create() {
        val mainScreen = MainGameScreen()
        setScreen(mainScreen)
    }

}
