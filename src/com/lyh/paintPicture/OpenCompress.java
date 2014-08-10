package com.lyh.paintPicture;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class OpenCompress implements ActionListener
{
	JFrame frame;
	Canvas can;
	JLabel label;
	OpenCompressPaint openPaint;
	Sstyle style;
	Sstyle1 style1;
	Sstyle2 style2;
	
	OpenCompress(JFrame frame, Canvas can,JLabel label,Sstyle style,Sstyle1 style1,Sstyle2 style2)
	{
		this.frame = frame;
		this.can = can;
		this.label = label;
		this.style = style;
		this.style1 = style1;
		this.style2 = style2;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String str = new String("");
		Graphics g = can.getGraphics();

		FileDialog log = new FileDialog(frame);
		log.setVisible(true);
		String filee = log.getFile();
		String path = log.getDirectory();
		path = path + filee;
		

		File file = new File(path);
		byte[] by = new byte[20480000];
		int length = 0;

		try
		{
			FileInputStream in = new FileInputStream(file);
			length = in.read(by);
		} catch (Exception e1)
		{
			JOptionPane.showMessageDialog(frame, "图片打开失败！", "错误！",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
			return;
		}
		
		// 判断是否为b１图片
				if (by[0] == 98 && by[1] == 49)
				{
					str = str + "   b1图像文件";
				} else
				{
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(frame,
							"打开的文件不是b１图像！", "错误！",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

		//将图片信息放在合适的数组中
		byte[] by1 = new byte[length];
		for (int i = 0; i < length; i++)
		{
			by1[i] = by[i];
		}
		by = null;
		
		byte[] byt1 = new byte[4];
		byte[] byt2 = new byte[4];
		
		for(int i = 0;i < 4;i++)
		{
			byt1[i] = by1[2 + i];
			byt2[i] = by1[6 + i];
		}

		// 获得文件的宽度
		int width = byte2int(byt1);
		// 获得文件的高度
		int height = byte2int(byt2);
		//获取颜色的个数
		int colNum = by1[10];
		
		//定义一个一维数组，获取和所有的颜色
		byte[] allCol = new byte[colNum * 3];
		for(int i = 0;i < colNum  * 3;i++)
		{
			allCol[i] = by1[11  + i];
		}
		//将一维数组中的颜色转换为二维数组中的颜色
		byte allColor[][] = new byte[colNum][3];
		for(int i = 0;i < colNum;i++)
		{
			allColor[i][0] = allCol[i * 3];
			allColor[i][1] = allCol[i * 3 + 1];
			allColor[i][2] = allCol[i * 3 + 2];
		}
		//定义一个一维数组获取图片的颜色信息
		byte[] picColor = new byte[width * height];
		for(int i = 0;i < width * height;i++)
		{
			picColor[i] = by1[11 + allCol.length + i];
		}
	
		Graphics2D gra = (Graphics2D)g;
		
		if(filee == null)
		{
			
		}
		else
		{
			can.setSize(width, height);
			frame.pack();
			str = str + "   宽度：" + width + "像素；  高度：" + height + "像素；" + "    颜色数量： " + colNum;
			label.setText(str);
			OpenCompressPaint ppp = new OpenCompressPaint();
			ppp.setParameter(gra, width, height, by1, picColor, allColor,style,style1,style2);
			ppp.start();
		}	
	}
	
	 public static int byte2int(byte[] res)
	    {
	//一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000 

	        int targets= (res[0] & 0xff)
	                     | ((res[1]<<8) & 0xff00)
	                     | ((res[2]<<24)>>>8)
	                     | (res[3]<<24);
	        return targets;
	    }
}


//用于绘画被压缩图片的类
class OpenCompressPaint extends Thread
{
	Graphics2D gra;
	int width;
	int height;
	byte[] by1;
	byte[] picColor;
	byte allColor[][];
	Sstyle style;
	Sstyle1 style1;
	Sstyle2 style2;
	
	
	public void setParameter(Graphics2D gra,int width,int height,byte[] by1,byte[] picColor,byte allColor[][],Sstyle style,Sstyle1 style1,Sstyle2 style2)
	{
		this.gra = gra;
		this.width = width;
		this.height = height;
		this.by1 = by1;
		this.picColor = picColor;
		this.allColor = allColor;
		this.style = style;
		this.style1  = style1;
		this.style2 = style2;
	}
	
	@Override
	public void run()
	{
		int i;
		int j;
		Color color[][] = new Color[height][width];
		if(width == 0 && height == 0 && by1 == null)
		{
			
		}
		else
		{			
			if(style.getStyle().equals("down > top"))
			{
				for (i = 0; i < height; i++)
				{			
					for (j = 0; j < width; j++)
					{	
						int tem = picColor[i * width + j];
						byte[] by11 = allColor[tem];
						int bb = 0;
						int gg = 0;
						int rr = 0;
						if(style2.getBackground() == "normal")
						{
							 rr = by11[0];   //by1[0]
							 gg = by11[1];   //by1[1]
							 bb = by11[2];   //by1[2]
						}
						else if(style2.getBackground() == "red")
						{
							bb = 0;
							gg = 0;
							rr = by11[0];
						}
						else if(style2.getBackground() == "green")
						{
							bb = 0;
							gg = by11[1];
							rr = 0;
						}
						else if(style2.getBackground() == "blue")
						{
							bb = by11[2];
							gg = 0;
							rr = 0;
						}
						else if(style2.getBackground() == "cyan")
						{
							 gg = by11[1];   //by1[1]
							 bb = by11[2];   //by1[2]
							 rr = 0;
						}
						else if(style2.getBackground() == "yellow")
						{
							bb = 0;
							rr = by11[0];   //by1[0]
							gg = by11[1];   //by1[1]
						}
						else if(style2.getBackground() == "purple")
						{
							bb = by11[2];
							gg = 0;
							rr = by11[0];
						}  
						if (bb < 0)
						{
							bb = bb + 256;
						}
						if (gg < 0)
						{
							gg = gg + 256;
						}
						if (rr < 0)
						{
							rr = rr + 256;
						}
						if(style1.getStyle() == "normal")
						{
							gra.setPaint(new Color(rr, gg, bb));
						}
						else if(style1.getStyle() == "fupian")
						{
							gra.setPaint(new Color(255 - rr,255 - gg,255 - bb));
						}
						else if(style1.getStyle() == "heibai")
						{
							gra.setPaint(new Color(rr, rr, rr));
						}
						Rectangle2D rec = new Rectangle(j, height - i, 1, 1);
						gra.draw(rec);
					}
				}
				width = 0;
				height = 0;
				by1 = null;
				
			}
		
			if(style.getStyle().equals("top > down"))
			{
				for (i = 0; i < height; i++)
				{
					for (j = 0; j < width; j++)
					{
						int tem = picColor[i * width + j];
						byte[] by11 = allColor[tem];

						int bb = 0;
						int gg = 0;
						int rr = 0;
						if(style2.getBackground() == "normal")
						{
							rr = by11[0]; // by1[0]
							gg = by11[1]; // by1[1]
							bb = by11[2]; // by1[2]
						}
						else if(style2.getBackground() == "red")
						{
							bb = 0;
							gg = 0;
							rr = by11[0];
						}
						else if(style2.getBackground() == "green")
						{
							bb = 0;
							gg = by11[1];
							rr = 0;
						}
						else if(style2.getBackground() == "blue")
						{
							bb = by11[2];
							gg = 0;
							rr = 0;
						}
						else if(style2.getBackground() == "cyan")
						{
							gg = by11[1]; // by1[1]
							bb = by11[2]; // by1[2]
							rr = 0;
						}
						else if(style2.getBackground() == "yellow")
						{
							bb = 0;
							rr = by11[0]; // by1[0]
							gg = by11[1]; // by1[1]
						}
						else if(style2.getBackground() == "purple")
						{
							bb = by11[2];
							gg = 0;
							rr = by11[0];
						}

						if (bb < 0)
						{
							bb = bb + 256;
						}
						if (gg < 0)
						{
							gg = gg + 256;
						}
						if (rr < 0)
						{
							rr = rr + 256;
						}
						
						Color c = null;
						if(style1.getStyle() == "normal")
						{
							c = new Color(rr, gg, bb);
						}
						else if(style1.getStyle() == "fupian")
						{
							c = new Color(255 - rr,255 - gg,255 - bb);
						}
						else if(style1.getStyle() == "heibai")
						{
							c = new Color(rr, rr, rr);
						}
						color[i][j] = c;
					}

				}
				for(int o = height - 1;o >= 0;o--)
				{
					for(int p = 0;p < width;p++)
					{
						gra.setPaint(color[o][p]);
						Rectangle2D rec = new Rectangle(p,height - o, 1, 1);
						gra.draw(rec);
					}
				}
				width = 0;
				height = 0;
				by1 = null;
			}
			
			if(style.getStyle().equals("left > right"))
			{
				for (i = 0; i < height; i++)
				{
					for (j = 0; j < width; j++)
					{
						int tem = picColor[i * width + j];
						byte[] by11 = allColor[tem];

						int bb = 0;
						int gg = 0;
						int rr = 0;
						if(style2.getBackground() == "normal")
						{
							rr = by11[0]; // by1[0]
							gg = by11[1]; // by1[1]
							bb = by11[2]; // by1[2]
						}
						else if(style2.getBackground() == "red")
						{
							bb = 0;
							gg = 0;
							rr = by11[0];
						}
						else if(style2.getBackground() == "green")
						{
							bb = 0;
							gg = by11[1];
							rr = 0;
						}
						else if(style2.getBackground() == "blue")
						{
							bb = by11[2];
							gg = 0;
							rr = 0;
						}
						else if(style2.getBackground() == "cyan")
						{
							gg = by11[1]; // by1[1]
							bb = by11[2]; // by1[2]
							rr = 0;
						}
						else if(style2.getBackground() == "yellow")
						{
							bb = 0;
							rr = by11[0]; // by1[0]
							gg = by11[1]; // by1[1]
						}
						else if(style2.getBackground() == "purple")
						{
							bb = by11[2];
							gg = 0;
							rr = by11[0];
						}

						if (bb < 0)
						{
							bb = bb + 256;
						}
						if (gg < 0)
						{
							gg = gg + 256;
						}
						if (rr < 0)
						{
							rr = rr + 256;
						}
						
						Color c = null;
						if(style1.getStyle() == "normal")
						{
							c = new Color(rr, gg, bb);
						}
						else if(style1.getStyle() == "fupian")
						{
							c = new Color(255 - rr,255 - gg,255 - bb);
						}
						else if(style1.getStyle() == "heibai")
						{
							c = new Color(rr, rr, rr);
						}
						color[i][j] = c;
					}

				}
				for(int p = 0;p < width;p++)
				{
					for(int o = height - 1;o >= 0;o--)
					{
						gra.setPaint(color[o][p]);
						Rectangle2D rec = new Rectangle(p,height - o, 1, 1);
						gra.draw(rec);
					}
				}
				width = 0;
				height = 0;
				by1 = null;
			}
			
			if(style.getStyle().equals("right > left"))
			{
				for (i = 0; i < height; i++)
				{
					for (j = 0; j < width; j++)
					{
						int tem = picColor[i * width + j];
						byte[] by11 = allColor[tem];

						int bb = 0;
						int gg = 0;
						int rr = 0;
						if(style2.getBackground() == "normal")
						{
							rr = by11[0]; // by1[0]
							gg = by11[1]; // by1[1]
							bb = by11[2]; // by1[2]
						}
						else if(style2.getBackground() == "red")
						{
							bb = 0;
							gg = 0;
							rr = by11[0];
						}
						else if(style2.getBackground() == "green")
						{
							bb = 0;
							gg = by11[1];
							rr = 0;
						}
						else if(style2.getBackground() == "blue")
						{
							bb = by11[2];
							gg = 0;
							rr = 0;
						}
						else if(style2.getBackground() == "cyan")
						{
							gg = by11[1]; // by1[1]
							bb = by11[2]; // by1[2]
							rr = 0;
						}
						else if(style2.getBackground() == "yellow")
						{
							bb = 0;
							rr = by11[0]; // by1[0]
							gg = by11[1]; // by1[1]
						}
						else if(style2.getBackground() == "purple")
						{
							bb = by11[2];
							gg = 0;
							rr = by11[0];
						}

						if (bb < 0)
						{
							bb = bb + 256;
						}
						if (gg < 0)
						{
							gg = gg + 256;
						}
						if (rr < 0)
						{
							rr = rr + 256;
						}
						
						Color c = null;
						if(style1.getStyle() == "normal")
						{
							c = new Color(rr, gg, bb);
						}
						else if(style1.getStyle() == "fupian")
						{
							c = new Color(255 - rr,255 - gg,255 - bb);
						}
						else if(style1.getStyle() == "heibai")
						{
							c = new Color(rr, rr, rr);
						}
						color[i][j] = c;
					}

				}
				for(int p = width - 1;p >= 0;p--)
				{
					for(int o = height - 1;o >= 0;o--)
					{
						gra.setPaint(color[o][p]);
						Rectangle2D rec = new Rectangle(p,height - o, 1, 1);
						gra.draw(rec);
					}
				}
				width = 0;
				height = 0;
				by1 = null;
			}
		}
	}
}















