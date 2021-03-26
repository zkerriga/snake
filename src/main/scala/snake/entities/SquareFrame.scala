package snake.entities

import scala.util.Random.between

case class SquareFrame(size: Int) extends Frame {
  override val points: Seq[Point] = {
    val last = size - 1
    Point(0, 0) +: Point(0, last) +: Point(last, 0) +: Point(last, last) +:
      (1 until last).foldLeft(Seq.empty[Point]) {
        (points, i) =>
          Point(i, 0) +: Point(0, i) +: Point(i, last) +: Point(last, i) +: points
      }
  }

  override def getRandomPoint: Point = Point(between(1, size), between(1, size))
}