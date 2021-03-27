package snake

import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}

import snake.bot.SimpleBot

object Main extends App {
  val config = new LwjglApplicationConfiguration

  val cellSize = 10
  val frameSize = 20

  config.title = "Scala Snake"
  config.width = cellSize * frameSize
  config.height = cellSize * frameSize

  new LwjglApplication(
    SnakeGame(cellSize, frameSize, bot = Some(new SimpleBot)),
    config
  )
}
