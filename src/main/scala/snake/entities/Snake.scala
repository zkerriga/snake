package snake.entities

case class Snake(body: Seq[Point], direction: Direction) {
  def move: Snake = {
    val point = direction match {
      case Up => body.head.copy(y = body.head.y + 1)
      case Down => body.head.copy(y = body.head.y - 1)
      case Left => body.head.copy(x = body.head.x - 1)
      case Right => body.head.copy(x = body.head.x + 1)
    }
    copy(body = point +: body.init)
  }

  def turn(direction: Direction): Snake = copy(direction = direction)

  def eat(food: Food): Snake = copy(body = food.body +: body)

  def canEat(food: Food): Boolean = food.body == body.head

  def isHeadbutt(frame: Frame): Boolean = frame.points.contains(body.head)

  def isBitTail: Boolean = body.tail.contains(body.head)
}
