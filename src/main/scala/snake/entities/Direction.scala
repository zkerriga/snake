package snake.entities

import com.badlogic.gdx.Input.Keys

sealed trait Direction {
  val reverse: Direction
}
case object Up extends Direction {
  override val reverse: Direction = Down
}
case object Down extends Direction {
  override val reverse: Direction = Up
}
case object Right extends Direction {
  override val reverse: Direction = Left
}
case object Left extends Direction {
  override val reverse: Direction = Right
}

object Direction {
  def keysToDirections(keys: Seq[Int]): Seq[Direction] = keys.collect {
    case Keys.UP => Up
    case Keys.DOWN => Down
    case Keys.LEFT => Left
    case Keys.RIGHT => Right
  }
}