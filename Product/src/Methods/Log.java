package Methods;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//this class logs executed steps
public class Log {
//	Prints a log of all the information, directions, when using the Arm
	public void printlog(String content) throws IOException {
		Date d = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("E dd/MM/yyyy 'at' hh:mm:ss a zzz");
		String log = ft.format(d) + ": " + content;
		FilesUtil.writeOverTextFile("Log.txt", log);
	}
}