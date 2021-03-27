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
    renderFrame(game)
    renderFood(game)
    renderSnakes(game)
  }

  private def renderSnakes(game: entities.Game): Unit = Seq(game.snake).foreach{
    snake =>
      shapeRenderer.setColor(Color.PURPLE)
      shapeRenderer.begin(ShapeType.Filled)
      for (p <- snake.body)
        shapeRenderer.circle(p.x * cellSize, p.y * cellSize, cellSize / 2)
      shapeRenderer.end()
  }

  private def renderFrame(game: entities.Game): Unit = {
    shapeRenderer.setColor(Color.BLACK)
    shapeRenderer.begin(ShapeType.Line)
    for (p <- game.frame.points)
      shapeRenderer.rect(p.x * cellSize - cellSize / 2, p.y * cellSize - cellSize / 2, cellSize, cellSize)
    shapeRenderer.end()
  }

  private def renderFood(game: entities.Game): Unit = {
    shapeRenderer.setColor(Color.RED)
    shapeRenderer.begin(ShapeType.Filled)
    val p = game.food.point
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