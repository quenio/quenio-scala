package quenio.locale.model

import org.scalatest._

object SpecLine extends Spec {
  
  describe("Line") {

    it("requires raw text") {
      intercept[IllegalArgumentException] { 
        new Line(null)
      }
    }

    it("has only raw text") {
      val text = "raw text"
      assert(new Line(text).raw == text)
    }
  
  }

}

