package quenio.exec

object Exec {
  
  def exec(command: String): Int = {
    val p = Runtime.getRuntime().exec("/usr/bin/env " + command)
    p.waitFor
  }
  
}