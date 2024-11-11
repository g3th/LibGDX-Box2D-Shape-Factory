package com.shapes.factory.com

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World

/* Shapes Factory, shape-types (case-sensitive):

1) polygon
2) circle
3) chain

Parameters:

shape: String - The name of the shape (see above),
polygonWidth: Float , polygonHeight: Float - If a box, the W x H
circleRadius: Float - If a Circle, the radius
chainArray: Array<Vector2> - If a chainshape, the connecting vertices
bodyPosition: Vector2 - The position of the body in the world

 */

class CreateGameElement {

    companion object {

        fun bounds(world: World, bodyPosition: Vector2 = Vector2(0f, 0f)){
            val chainArray = arrayOf(Vector2(1f, 1f),
                Vector2(SCREEN_WIDTH - 1f, 1f),
                Vector2(SCREEN_WIDTH - 1f, SCREEN_HEIGHT - 1f),
                Vector2(1f, SCREEN_HEIGHT - 1f),
                Vector2(1f, 1f))
            // Position isn't required, since it's supplied in the vertices array
            val bounds = SimpleShapesFactory("chain", verticesArray = chainArray)
            bounds.shapeFixtures(1f, false, 1f, 1f)
            bounds.bodyDefinitions(BodyDef.BodyType.StaticBody, 1f)
            bounds.createBody(world)
        }

        fun ball(world: World, bodyPosition: Vector2){
            val shape = SimpleShapesFactory("circle", circleRadius = 0.1f, bodyPosition = bodyPosition)
            shape.shapeFixtures(1f, false, 1f, 1f)
            shape.bodyDefinitions(BodyDef.BodyType.DynamicBody, 1f)
            shape.createBody(world)
            shape.freeShapeMem()
        }

        fun box(world: World, bodyPosition: Vector2){
            val shape = SimpleShapesFactory("box", boxWidth = 0.3f, boxHeight = 0.3f,
                bodyPosition = bodyPosition)
            shape.shapeFixtures(1f, false, 1f, 1f)
            shape.bodyDefinitions(BodyDef.BodyType.DynamicBody, 1f)
            shape.createBody(world)
            shape.freeShapeMem()
        }

        fun triangle(world: World, bodyPosition: Vector2){
            val shape = SimpleShapesFactory("polygon", verticesArray = arrayOf(Vector2(0f,0f),
                Vector2(0.5f,0f),
                Vector2(0f,0.5f)), bodyPosition = bodyPosition)
            shape.shapeFixtures(1f, false, 1f, 1f)
            shape.bodyDefinitions(BodyDef.BodyType.DynamicBody, 1f)
            shape.createBody(world)
            shape.freeShapeMem()
        }

    }

}

