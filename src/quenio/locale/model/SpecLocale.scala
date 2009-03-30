package quenio.locale.model

import org.scalatest._

object SpecLocale extends Spec {
  
  describe("Locale") {

    it("requires all lines") {
      intercept[IllegalArgumentException] {
        new Locale(null)
      }
    }
 
    it("has immutable lines") {
      val locale = new Locale(new Line("first") :: Nil) 
      assert(locale.lines(0).raw == "first")
    }
 
  }
  
}