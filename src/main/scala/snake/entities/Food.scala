package snake.entities

import scala.util.Random

case class Food(body: Point, random: Random) {
  def moveRandomIn(frame: Frame): Food = {
    val x = random.between(frame.min.x + 1, frame.max.x)
    val y = random.between(frame.min.y + 1, frame.max.y)
    copy(body = Point(x, y))
  }
}
