package snake

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.Input.Keys

import snake.entities.Direction

class InputCondensate extends InputAdapter {
  private var keys: Seq[Int] = Seq.empty[Int]

  def getDirections: Seq[Direction] = keys.map {
    case Keys.UP => entities.Up
    case Keys.DOWN => entities.Down
    case Keys.LEFT => entities.Left
    case Keys.RIGHT => entities.Right
  }

  def clear(): Unit = {
    keys = Seq.empty[Int]
  }

  override def keyDown(keycode: Int): Boolean = {
    keys = keycode +: keys
    true
  }
}
