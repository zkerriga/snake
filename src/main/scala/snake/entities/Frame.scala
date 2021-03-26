package snake.entities

trait Frame {
  def points: Seq[Point]
  def getRandomPoint: Point
}
