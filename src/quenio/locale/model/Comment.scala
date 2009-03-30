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
    if (raw startsWith "#")
      Some(new Comment(raw).text)
    else 
      None
  }

  def unapply(line: Line): Option[String] = unapply(line.raw)
  
}