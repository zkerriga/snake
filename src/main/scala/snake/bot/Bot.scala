package snake.bot

import snake.entities.{Direction, Game}

trait Bot {
  def chooseDirection(game: Game): Direction
}
