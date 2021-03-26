package snake.entities

import scala.util.Random

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

object Game {
  def create(width: Int, height: Int): Game = {
    assert(width > 10 && height > 10)

    val food = Food(Point(width / 2, width / 2), new Random())
    val frame = Frame(Point(0, 0), Point(width, height))
    val snake = Snake(Point(5, 5) :: Point(6, 6) :: Point(7, 7) :: Nil, Right)
    Game(food, snake, frame, elapsedTime = 0, snake)
  }
}
