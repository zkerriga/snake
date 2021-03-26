package snake.entities

case class Snake(body: Seq[Point], direction: Direction) {
  def move: Snake = {
    val head = body.head
    val movedHead = direction match {
      case Up => head.copy(y = head.y + 1)
      case Down => head.copy(y = head.y - 1)
      case Left => head.copy(x = head.x - 1)
      case Right => head.copy(x = head.x + 1)
    }
    copy(body = movedHead +: body.init)
  }

  def turn(newDirection: Direction): Snake =
    if (newDirection == direction.reverse) this
    else copy(direction = newDirection)

  def eat(food: Food): Snake = copy(body = food.body +: body)

  def canEat(food: Food): Boolean = food.body == body.head

  def isHeadbutt(frame: Frame): Boolean = frame.points.contains(body.head)

  def isBitTail: Boolean = body.tail.contains(body.head)
}
