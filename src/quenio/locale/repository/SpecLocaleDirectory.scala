package quenio.locale.repository

import org.scalatest._
import org.scalatest.matchers._
import quenio.locale.model._
import quenio.fs._

object SpecLocaleDirectory extends Spec with ShouldMatchers {
  
  describe("The test edition of LocaleDirectory") {

    var homeDir = System.getProperty("user.home")
    var scalaDir = homeDir + "/scala"
    var dirName = "test-repository"
    val originDir =  scalaDir + "/var/" + dirName
    val tmpDir = scalaDir + "/tmp/" + dirName
    val directory = new LocaleDirectory(Directory.cleanCopy(originDir, tmpDir))
    
    it("can map Locale instances from their code.") {
      directory("en").code should equal ("en")
      directory("fr_CA").code should equal ("fr_CA")
    }
 
    it("contains English locale's contents") {
      val english = directory("en")
      english.code should equal ("en")
      english.lines should have length (3)
      val List(Break, Comment(text), Property(name, value)) = english.lines
      text should be ("Simple English comment...")
      name should be ("enpropname")
      value should be ("enpropval")
    }

    it("contains French locale's contents") {
      val french = directory("fr_CA")
      french.code should equal ("fr_CA")
      french.lines should have length (6)
      val List(Comment(c1), Break, Property(name, value), Break, Comment(c2), Comment(c3)) = 
        french.lines
      c1 should be ("Simple French comment...")
      name should be ("depropname")
      value should be ("")
      c2 should be ("---")
      c3 should be ("................... =Some text...")
    }
    
    it("can replace an existing set of locales with a new one.") {

      val originalEnglish = directory("en")
      val originalFrench = directory("en")
      val newFrench = new Locale("fr_CA", List(new Property("newprop=newval")))

      directory.replaceWith(List(newFrench))

      // Checking if locales not replaced remain and if new locales changed as expected.
      directory("en") should be (originalEnglish)
      directory("fr_CA") should not (be (originalFrench))
      directory("fr_CA").lines(0).raw should be (newFrench.lines(0).raw)

      // Checking file system's contents with a branch new instance.
      val newDirectory = new LocaleDirectory(tmpDir)
      newDirectory("en").lines(0).raw should be (originalEnglish.lines(0).raw)
      newDirectory("fr_CA").lines(0).raw should be (newFrench.lines(0).raw)

    }

  }
  
}