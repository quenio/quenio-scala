package quenio.locale.repository

import org.scalatest._
import org.scalatest.matchers._
import quenio.locale.model._
import quenio.locale.roles._

object SpecLeveler extends Spec with ShouldMatchers {
  
  val commentLine = "# My comment..."
  val propertyLine = "myprop=myval"
  val extraProperty = "exprop="

  val en = new Locale(
    "en", 
    List(
      new Comment(commentLine),
      new Property(propertyLine + "_en"),
      new Property(extraProperty + "ex"),
      Break
    )
  )
  
  val de = new Locale(
    "de",
    List(
      new Property(propertyLine + "_de")
    )
  )
  
  val fr = new Locale(
    "fr_CA",
    List(
      new Comment("Any comment..."),
      Break,
      new Property(propertyLine + "_fr_CA")
    )
  )

  describe("Lever") {
    
    it("can level one set of locales based on a specified locale") {
      val leveled = Leveler.level(en, List(de, fr))
      leveled should have length(2)
      leveled(0) should not equal (de)
      leveled(0).code should be (de.code)
      checkLines(de.code, leveled(0).lines)
      checkLines(fr.code, leveled(1).lines)
    }
    
  }

  def checkLines(localeCode: String, lines: List[Line]) {
    lines should have length (4) 

    lines(0).raw should equal (commentLine)
    lines(1).raw should equal (propertyLine + "_" + localeCode)
    lines(2).raw should equal (extraProperty + "(" + localeCode + ") ex")
    lines(3) should equal (Break)

    lines(0) should equal (en.lines(0)) // Should reuse Comment object
    lines(1).getClass.getName should equal ("quenio.locale.model.Property")
    lines(2).getClass.getName should equal ("quenio.locale.model.Property")
  }

}