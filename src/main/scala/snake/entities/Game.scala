package snake.entities

case class Game(food: Food, snake: Snake, frame: Frame, elapsedTime: Float, start: Snake) {
  def handle(input: Seq[Direction]): Game = {
    if (input.isEmpty) {
      this
    }
    else {
      copy(snake = input.foldLeft(snake)(
        (snake, direction) => snake.turn(direction)
      ))
    }
  }

  def update(deltaTime: Float): Game = {
    val elapsed = elapsedTime + deltaTime
    if (elapsed > 0.06) {
      val game = copy(snake = snake.move, elapsedTime = 0)
      if (game.snake.isHeadbutt(frame) || game.snake.isBitTail) {
        game.reset()
      }
      else if (game.snake.canEat(food)) {
        game.copy(snake = snake.eat(food), food = food.moveRandomIn(frame))
      }
      else {
        game
      }
    } else {
      copy(elapsedTime = elapsed)
    }
  }

  def reset(): Game = copy(snake = start)

  def points: Seq[Point] = food.body +: snake.body ++: frame.points
}
