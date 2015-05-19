//imports
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * This is the drawing surface. It will be instanced at the beggining, and have a variable class known as screen.
 * To save memory, the drawing screen will never be modified, just the commands that the drawing screen carries out.
 * The class arguments screen will have three manditory methods: draw() , input[to be determined still] , gameloop().
 * This screen will also call an image loader, which will take an array of strings, and create images out of them. while this is happening,
 * the painted-screen will be displaying a loading indicator, which will inform the user that the screen is not yet ready for use, 
 * until the images have been created and passed as an argument.
 */
public class DrawSurface extends JPanel implements MouseListener,MouseMotionListener
{
	
	//the Main screen argument
	static Screen activeScreen;
	
	//main constructor
	public DrawSurface()
	{
		//make the drawing surface visible
		setVisible(true);
		//set the active Screen (as default) to the title screen
		activeScreen = new TitleScreen();
		//activate the mouse listener
		addMouseListener(this);
		addMouseMotionListener(this);
		//start the update timer
		update.start();
	}
	
	//the main drawing method. will take a command from the screen argument
	public void paintComponent(Graphics g)
	{
		//call to the graphics object argument
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, Main.tool.getScreenSize().width, Main.tool.getScreenSize().height);
		
		activeScreen.draw(g);
	}
	
	//the main gameloop method. most of the action happens here.
	//will take a command from the screen argument
	public void gameloop()
	{
		activeScreen.gameloop();
		repaint();
	}
	
	//INPUT

	//Mouse Input
	
	public void mouseEntered( MouseEvent e ) //mouse entered
	{
		//activeScreen.MouseEntered(e.getX(), e.getY());
	}
	public void mouseExited( MouseEvent e ) //mouse excited
	{
	}
	public void mouseClicked( MouseEvent e ) //mouse clicked - most action will happen here
	{
		activeScreen.MouseClicked(e.getX(),e.getY());
	}
	public void mousePressed( MouseEvent e ) //mouse pressed
	{
	}
	public void mouseReleased( MouseEvent e ) //mouse released - accompanies mouse clicked
	{  
	}
	public void mouseMoved(MouseEvent e) //if the mouse was moved
	{
		activeScreen.MouseMoved(e.getX(), e.getY());
	}
	public void mouseDragged(MouseEvent e) //if the mouse was clicked and moved (dragged)
	{
	}
	
	//UPDATE TIMER
	Timer update = new Timer(1,new ActionListener(){public void actionPerformed(ActionEvent e){
		gameloop();
	}});
}
