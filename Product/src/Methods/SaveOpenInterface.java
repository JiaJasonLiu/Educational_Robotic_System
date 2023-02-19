package Methods;

import java.io.File;
import java.io.IOException;

//	interface, that is used to save and open text documents
public interface SaveOpenInterface {

	public void SaveAs(String s, File f);

	public String Open(File f) throws IOException;

}
