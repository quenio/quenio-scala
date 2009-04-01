package quenio.fs

object Directory {
  
  import quenio.exec.Exec._

  def make(dir: String): String = {
    val exitCode = exec("mkdir " + dir)
    assert(exitCode == 0, "Error " + exitCode  + " creating directory at " + dir)
    dir
  }

  def copy(sourceDir: String, targetDir: String): String = {
    val exitCode = exec("cp -R " + sourceDir + " " + targetDir);
    assert(exitCode == 0, "Error " + exitCode  + " copying directory to " + targetDir)
    targetDir
  }
  
  def remove(dir: String): String = {
    val exitCode = exec("rm -Rf " + dir)
    assert(exitCode == 0, "Error " + exitCode  + " removing directory from " + dir)
    dir
  }
  
  def cleanCopy(sourceDir: String, targetDir: String): String = {
    exec("mkdir " + targetDir) // Ensures directory exists to avoid error in the next operation.
    remove(targetDir)
    copy(sourceDir, targetDir)
    targetDir
  }

}