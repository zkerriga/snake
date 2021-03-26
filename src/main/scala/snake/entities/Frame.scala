package snake.entities

case class Frame(min: Point, max: Point) {
  def points: Seq[Point] = {
    for (i <- min.x until max.x + 1;
         j <- min.y until max.y + 1
         if i == min.x ||
           i == max.x ||
           j == min.y ||
           j == max.y)
      yield Point(i, j)
  }
}

