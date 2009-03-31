package quenio.locale.model

import org.scalatest._
import org.scalatest.matchers._

object SpecLocale extends Spec with ShouldMatchers {
  
  describe("Locale") {

    it("requires locale code") {
      intercept[IllegalArgumentException] {
        new Locale(null, null)
      }
    }

    it("requires all lines") {
      intercept[IllegalArgumentException] {
        new Locale("en", null)
      }
    }
 
    it("has immutable lines") {
      val locale = new Locale("en", new Line("first") :: Nil) 
      assert(locale.lines(0).raw == "first")
    }
 
    it("has properties") {
      val locale = new Locale("en", new Property("prop=val") :: Nil)
      locale("prop") should be ("val")
    }
    
  }
  
}