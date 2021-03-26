package snake.bot

import snake.entities.{Direction, Game, Up}

class StupidBot extends Bot {
  override def chooseDirection(game: Game): Direction = Up
}
