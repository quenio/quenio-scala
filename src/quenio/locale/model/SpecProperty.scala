package quenio.locale.model

import org.scalatest._
import org.scalatest.matchers._

object SpecProperty extends Spec with ShouldMatchers {
  
  describe("Property") {
    
    val property = new Property("myname=myval")

    it("has name.") {
      assert(property.name == "myname")
    }
    
    it("has value.") {
      assert(property.value == "myval")
    }
    
    it("requires name") {
      intercept[IllegalArgumentException] {
        new Property(null)
      }
    }

    it("requires value") {
      intercept[IllegalArgumentException] {
        new Property("myname")
      }
    }

    it("can match items in a list of Property instances") {
      val List(Property(name, value)) = List(new Property("propname=propval"))
      name should be ("propname")
      value should be ("propval")
    }
    
    it("can match items in a list of Line instances") {
      val List(Property(name, value), Break) = List(new Property("propname=propval"), Break)
      name should be ("propname")
      value should be ("propval")
    }

  }
  
  describe("Property construction") {
    
    it("requires name") {
      intercept[IllegalArgumentException] {
        Property(null, "myval")
      }
    }
    
    it("requires value") {
      intercept[IllegalArgumentException] {
        Property("myname", null)
      }
    }
    
    it("yields instance with name and value") {
      val property = Property("myname", "myval")
      assert(property.name == "myname")
      assert(property.value == "myval")
    }

  }

}
