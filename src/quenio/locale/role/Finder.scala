package quenio.locale.role

import quenio.locale.model._
import quenio.locale.repository._

trait Finder extends LocaleRepository {

  def find(localeCode: String, str: String): List[Line] =
    Finder.find(apply(localeCode).lines, str)
  
}

object Finder {
  
  def find(lines: List[Line], str: String) = lines.filter(_.raw contains str)
  
}