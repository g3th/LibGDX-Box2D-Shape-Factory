package com.shapes.factory.com

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport

// Potato Pixels Width x Height
const val SCREEN_WIDTH = 10f
const val SCREEN_HEIGHT = 5f

class MainGameScreen: Screen {
    lateinit var world: World
    lateinit var physicsDebugger: Box2DDebugRenderer
    val stage = Stage()
    val viewPort: Viewport
    val camera: OrthographicCamera

    init {
        camera = OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT)
        viewPort = FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera)
        camera.position.set(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f, 0f)
    }

    override fun show() {
        // Views
        stage.viewport = viewPort
        stage.viewport.camera = camera

        // Physics Initialization
        world = World(Vector2(0f, -9.8f), true)
        physicsDebugger = Box2DDebugRenderer()

        // Some Bounds
        CreateGameElement.bounds(world)

        // Some Shapes
        CreateGameElement.ball(world, bodyPosition = Vector2(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2))
        CreateGameElement.box(world, bodyPosition = Vector2(SCREEN_WIDTH / 2 - 2f, SCREEN_HEIGHT / 2))
        CreateGameElement.triangle(world, bodyPosition = Vector2(SCREEN_WIDTH / 2 + 1f, SCREEN_HEIGHT / 2))


    }

    override fun resize(width: Int, height: Int) {
        viewPort.update(width, height)
        camera.update()
        stage.camera.update()
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(0f, 0f, 0f, 0f)
        physicsDebugger.render(world, camera.combined)
        stage.act()
        stage.draw()
        world.step(1f / 60f, 6, 2)
    }

    override fun dispose() {
        debugAsLog("Dispose was Called")
        world.dispose()
        stage.dispose()
        physicsDebugger.dispose()
    }

    /////////////////
    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
}
