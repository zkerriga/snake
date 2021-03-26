package snake.entities

case class Snake(body: List[Point], direction: Direction) {
  def move(): Snake = {
    val point = direction match {
      case Up => body.head.copy(y = body.head.y + 1)
      case Down => body.head.copy(y = body.head.y - 1)
      case Left => body.head.copy(x = body.head.x - 1)
      case Right => body.head.copy(x = body.head.x + 1)
    }
    copy(body = point :: body.filter(p => p != body.last))
  }

  def turn(direction: Direction): Snake = {
    copy(direction = direction)
  }

  def eat(food: Food): Snake = {
    copy(body = food.body :: body)
  }

  def canEat(food: Food): Boolean = {
    food.body == body.head
  }

  def headIsIn(frame: Frame): Boolean = {
    body.head.x < frame.max.x &&
      body.head.y < frame.max.y &&
      body.head.x > frame.min.x &&
      body.head.y > frame.min.y
  }

  def isBitTail() = {
    body.tail.exists(p => p == body.head)
  }
}
