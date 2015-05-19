//imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/*
 * This class is the first screen. it will contain all necessary information to create an effective title screen
 */

class TitleScreen extends Screen
{
	//the images array
	Image Sprites[];
	
	//The boolean which will I dentify whether or not the images are still loading (pre-drawing)
	boolean bool_loading = true;
	
	//the string which will be converted to an image (for ease of programming)
	String SpriteNames[] = {
			"Resources/Images/title_background.png",// titlescreen background = 0
			"Resources/Images/temp_title.png", //.titlescreen title = 1
			"Resources/Images/sandbox_buttonA.png", //the first sandbox button = 2
			"Resources/Images/sandbox_buttonB.png", //the second sandbox button = 3
			"Resources/Images/x_buttonA.png", //the first exit button = 4
			"Resources/Images/x_buttonB.png" //the second exit button = 5
	};
	
	//the integer which identifies the size of the loading bar
	int int_loading_bar = SpriteNames.length;
	
	//the main constructor
	public TitleScreen()
	{	
		//JAOButtons
		buttons = new JAOButton[2];
		
		//set the sprites by using the LoadImages Method
		Sprites = setImages(SpriteNames);
		
		//set the buttons
		buttons[0] = new JAOButton(300,400,256,32,Sprites[2],Sprites[3]);
		buttons[1] = new JAOButton(1300,20,32,32,Sprites[4],Sprites[5]);
	}
	
	//This is the draw method, which will be called by the drawing surface.
	public void draw(Graphics g)
	{
		//"load" the images (pre-draw)
		if(bool_loading != false)
		{
			//set the font and draw a message indicating loading
			g.setFont(new Font("Lucida Console", 30, 30));
			g.setColor(Color.white);
			g.drawString("Loading Sprites...",100,300);
			
			//draw the loading bar outline
			g.setColor(Color.green);
			g.fillRect(500,270,400 / int_loading_bar,30);
			g.setColor(Color.white);
			g.drawRect(500,270,400,30);
			
			//draw all the images used on this screen
			for(int i = 0; i < SpriteNames.length; i++)
			{
				g.drawImage(Sprites[i],0,0,Main.panel);
				int_loading_bar -= 1;
				
				if(int_loading_bar <= 0)
				{
					bool_loading = false;
				}
				
				Main.panel.repaint();
			}
		}
		else
		{
			//draw the titlescreen background
			g.drawImage(Sprites[0],0,0,Main.panel);
			g.drawImage(Sprites[1], (Main.tool.getScreenSize().width - Sprites[1].getWidth(Main.panel)) / 2, 100, Main.panel);
			
			//draw the buttons
			for(int i = 0; i < buttons.length; i++)
			{
				if(buttons[i] != null)
				{
					g.drawImage(buttons[i].Sprite_base,buttons[i].int_x,buttons[i].int_y,Main.panel);
				}
			}
		}
	}
	
	//the method which will load the images. will take an array of strings as it's argument
	public Image[] setImages(String[] images)
	{
		//create a temporary image array
		Image[] Pics = new Image[999];
		
		//set the image array using the data in the string array
		for(int i = 0; i < images.length; i++)
		{
			Pics[i] = Main.tool.getImage(images[i]);
		}
		
		//return the image array
		return Pics;
	}
	
	//MOUSE INPUT METHODS
	public void MouseMoved(int MX, int MY)
	{
		//run through the buttons and move them if they have been muiose overed
		for(int i = 0; i < buttons.length; i++)
		{
			if(buttons[i] != null)
			{
				buttons[i].input(MX,MY);
			}
		}
	}
	
	public void MouseClicked(int MX, int MY)
	{
		//individually program each button
		if(MouseCollide.mouseCollide(MX,MY,buttons[0].int_x,buttons[0].int_y,buttons[0].int_width,buttons[0].int_height))
		{
			DrawSurface.activeScreen = new SandBoxScreen();
		}
		else if(MouseCollide.mouseCollide(MX,MY,buttons[1].int_x,buttons[1].int_y,buttons[1].int_width,buttons[1].int_height))
		{
			System.exit(0);
		}
	}
}
