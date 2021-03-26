package snake

import com.badlogic.gdx.InputAdapter


class InputCondensate extends InputAdapter {
  type Key = Int
  private var keys: Seq[Key] = Seq.empty[Key]

  def getKeys: Seq[Key] = keys

  def clear(): Unit = {
    keys = Seq.empty[Key]
  }

  override def keyDown(keycode: Key): Boolean = {
    keys = keycode +: keys
    true
  }
}
