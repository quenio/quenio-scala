package quenio.locale.role

import org.scalatest._
import org.scalatest.matchers._
import quenio.locale.model._

object SpecFinder extends Spec with ShouldMatchers {
  
  describe("Finder") {
    
    it("can find a specific string in the provided list of lines.") {
      val escapedChar = "\\u000A"; // New line char
      val lines = List(
        Break,
        new Comment("Some comment with " + escapedChar + "..."),
        new Property("propname=propval" + escapedChar + "moreval"),
        new Property("propname=propval2"),
        Break
      )
      
      val foundLines = Finder.find(lines, escapedChar)
      
      foundLines should have length (2)
      foundLines(0) should be (lines(1))
      foundLines(1) should be (lines(2))
    }
    
  }

}