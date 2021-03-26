package snake.bot

import snake.entities.{Direction, Game, Point}
import snake.entities

class SimpleBot extends Bot {
  private val directions = Seq(
    entities.Up, entities.Down, entities.Left, entities.Right
  )

  private def analyze(game: Game, direction: Direction): Either[String, (Direction, Double)] = {
    if (game.snake.direction == direction.reverse) {
      Left("no sense")
    }
    else {
      val snake = game.snake.turn(direction).move
      if (snake.isBitTail || snake.isHeadbutt(game.frame)) {
        Left("fail")
      }
      else {
        Right(direction, Point.distanceSquare(snake.body.head, game.food.body))
      }
    }
  }

  override def chooseDirection(game: Game): Direction = {
    val sortedSeq = directions
      .map(analyze(game, _))
      .collect{ case Right(pair) => pair }
      .sortBy(_._2)

    sortedSeq match {
      case head +: _ => head._1
      case _ => entities.Up
    }
  }
}
