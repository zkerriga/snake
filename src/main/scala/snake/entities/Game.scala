package snake.entities

case class Game(food: Food, snake: Snake, frame: Frame, elapsedTime: Float, start: Snake) {
  val framePoints: List[Point] = frame.points.toList

  def handle(input: List[Direction]): Game = {
    if (input.isEmpty) {
      this
    } else {
      copy(snake = input.foldLeft(snake)((s, d) => s.turn(d)))
    }
  }

  def update(deltaTime: Float): Game = {
    val elapsed = elapsedTime + deltaTime
    if (elapsed > 0.1) {
      val game = copy(snake = snake.move(), elapsedTime = 0)
      if (!game.snake.headIsIn(frame)) {
        game.reset()
      } else if (game.snake.isBitTail()) {
        game.reset()
      } else if (game.snake.canEat(food)) {
        game.copy(snake = snake.eat(food), food = food.moveRandomIn(frame))
      } else {
        game
      }
    } else {
      copy(elapsedTime = elapsed)
    }
  }

  def reset(): Game = copy(snake = start)

  def points: List[Point] = {
    (food.body :: snake.body) ::: framePoints
  }
}
