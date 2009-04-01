package quenio.locale.role

import quenio.locale.model._
import quenio.locale.repository._

trait Replacer extends LocaleRepository {

  def replace(origStr: String, newStr: String) {
    val newLocales = locales.map(locale => 
      new Locale(locale.code, Replacer.replace(locale.lines, origStr, newStr)))
    replaceWith(newLocales)
  }

}

object Replacer {
  
  def replace(lines: List[Line], origStr: String, newStr: String): List[Line] = 
    lines.map(line => replace(line, origStr, newStr))
  
  private def replace(line: Line, origStr: String, newStr: String): Line = line match {
    case Property(name, value) => {
      if (value contains origStr) {
        new Property(name + "=" + value.replace(origStr, newStr))
      } else {
        line
      }
    } 
    case _ => line
  }
  
}