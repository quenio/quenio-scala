package quenio.locale.model

class Comment(rawText: String) extends Line(rawText) {
	val text = raw.trim.drop(1).trim
}
