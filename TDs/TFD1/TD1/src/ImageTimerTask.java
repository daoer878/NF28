import java.util.TimerTask;


public class ImageTimerTask extends TimerTask{
	private static int current_index = 0;
	private static int max_index = 9;
	public static Console my_console;
	public ImageTimerTask(Console c) {
		my_console = c;
	}

	@Override
	public void run() {
		my_console.setImage("src/images/image" + String.valueOf(current_index) + ".jpg");
		current_index = ++current_index % max_index;
	}

}
