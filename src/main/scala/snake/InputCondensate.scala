package snake

import com.badlogic.gdx.InputAdapter

class InputCondensate extends InputAdapter {
  private var keys: Seq[Int] = Seq.empty[Int]

  def list: Seq[Int] = keys.reverse

  def clear(): Unit = {
    keys = Seq.empty[Int]
  }

  override def keyDown(keycode: Int): Boolean = {
    keys = keycode +: keys
    true
  }
}
