package Chapter11;
import java.io.*;
import java.net.*;
import org.apache.commons.io.FileUtils;

/**
 * used for ThreadDownloader01 & ThreadDownloader02
 * @author Woo
 *
 */
public class PictureDownloader {
	// download method
		public void Download(String Url, String Name) {
			try {
				FileUtils.copyURLToFile(new URL(Url), new File(Name));
			} catch (MalformedURLException e) {
				e.printStackTrace();
				System.out.println("Illegal Url");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Fail to download");
			}
		}
}
