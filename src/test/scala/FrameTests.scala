import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import snake.entities.SquareFrame

class FrameTests extends AnyFlatSpec with Matchers {
  "points" should "have correct size" in {
    val squareFrame3 = SquareFrame(3)
    squareFrame3.points.size shouldBe 8

    val squareFrame10 = SquareFrame(10)
    squareFrame10.points.size shouldBe 36
  }
}
