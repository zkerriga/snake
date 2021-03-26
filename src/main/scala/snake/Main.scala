package snake

import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}

object Main extends App {
  val config = new LwjglApplicationConfiguration

  val cellSize = 10
  val width = 40
  val height = 40

  config.title = "Scala Snake"
  config.width = cellSize * width
  config.height = cellSize * height

  new LwjglApplication(
    SnakeGame(cellSize, width, height),
    config
  )
}
