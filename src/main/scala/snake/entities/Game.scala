package snake.entities

import com.badlogic.gdx.graphics.Color
import scala.util.Random.nextInt

case class Game(
  food: Food,
  snakes: Set[Snake],
  frame: Frame,
  private val elapsedTime: Float,
  private val start: Set[Snake],
  private val speedK: Int
) {
  def handleTurn(map: Map[Snake, Direction]): Game = map.foldLeft(this) {
      (acc, pair) =>
        acc.copy(snakes = (acc.snakes - pair._1) + pair._1.turn(pair._2))
    }

  private def fullUpdate: Game = {
    snakes.foldLeft(this) {
      (acc, snake) =>
        val movedSnake = snake.move
        val game = acc.copy(snakes = (acc.snakes - snake) + movedSnake)
        if (movedSnake.isHeadbutt(frame) || movedSnake.isBitTail) {
          game.copy(snakes = game.snakes - movedSnake)
        }
        else if (movedSnake.canEat(food)) {
          game.copy(
            snakes = (game.snakes - movedSnake) + movedSnake.eat(food),
            food = Food(frame.getRandomPoint)
          )
        }
        else {
          game
        }
    }
  }
  def update(deltaTime: Float): Game = {
    val elapsed = elapsedTime + deltaTime
    if (elapsed > 0.1 / speedK) {
      fullUpdate.copy(elapsedTime = 0)
    } else {
      copy(elapsedTime = elapsed)
    }
  }
}

object Game {
  def create(size: Int, speed: Int): Game = {
    assert(size > 8)

    val frame = SquareFrame(size + 1)
    val food = Food(frame.getRandomPoint)
    val snakes: Set[Snake] = (1 to 4).foldLeft(Set.empty[Snake])(
      (acc, i) => acc + Snake(frame.getRandomPoint +: Nil, Up, getRandomColor)
    )
    Game(food, snakes = snakes, frame, elapsedTime = 0, start = snakes, speed)
  }

  private def getRandomColor: Color = nextInt() % 8 match {
    case 0 => Color.BLUE
    case 1 => Color.RED
    case 2 => Color.PURPLE
    case 3 => Color.BLACK
    case 4 => Color.BROWN
    case 5 => Color.GOLD
    case 6 => Color.MAGENTA
    case _ => Color.GREEN
  }
}
