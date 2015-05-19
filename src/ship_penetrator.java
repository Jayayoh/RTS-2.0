
class ship_penetrator extends Entity 
{
	
	public ship_penetrator(int x, int y)
	{
		int_x = x;
		int_y = y;
		
		int_x -= Main.int_camera_refX;
		int_y -= Main.int_camera_refY;
		
		int_speedX = 5;
		int_speedY = 5;
		int_width = 32;
		int_height = 32;
		
		int_target_x = int_x;
		int_target_y = int_y;
		
		image = Main.tool.getImage("Resources/Images/penetrator_temp.png");
		image_selected = Main.tool.getImage("Resources/Images/penetrator_temp_selected.png");
	}
}