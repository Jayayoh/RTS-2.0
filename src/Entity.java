import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;


class Entity 
{
	float int_x;
	float int_y;
	float int_width;
	float int_height;
	float int_speedX;
	float int_speedY;
	
	float int_target_x;
	float int_target_y;
	
	boolean selected;
	
	Image image;
	Image image_selected;
	
	public Entity()
	{
		selected = false;
	}
	
	public void move()
	{
		if(int_target_x - int_x > 3 || int_target_x - int_x < -3)
		{
			int_x += int_speedX;
		}
		
		if(int_target_y - int_y > 3 || int_target_y - int_y < -3)
		{
			int_y += int_speedY;
		}
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		if(selected == true)
		{
			g.setColor(new Color(150,150,150));
			g.fillOval(Main.int_camera_refX + (int)int_x,Main.int_camera_refY + (int)int_y,(int)int_width,(int)int_height);

			g2d.translate(-int_x + int_width / 2, -int_y + int_height / 2);
			g2d.rotate(45);
			g.drawImage(image_selected,Main.int_camera_refX + (int)int_x, Main.int_camera_refY + (int)int_y,Main.panel);
			g2d.rotate(-45);
			g2d.translate(int_x + int_width / 2, int_y + int_height / 2);
			
			g.drawString("x : " + (Main.int_camera_refX + (int)int_x), 10,50);
			g.drawString("y : " + (Main.int_camera_refY + (int)int_y), 10,60);
			
			g.drawString("camera x :" + Main.int_camera_refX, 10,70);
			g.drawString("camera y :" + Main.int_camera_refY, 10,80);
		}
		else
		{
			g.drawImage(image, Main.int_camera_refX + (int)int_x,Main.int_camera_refY + (int)int_y,Main.panel);
		}
	}
}