import java.awt.Color;

import com.thehowtotutorial.splashscreen.JSplash;

public class SplashExample {

	public static void main(String[] args) throws InterruptedException {
		
		JSplash splash = new JSplash(SplashExample.class.getResource("/image/bookstore.jpeg"),
				true,true,false,"Ver1.0",null,Color.WHITE,Color.RED);
		splash.splashOn();
		for(int i=1; i<=100; i++) {
			splash.setProgress(i,"초기화중...");
			Thread.sleep(5);
		}
		splash.splashOff();
		
		WinMain win = new WinMain();
		win.setVisible(true);
	}
}
