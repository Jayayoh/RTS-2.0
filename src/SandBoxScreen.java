//imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image; 


/*
 * THis screen will be the customizable sandbox screen, where gameplay will happen
 */
public class SandBoxScreen extends Screen
{
	
	//the boolean which will tell if the sprites are still pre-drawing
	boolean bool_loading = true;
	
	//the string which will be converted to an image
	Image Sprites[] = {
			Main.tool.getImage("Resources/Images/GUI_base.png"), //the GUI base = 0
			Main.tool.getImage("Resources/Images/menu_bar.png"), //the menu bar = 1
			Main.tool.getImage("Resources/Images/menu_button.png"), //the first menu button = 2
			Main.tool.getImage("resources/Images/menu_buttonB.png"), //the second menu button = 3
			Main.tool.getImage("Resources/Images/menu_downA.png"), //the first down button = 4
			Main.tool.getImage("Resources/Images/menu_downB.png"), //the second down button = 5
			Main.tool.getImage("Resources/Images/menu_upA.png"), //the first up button = 6
			Main.tool.getImage("Resources/Images/menu_upB.png"), //the second up button = 7
			Main.tool.getImage("Resources/Images/menu_base.png"), //the menu panel base = 8
			Main.tool.getImage("Resources/Images/menu_exitA.png"), //the first menu exit button = 9
			Main.tool.getImage("Resources/Images/menu_exitB.png"), //the second menu exit button = 10
			Main.tool.getImage("Resources/Images/menu_resumeA.png"), //the first menu exit button = 11
			Main.tool.getImage("Resources/Images/menu_resumeB.png"), //the second menu exit button = 12
			//the images for the selection pane
			Main.tool.getImage("Resources/images/penetrator_select.png"), //the penetrator = 13
			//end
			Main.tool.getImage("resources/Images/menu_downA.png"), //the first menu down button  = 14
			Main.tool.getImage("resources/Images/menu_downB.png"), //the second menu down button = 15
			Main.tool.getImage("resources/Images/menu_upA.png"), //the first menu up button = 16
			Main.tool.getImage("resources/Images/menu_upB.png"), //the second menu up button = 17
			Main.tool.getImage("Resources/Images/spawnA.png"), //the first spawn button = 18
			Main.tool.getImage("Resources/Images/spawnB.png") //the second spawn button = 19
			
	};
	
	//the integer which will identify the size of the loading bar
	int int_loading_bar = Sprites.length;
	
	//this boolean will identify if the mennu is open or not
	boolean bool_menu_open = false;
	
	JAOButton buttons[] = {
			//the menu button
			new JAOButton(300,0,128,32,Sprites[2],Sprites[3]),
			//the up and down selector buttons
			new JAOButton(655,680,32,16,Sprites[16],Sprites[17]), //up
			new JAOButton(655,696,32,16,Sprites[14],Sprites[15]), //down
			//the spawn button
			new JAOButton(700, 680, 32, 32, Sprites[18], Sprites[19])
	};
	
	//the buttons in the menu that opens on click. must be seperate
	JAOButton menu_buttons[] = {
			//set the buttons for the menu screen
			//menu button
			new JAOButton((Main.tool.getScreenSize().width - 250) / 2 + 66,(Main.tool.getScreenSize().height - 400) / 2 + 32, 128,32, Sprites[2],Sprites[3]),
			//exit button
			new JAOButton((Main.tool.getScreenSize().width - 250) / 2 + 66,(Main.tool.getScreenSize().height - 400) / 2 + 96, 128,32, Sprites[9],Sprites[10]),
			//resume button
			new JAOButton((Main.tool.getScreenSize().width - 250) / 2 + 66,(Main.tool.getScreenSize().height - 400) / 2 + 162, 128,32, Sprites[11],Sprites[12])
	};
	
	//seems redundant, but this collects all selector images into one place so taht they can be accessed via index number
	Image img_selector[] = {
			Sprites[13]
	};
	
	//the active selector, for drawing purposes
	Image img_selector_active = img_selector[0];
	
	//the active selector integer, for indexing purposes
	int int_selector_active = 0;
	
	//THE ARRAY OF ENTITIES. THIS HOLDS ALL THE SHIPS ON THE PLAYING FIELD
	Entity entities[] = new Entity[99];
	
	//the boolean which will identify if spawning is active
	boolean bool_spawn = false;
	
	//this integer will work with mouse entered / exited functions to move the camera
	int camera_moveX;
	int camera_moveY;
	
	//the main constructor
	public SandBoxScreen()
	{	
		Main.int_camera_refX = 0;
		Main.int_camera_refY = 0;
	}
	
	//the gameloop
	public void gameloop()
	{
		//move the camera
		if(bool_menu_open != true)
		{
			switch(camera_moveX)
			{
				case 0: break;
				case 1: Main.int_camera_refX += 5;break;
				case 2: Main.int_camera_refX -= 5;break;
			}
			
			switch(camera_moveY)
			{
				case 0: break;
				case 1: Main.int_camera_refY += 5; break;
				case 2: Main.int_camera_refY -= 5; break;
			}
		}
		
		//move the ships if they can move
		for(int i = 0; i < entities.length; i++)
		{
			if(entities[i] != null)
			{
				entities[i].move();
			}
			else
			{
				break;
			}
		}
	}
	
	//This is the draw method, which will be called by the drawing surface.
	public void draw(Graphics g)
	{
		//check if images are loading, draw appropriately
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
			for(int i = 0; i < Sprites.length; i++)
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
			//draw the entities
			for(int i = 0; i < entities.length; i++)
			{
				if(entities[i] != null)
				{
					//draw the ships only if they are on screen
					if((entities[i].int_x + Main.int_camera_refX) + entities[i].int_width > 0 && (entities[i].int_x + Main.int_camera_refX) < Main.tool.getScreenSize().width && 
							(entities[i].int_y + Main.int_camera_refY) + entities[i].int_height > 0 && (entities[i].int_y + Main.int_camera_refY) < Main.tool.getScreenSize().height - 128)
					{
						entities[i].draw(g);
					}
				}
				else
				{
					break;
				}
			}
			
			//draw the GUI bases
			g.drawImage(Sprites[0],0,Main.tool.getScreenSize().height - 128,Main.panel);
			g.drawImage(Sprites[1],0,0,Main.panel);
			
			//draw the buttons
			for(int i = 0; i < buttons.length; i++)
			{
				if(buttons[i] != null)
				{
					g.drawImage(buttons[i].Sprite_base,buttons[i].int_x,buttons[i].int_y,Main.panel);
				}
			}
			
			//draw the active selector
			g.drawImage(img_selector_active,400,680,Main.panel);
			
			//draw the menu things if the menu is open
			if(bool_menu_open)
			{
				//create a transparent color
				Color c = new Color(0,0,0,0.7f);
				
				//set the transparent color
				g.setColor(c);
				
				//draw the transparent background
				g.fillRect(0,0,Main.tool.getScreenSize().width,Main.tool.getScreenSize().height);
				
				//draw the menu panel
				g.drawImage(Sprites[8],(Main.tool.getScreenSize().width - 250) / 2, (Main.tool.getScreenSize().height - 400) / 2, Main.panel);
				
				//draw the menu buttons
				for(int i = 0; i < menu_buttons.length; i++)
				{
					if(menu_buttons[i] != null)
					{
						g.drawImage(menu_buttons[i].Sprite_base,menu_buttons[i].int_x,menu_buttons[i].int_y,Main.panel);
					}
				}
			} 
		}
	}
	
	//MOUSE INPUT METHODS
	public void MouseMoved(int MX, int MY)
	{
		//check if the menu is open
		if(bool_menu_open != true)
		{
			//loop through the main buttons and perform input
			for(int i = 0; i < buttons.length; i++)
			{
				//check if the button exists first
				if(buttons[i] != null)
				{
					buttons[i].input(MX,MY);
				}
			}
			
			//check if the mouse moves the camera
			if(MX < 32)
			{
				camera_moveX = 1;
			}
			else if(MX > Main.tool.getScreenSize().width - 32)
			{
				camera_moveX = 2;
			}
			else
			{
				camera_moveX = 0;
			}
			
			if(MY < 32 && MouseCollide.mouseCollide(MX,MY,-1,-1,1025,33) == false)
			{
				camera_moveY = 1;
			}
			else if(MY > Main.tool.getScreenSize().height - 32)
			{
				camera_moveY = 2;
			}
			else
			{
				camera_moveY = 0;
			}
			
			//if the mouse is not on any of the above points
		}
		else
		{
			for(int i = 0; i < menu_buttons.length; i++)
			{
				if(menu_buttons[i] != null)
				{
					menu_buttons[i].input(MX, MY);
				}
			}
		}
	}
	
	public void MouseClicked(int MX, int MY)
	{
		System.out.println("clicked");
		
		//check if the menu is open
		if(bool_menu_open != true)
		{
			//the menu button to open up the menu
			if(MouseCollide.mouseCollide(MX,MY,buttons[0].int_x,buttons[0].int_y,buttons[0].int_width,buttons[0].int_height))
			{
				bool_menu_open = true;
			}
			else if(MouseCollide.mouseCollide(MX,MY,buttons[3].int_x,buttons[3].int_y,buttons[3].int_width,buttons[3].int_height))
			{
				bool_spawn = true;
			}
			
			//check if the mouse has clicked the playing surface (THIS WILL BE BIG)
			else if(MY > 32 && MY < (Main.tool.getScreenSize().height - 128))
			{
				if(bool_spawn == true)
				{
					for(int i = 0; i < entities.length; i++)
					{
						if(entities[i] == null)
						{
							switch(int_selector_active)
							{
								case 0:entities[i] = new ship_penetrator(MX - 8,MY - 16); break;  
							}
							
							bool_spawn = false;
							
							break;
						}
						else
						{
							continue;
						}
					}
				}
				else
				{
					//ruin through the ships
					for(int i = 0; i < entities.length; i++)
					{
						//if a ship exists
						if(entities[i] != null)
						{
							//if you have clicked on that ship
							if(MouseCollide.mouseCollide(MX,MY,(int)entities[i].int_x,(int)entities[i].int_y,(int)entities[i].int_width,(int)entities[i].int_height))
							{
								entities[i].selected = !entities[i].selected;
								break;
							}
							else
							{
								for(int j = 0; j < entities.length; j++)
								{
									if(entities[j] != null)
									{
										if(entities[j].selected == true)
										{
											entities[j].int_target_x = MX - Main.int_camera_refX;
											entities[j].int_target_y = MY - Main.int_camera_refY;
											
											float int_divisor = (float)Math.sqrt(Math.pow(entities[j].int_target_x - entities[j].int_x, 2) + Math.pow(entities[j].int_target_y - entities[j].int_y,2));
											
											entities[j].int_speedX = (float)(entities[j].int_target_x - entities[j].int_x) / int_divisor;
											entities[j].int_speedY = (float)(entities[j].int_target_y - entities[j].int_y) / int_divisor;
											
										}
										else
										{
											continue;
										}
									}
									else
									{
										break;
									}
								}
							}
						}
						else
						{
							break;
						}
					}
				}
			}
		}
		else
		{
			//check if any menu buttons have been pressed
			if(MouseCollide.mouseCollide(MX,MY,menu_buttons[0].int_x,menu_buttons[0].int_y,menu_buttons[0].int_width,menu_buttons[0].int_height))
			{
				//go to the titlescreen
				Main.panel.activeScreen = new TitleScreen();
			}
			else if(MouseCollide.mouseCollide(MX,MY,menu_buttons[1].int_x,menu_buttons[1].int_y,menu_buttons[1].int_width,menu_buttons[1].int_height))
			{
				//close the program
				System.exit(0);
			}
			else if(MouseCollide.mouseCollide(MX,MY,menu_buttons[2].int_x,menu_buttons[2].int_y,menu_buttons[2].int_width,menu_buttons[2].int_height))
			{
				//resume the game
				bool_menu_open = false;
			}
		}
	}
	
	public void MouseEntered(int MX, int MY)
	{
	}
}
