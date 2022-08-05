package Chapter11;

/**
 * use Thread to download pictures
 * @author Woo
 *
 */
// implements Runnable
public class ThreadDownloader01 extends Thread {
	private String Url; // remote path
	private String Name; // the name used to store with
	
	// constructor
	public ThreadDownloader01(String Url, String Name) {
		this.Url = Url;
		this.Name = Name;
	}
	
	@Override
	public void run() {
		PictureDownloader Pd = new PictureDownloader();
		Pd.Download(Url, Name);
	}
	
	public static void main(String[] args) {
		ThreadDownloader01 t1 = new ThreadDownloader01("https://p0.ssl.qhimgs1.com/sdr/400__/t016ab7bcc4d1a6d29a.jpg", "Marvel01.jpg");
		ThreadDownloader01 t2 = new ThreadDownloader01("https://p5.ssl.qhimgs1.com/sdr/400__/t013ef25a57da8adabf.jpg", "Marvel02.jpg");
		ThreadDownloader01 t3 = new ThreadDownloader01("https://p2.ssl.qhimgs1.com/sdr/400__/t0148494a10afdf78c2.jpg", "Marvel03.jpg");
		
		// start, not execute in order
		t1.start(); 
		t2.start();
		t3.start();
		// implements Runnable
		// new Thread(t1).start();
		// new Thread(t2).start();
		// new Thread(t3).start();
	}
}