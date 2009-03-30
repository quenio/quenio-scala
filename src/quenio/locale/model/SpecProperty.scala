package quenio.locale.model

import org.scalatest._

object SpecProperty extends Spec {
  
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
