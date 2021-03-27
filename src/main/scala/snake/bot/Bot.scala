package snake.bot

import snake.entities.{Direction, Game, Snake}

trait Bot {
  def chooseDirection(game: Game, snake: Snake): Direction
}
