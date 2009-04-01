package quenio.locale.model

class Comment(rawText: String) extends Line(rawText) {
	val text = raw.trim.drop(1).trim
}

object Comment {
  
  def apply(text: String) = {

    require(text != null, "The 'text' parameter is required.")
    
    new Comment("# " + text)
  }
  
  def unapply(raw: String): Option[String] = {
    val line: String = if (raw.length >= 2 && raw(1) == '#') raw.drop(1) else raw
    if (line startsWith "#")
      Some(new Comment(line).text)
    else 
      None
  }

  def unapply(line: Line): Option[String] = 
    if (line.isInstanceOf[Comment])
      unapply(line.raw)
    else
      None
}