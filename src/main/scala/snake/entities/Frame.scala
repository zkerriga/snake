package snake.entities

case class Frame(min: Point, max: Point) {
  def points: Seq[Point] = for {
    i <- min.x to max.x
    j <- min.y to max.y
    if i == min.x || i == max.x || j == min.y || j == max.y
  } yield Point(i, j) /* todo: remove this borders */
}
