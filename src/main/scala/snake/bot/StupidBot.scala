package snake.bot

import snake.entities.{Direction, Game, Snake, Up}

class StupidBot extends Bot {
  override def chooseDirection(game: Game, snake: Snake): Direction = Up
}
