package quenio.locale.repository

import org.scalatest._
import org.scalatest.matchers._
import quenio.locale.model._

object SpecLocaleRepository extends Spec with ShouldMatchers {
  
  describe("LocaleRepository") {

    val dirPath = System.getProperty("user.home") + "/scala/var/test-repository"
    val repository = new LocaleRepository(dirPath)

    it("contains Locale objects") {
      repository("en").code should equal ("en")
      repository("fr_CA").code should equal ("fr_CA")
    }
 
    it("contains English locale's contents") {
      val english = repository("en")
      english.code should equal ("en")
      english.lines should have length (3)
      val List(Break, Comment(text), Property(name, value)) = english.lines
      text should be ("Simple English comment...")
      name should be ("enpropname")
      value should be ("enpropval")
    }

    it("contains French locale's contents") {
      val french = repository("fr_CA")
      french.code should equal ("fr_CA")
      french.lines should have length (5)
      val List(Comment(c1), Break, Property(name, value), Break, Comment(c2)) = french.lines
      c1 should be ("Simple French comment...")
      name should be ("depropname")
      value should be ("")
      c2 should be ("---")
    }

  }
  
}