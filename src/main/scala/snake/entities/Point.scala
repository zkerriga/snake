package snake.entities

case class Point(x: Int, y: Int)

object Point {
  def distanceSquare(p1: Point, p2: Point): Double =
    scala.math.hypot(p2.x - p1.x, p2.y - p2.y)
}
