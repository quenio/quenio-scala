package quenio.locale.model

class Property(rawText: String) extends Line(rawText) {
  
  private var _name: String = null
  private var _value: String = null
  
  raw match {
    case Property(name, value) => {
      _name = name
      _value = value
    }
    
  }
  
  require(_value != null, "Property requires value.")
  
  val name = _name
  val value = _value
}

object Property {
  
  def apply(name: String, value: String) = {

    require(name != null, "The 'name' parameter is required.")
    require(value != null, "The 'value' parameter is required.")
    
    new Property(name + "=" + value)
  }
  
  def unapply(str: String): Option[(String, String)] = { 
    val parts = str split "=" 
    if (parts.length == 2) 
      Some(parts(0), parts(1)) 
    else 
      Some(str, null) 
  }
}
