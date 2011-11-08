package quenio.locale.role

import org.scalatest._
import org.scalatest.matchers._
import quenio.locale.model._

object SpecPrepender extends Spec with ShouldMatchers {
  
  describe("Prepender") {
    
    it("can insert a comment at the begining of the locale file.") {
      val lines = List(
        new Property("propname1=propval1"),
        new Property("propname2=propval2"),
        Break
      )
      val comment = "Some comment..."
      
      val replacedLines = Prepender.prepend(lines, comment)
      
      replacedLines should have length (4)
      replacedLines(0).raw should be (Comment(comment).raw)
      replacedLines(1) should be (lines(0))
      replacedLines(2) should be (lines(1))
      replacedLines(3) should be (lines(2))
    }
    
  }

}