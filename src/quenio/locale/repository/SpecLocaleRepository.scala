package quenio.locale.repository

import org.scalatest._
import org.scalatest.matchers._
import quenio.locale.model._

object SpecLocaleRepository extends Spec with ShouldMatchers {
  
  describe("LocaleRepository") {

    it("contains Locale objects") {
      val repository = new LocaleRepository(".")
      repository("en").code should equal ("en")
      repository("de").code should equal ("de")
    }
 
    it("contains English locale's contents") {
      val repository = new LocaleRepository(".")
      val english = repository("en")
      english.code should equal ("en")
      english.lines should have length (3)
      val List(Break, Comment(text), Property(name, value)) = english.lines
      text should be ("Simple English comment...")
      name should be ("enpropname")
      value should be ("enpropval")
    }

    it("contains German locale's contents") {
      val repository = new LocaleRepository(".")
      val german = repository("de")
      german.code should equal ("de")
      german.lines should have length (4)
      val List(Comment(text), Break, Property(name, value), Break) = german.lines
      text should be ("Simple German comment...")
      name should be ("depropname")
      value should be ("depropval")
    }

  }
  
}