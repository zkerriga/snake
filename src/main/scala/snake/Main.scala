package snake

import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}
import scala.util.Random

object Main extends App {
  val config = new LwjglApplicationConfiguration
  config.title = "Scala Snake"
  config.width = 300
  config.height = 300
  val food = entities.Food(entities.Point(4, 4), new Random())
  val frame = entities.Frame(entities.Point(0, 0), entities.Point(30, 30))
  val snake = entities.Snake(entities.Point(5, 5) :: entities.Point(6, 6) :: entities.Point(7, 7) :: Nil, entities.Right)
  val game = entities.Game(food, snake, frame, 0, snake)
  new LwjglApplication(new SnakeGame(game, 10), config)
}
