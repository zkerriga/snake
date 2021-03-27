package snake

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.graphics.{Color, GL20}
import com.badlogic.gdx.{Game, Gdx}

import snake.bot.Bot
import snake.entities.Direction

class SnakeGame(var game: entities.Game, val cellSize: Float, bot: Bot) extends Game {
  lazy val pressedKeys = new InputCondensate
  lazy val shapeRenderer: ShapeRenderer = new ShapeRenderer()

  override def create(): Unit = Gdx.input.setInputProcessor(pressedKeys)
  override def render(): Unit = {
    game = game
      .handleTurn(bot.chooseDirection(game))
      .update(Gdx.graphics.getDeltaTime)

    pressedKeys.clear()

    Gdx.gl.glClearColor(1, 1, 1, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    shapeRenderer.setColor(Color.BLACK)
    shapeRenderer.begin(ShapeType.Filled)
    for (p <- game.points)
      shapeRenderer.circle(p.x * cellSize, p.y * cellSize, cellSize / 2)
    shapeRenderer.end()
  }
}

object SnakeGame {
  def apply(cellSize: Int, frameSize: Int, speed: Int, bot: Bot): SnakeGame = {
    val game = entities.Game.create(frameSize, speed)
    new SnakeGame(game, cellSize, bot)
  }
}