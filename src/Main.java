/*
 * This is a real-time-strategy game that will be made by Gabriel Green (game mechanics),
 * Paul Rusnak (graphics) and John Omeljnaiuk (programming). It's earlier  Verision consisted of LWJGL addon
 * and openGL extension, but due to many programming issues, and for simplicity's sake, the newer version will be completed
 * using Java's default UI javax.swing.
 */

//imports
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;

//the Main class. All main functions are called here
public class Main extends JFrame
{

	//Main Toolbox. used for getting images and getting screen size
	static Toolkit tool = Toolkit.getDefaultToolkit();
	
	//instance the drawing surface JPanel
	static DrawSurface panel = new DrawSurface();
	
	//the icon image
	Image icon = tool.getImage("Resources/Images/icon32.png");
	
	//the camera reference integer
	static int int_camera_refX = 0;
	static int int_camera_refY = 0;
	
	//main method. main stuff happens here
	public static void main(String[] args) 
	{
		//instance the class, creating a window
		Main window = new Main();
	}
	
	//main constructor. JFrame is created here
	public Main()
	{
		//give the window a name
		super("RTS 2.0");
		//make fullscreen
		setUndecorated(true);
		//make the window visible
		setVisible(true);
		//tell the window to close when the x button is clicked
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//set the size of the window (default toolit get's the screen's size in pixels)
		setSize(tool.getScreenSize().width,tool.getScreenSize().height);
		//add the drawing surface to the main screen
		add(panel);
		//make the cursor look cool **to be changed
		this.setCursor(1);
		//set the Icon for the game window 
		this.setIconImage(icon);
	}
}
