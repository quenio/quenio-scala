package quenio.locale.role

import org.scalatest._
import org.scalatest.matchers._
import quenio.locale.model._

object SpecReplacer extends Spec with ShouldMatchers {
  
  describe("Replacer") {
    
    it("can replace a string with another in the values of properties.") {
      val escapedChar = "\\u000A";
      val newLine = "\\n";
      val lines = List(
        Break,
        new Comment("Some comment with " + escapedChar + "..."),
        new Property("propname=propval" + escapedChar + "moreval"),
        new Property("propname=propval2"),
        Break
      )
      
      val replacedLines = Replacer.replace(lines, escapedChar, newLine)
      
      replacedLines should have length (5)
      replacedLines(0) should be (lines(0))
      replacedLines(1) should be (lines(1))
      replacedLines(2).raw should be ("propname=propval" + newLine + "moreval")
      replacedLines(3) should be (lines(3))
      replacedLines(4) should be (lines(4))
    }
    
  }

}