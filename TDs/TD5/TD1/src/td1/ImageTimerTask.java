package td1;


//import java.util.Timer;
import java.util.TimerTask;

public class ImageTimerTask extends TimerTask{
	
	public static Console consolehere;
	public static int imagedebut = 0;
	public static int imagenow = 0;
	
	public ImageTimerTask(Console c){
		consolehere = c;
	}
	
	@Override
	public void run(){
		String image = "src/image"+ String.valueOf(imagenow) + ".jpg";
		consolehere.setImage(image);
		imagenow++;
		if (imagenow == 9)
			imagenow = imagedebut;
	}
}
