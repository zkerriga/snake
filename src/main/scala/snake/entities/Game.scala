package snake.entities

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
        acc.copy(snakes = (snakes - pair._1) + pair._1.turn(pair._2))
    }

  private def fullUpdate: Game = {
    snakes.foldLeft(this) {
      (acc, snake) =>
        val movedSnake = snake.move
        val game = acc.copy(snakes = (acc.snakes - snake) + movedSnake)
        if (movedSnake.isHeadbutt(frame) || movedSnake.isBitTail) {
          game.copy(snakes = snakes - movedSnake)
        }
        else if (movedSnake.canEat(food)) {
          game.copy(
            snakes = snakes - movedSnake + movedSnake.eat(food),
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

  def reset(): Game = copy(snakes = start)
}

object Game {
  def create(size: Int, speed: Int): Game = {
    assert(size > 8)

    val frame = SquareFrame(size + 1)
    val food = Food(frame.getRandomPoint)
    val snakes: Set[Snake] = (1 to 4).foldLeft(Set.empty[Snake])(
      (acc, i) => acc + Snake(Point(i, i) +: Nil, Up)
    )
    Game(food, snakes = snakes, frame, elapsedTime = 0, start = snakes, speed)
  }
}
