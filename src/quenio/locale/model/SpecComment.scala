package quenio.locale.model

import org.scalatest._
import org.scalatest.matchers._

object SpecComment extends Spec with ShouldMatchers {
  
  describe("Comment") {

    it("has text.") {
      val text = "Some comment text..."
      assert(new Comment("# " + text).text == text, "checking prefix '# '")
      assert(new Comment("#" + text).text == text, "checking prefix '#'")
      assert(new Comment(" #" + text).text == text, "checking prefix ' #'")
    }
    
    it("can match items in a list of Comment instances") {
      val List(Comment(text)) = List(new Comment("# some text"))
      text should equal ("some text")
    }
    
    it("can match items in a list of Line instances") {
      val List(Break, Comment(text)) = List(Break, new Comment("# some text"))
      text should equal ("some text")
    }
    
  }

}

