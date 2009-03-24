
import scala.collection.immutable._;

class CommandApp[ParamType](commandParam: ParamType) {
	
	var appName: String = null
	var appArgs: Array[String] = null
	var commands: Map[String, (Array[String], ParamType) => Unit] = null

	def start() {
		assert(appName != null, "Define the application name: appName")
		assert(appArgs != null, "Define the application arguments: appArgs")
		assert(commands != null, "Define the commands supported by the application")
		if (appArgs.length > 0) {
		  val command = appArgs(0)
		  if (commands.isDefinedAt(command)) {
			commands(command).apply(appArgs, commandParam)
		  } else {
			println("Unknown command: " + command)
		  }
		} else {
			println("Please specify a command. Ex.: " + appName + " <command>")
		}
	}
	
}

