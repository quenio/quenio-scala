package quenio.locale.model

import org.scalatest._

object SpecComment extends Spec {
  
  describe("Comment") {

    it("has text.") {
      val text = "Some comment text..."
      assert(new Comment("# " + text).text == text, "checking prefix '# '")
      assert(new Comment("#" + text).text == text, "checking prefix '#'")
      assert(new Comment(" #" + text).text == text, "checking prefix ' #'")
    }
    
  }

}

