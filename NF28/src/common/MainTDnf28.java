package common;

import td1.ConsoleView;

import td1.ImageView;
import td2.ListView;
import td3.TreeView;
@SuppressWarnings("unused")
public class MainTDnf28 {
	public static void main(String[] args) {
		//td1();
		td2();
		//td3();
	}
	public static void td1() {
		ConsoleView consoleView = new ConsoleView();
		ImageView imageView1= new ImageView();
		ImageView imageView2= new ImageView();
	}
	
	public static void td2() {
		ListView dict_view = new ListView("Mots");
	}
	
	public static void td3() {
		TreeView contacts = new TreeView();
	}
}
