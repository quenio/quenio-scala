package quenio.locale.role

import quenio.locale.model._
import quenio.locale.repository._

trait Prepender extends LocaleRepository {

  def prepend(comment: String) {
    val newLocales = locales.map(locale => 
      new Locale(locale.code, Prepender.prepend(locale.lines, comment)))
    replaceWith(newLocales)
  }

}

object Prepender {
  
  def prepend(lines: List[Line], comment: String): List[Line] = Comment(comment) :: lines
  
}