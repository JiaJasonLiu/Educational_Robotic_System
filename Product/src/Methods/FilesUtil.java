package Methods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
//this class has methods using random access files
public class FilesUtil {

//	Reads the text document as a string
	public static String readTextFile(String fileName) throws IOException {
		String content = new String(Files.readAllBytes(Paths.get(fileName)));
		return content;
	}

//	Reads the text document as a string, but by lines
	public static List<String> readTextFileByLines(String fileName) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(fileName));
		return lines;
	}

//	Writes a String into the text document, and saves it at the directory
	public static void writeToTextFile(String fileName, String content) throws IOException {
		Files.deleteIfExists(Paths.get(fileName));
		Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
	}

//	Adds a new String in an existing file
	public static void writeOverTextFile(String fileName, String content) throws IOException {
		content = content + "\r\n\r\n";
		Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.APPEND);
	}

//	Checks if the file exists, if not, then create the file
	public static void fileExist(String fileName) throws IOException {
		Path path = Paths.get(fileName);
		boolean pathExists = Files.exists(path);
		if (!pathExists) {
			FilesUtil.writeToTextFile(fileName, "");
		}
	}
}
