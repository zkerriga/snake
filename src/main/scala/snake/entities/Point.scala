package snake.entities

case class Point(x: Int, y: Int)

object Point {
  def distanceSquare(p1: Point, p2: Point): Double = {
    (p1.x - p2.x) ^ 2 + (p1.y - p2.y) ^ 2
  }
}
