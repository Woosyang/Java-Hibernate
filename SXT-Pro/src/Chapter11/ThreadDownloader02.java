package Chapter11;
import java.util.concurrent.*;


/**
 * Implements the Callable interface to start a new thread
 * @author Woo
 *
 */
public class ThreadDownloader02 implements Callable<Boolean> {
	private String Url;
	private String Name;
	
	// constructor
	public ThreadDownloader02(String Url, String Name) {
		this.Url = Url;
		this.Name = Name;
	}
	
	public Boolean call() throws Exception {
		PictureDownloader P = new PictureDownloader();
		P.Download(Url, Name);
		System.out.println(Name);
		return true;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ThreadDownloader02 t1 = new ThreadDownloader02("https://p0.ssl.qhimgs1.com/sdr/400__/t016ab7bcc4d1a6d29a.jpg", "Marvel01.jpg");
		ThreadDownloader02 t2 = new ThreadDownloader02("https://p5.ssl.qhimgs1.com/sdr/400__/t013ef25a57da8adabf.jpg", "Marvel02.jpg");
		ThreadDownloader02 t3 = new ThreadDownloader02("https://p2.ssl.qhimgs1.com/sdr/400__/t0148494a10afdf78c2.jpg", "Marvel03.jpg");
		
		// 1. creating an executing service
		ExecutorService Ser = Executors.newFixedThreadPool(3);
		// 2. submit the execution
		Future<Boolean> R1 = Ser.submit(t1);
		Future<Boolean> R2 = Ser.submit(t2);
		Future<Boolean> R3 = Ser.submit(t3);
		// 3. get the result
		boolean r1 = R1.get();
		boolean r2 = R2.get();
		boolean r3 = R3.get();
		System.out.println("Whether the download is successful: ");
		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r3);
		// 4. shutdown the service
		Ser.shutdownNow();
	}	
}

