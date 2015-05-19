//imports
import java.awt.Image;

/*
 * THis class is used for custom buttons, and will be responsible for their coordinates, dimensions, and input
 */
public class JAOButton 
{
	
	//the coordinates
	int int_x;
	int int_y;
	
	//the dimensions
	int int_width;
	int int_height;
	
	//the Sprites
	Image Sprite1;
	Image Sprite2;
	Image Sprite_base;
	
	public JAOButton(int x, int y,int width,int height, Image s1, Image s2)
	{
		int_x = x;
		int_y = y;
		
		Sprite1 = s1;
		Sprite2 = s2;
		
		int_width = width;
		int_height = height;
		
		Sprite_base = Sprite1;
	}
	
	public void input(int MX, int MY)
	{
		if(MouseCollide.mouseCollide(MX,MY,int_x,int_y,int_width,int_height))
		{
			Sprite_base = Sprite2;
		}
		else
		{
			Sprite_base = Sprite1;
		}
	}
}
