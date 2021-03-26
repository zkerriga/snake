package snake

import com.badlogic.gdx.InputAdapter

class InputCondensate extends InputAdapter {
  private var keys: List[Int] = List.empty[Int]

  def list: List[Int] = keys.reverse

  def clear(): Unit = {
    keys = List.empty[Int]
  }

  override def keyDown(keycode: Int): Boolean = {
    keys = keycode :: keys
    true
  }
}
