package snake

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.graphics.{Color, GL20}
import com.badlogic.gdx.{Game, Gdx}

class SnakeGame(var game: entities.Game, val sizeMultiplayer: Float) extends Game {
  lazy val prs = new InputCondensate
  lazy val shapeRenderer: ShapeRenderer = new ShapeRenderer()

  override def create(): Unit = Gdx.input.setInputProcessor(prs)
  override def render(): Unit = {
    game = game
      .handle(prs.list.map {
        case Keys.UP => entities.Up
        case Keys.DOWN => entities.Down
        case Keys.LEFT => entities.Left
        case Keys.RIGHT => entities.Right
      })
      .update(Gdx.graphics.getDeltaTime)

    prs.clear()
    Gdx.gl.glClearColor(1, 1, 1, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    shapeRenderer.setColor(Color.BLACK)
    shapeRenderer.begin(ShapeType.Filled)
    for (p <- game.points)
      shapeRenderer.circle(p.x * sizeMultiplayer, p.y * sizeMultiplayer, sizeMultiplayer / 2)
    shapeRenderer.end()
  }
}
