package snake.entities

import scala.util.Random.between

case class SquareFrame(size: Int) extends Frame {
  private val last: Int = size - 1

  override val points: Seq[Point] = {
    Point(0, 0) +: Point(0, last) +: Point(last, 0) +: Point(last, last) +:
      (1 until last).foldLeft(Seq.empty[Point]) {
        (points, i) =>
          Point(i, 0) +: Point(0, i) +: Point(i, last) +: Point(last, i) +: points
      }
  }

  override def getRandomPoint: Point = Point(between(1, last), between(1, last))

  override def collision(point: Point): Boolean = point match {
    case Point(x, y) if x == 0 || y == 0 || x == last || y == last => true
    case _ => false
  }
}