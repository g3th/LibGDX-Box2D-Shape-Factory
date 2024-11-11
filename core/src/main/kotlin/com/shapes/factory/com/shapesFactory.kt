package com.shapes.factory.com

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.badlogic.gdx.physics.box2d.ChainShape
import com.badlogic.gdx.physics.box2d.CircleShape
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World

interface ShapesCreator {

    fun shapeFixtures(density: Float, sensor: Boolean, friction: Float, restitution: Float)
    fun bodyDefinitions(bodyType: BodyType, gravityScale: Float)
    fun createBody(world: World)
    fun freeShapeMem()
}

abstract class CreateShape: ShapesCreator {

    lateinit var body: Body
    var fixtureDef = FixtureDef()
    var bodyDef = BodyDef()

    override fun shapeFixtures(density: Float, sensor: Boolean, friction: Float, restitution: Float) {}
    override fun bodyDefinitions(bodyType: BodyType, gravityScale: Float){}
    override fun createBody(world: World) {}
    override fun freeShapeMem() {}
}

class SimpleShapesFactory(shape: String, boxWidth: Float = 0f, boxHeight: Float = 0f, circleRadius: Float = 0f,
                          verticesArray: Array<Vector2> = arrayOf(Vector2(1f, 1f)),
                          bodyPosition: Vector2 = Vector2(0f, 0f)): CreateShape(){

    lateinit var box: PolygonShape
    lateinit var polygon: PolygonShape
    lateinit var circle: CircleShape
    lateinit var chain: ChainShape
    val userShape = shape
    val objectPosition = bodyPosition

    init {
        when {
            shape == "box" -> {
                box = PolygonShape()
                box.setAsBox(boxWidth, boxHeight)
                fixtureDef.shape = box
            }
            shape == "polygon" -> {
                polygon = PolygonShape()
                polygon.set(verticesArray)
                fixtureDef.shape = polygon
            }
            shape == "circle" -> {
                circle = CircleShape()
                circle.radius = circleRadius
                fixtureDef.shape = circle
            }
            shape == "chain" -> {
                chain = ChainShape()
                chain.createChain(verticesArray)
                fixtureDef.shape = chain
            }
        }
    }

    override fun shapeFixtures(density: Float, sensor: Boolean, friction: Float, restitution: Float) {
        fixtureDef.density = density
        fixtureDef.isSensor = sensor
        fixtureDef.friction = friction
        fixtureDef.restitution = restitution
    }

    override fun bodyDefinitions(bodyType: BodyType, gravityScale: Float) {
        bodyDef.type = bodyType
        bodyDef.gravityScale = gravityScale
        if (userShape != "chain") {
            bodyDef.position.set(objectPosition)
        }
    }

    override fun createBody(world: World) {
        body = world.createBody(bodyDef)
        body.createFixture(fixtureDef)
        if (userShape != "chain") {
            body.position.set(objectPosition)
        }
    }

    override fun freeShapeMem() {
        when {
            userShape == "polygon" -> {
                polygon.dispose()
                debugAsLog("Memory Deallocated for Polygon")
            }
            userShape == "box" ->{
                box.dispose()
                debugAsLog("Memory Deallocated for Box")
            }
            userShape == "circle" -> {
                circle.dispose()
                debugAsLog("Memory Deallocated for Circle")
            }
            userShape == "chain" -> {
                chain.dispose()
                debugAsLog("Memory Deallocated for Chain")
            }
        }
    }
}
