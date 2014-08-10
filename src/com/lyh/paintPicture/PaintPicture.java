package com.lyh.paintPicture;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

public class PaintPicture
{
	public static void main(String[] args)
	{
		Sstyle style = new Sstyle();             //用于控制画图方向
		Sstyle1 style1 = new Sstyle1();          //用于控制图像特效
		Sstyle2 style2 = new Sstyle2();          //用于控制打开时背景色
//		Sstyle2 style3 = new Sstyle3();          //用于控制保存时背景色
//		Sstyle3 style4 = new Sstyle4();          //用于控制压缩方式
		
		TransInfo info = new TransInfo();

		Font font = new Font("宋体", Font.BOLD, 15);
		JLabel label = new JLabel("");
		label.setForeground(Color.blue);
		label.setFont(font);

		JFrame frame = new JFrame();
		frame.setBounds(400, 70, 300, 300);
		JMenuBar bar = new JMenuBar();
		Canvas can = new Canvas();
		
		ToCompress toCompress = new ToCompress();                    //定义一个压缩图片的的类

		JMenu menu1 = new JMenu("打开");
		JMenu menu2 = new JMenu("画图方式");
		JMenu menu3 = new JMenu("图像特效");
		JMenu menu4 = new JMenu("压缩");
		JMenu menu5 = new JMenu("背景色");

		JMenuItem item11 = new JMenuItem("打开");
		JMenuItem item12 = new JMenuItem("清除");
		JMenuItem item13 = new JMenuItem("退出");
		JMenuItem item14 = new JMenuItem("保存");
		item14.setEnabled(false);

		JMenu item21 = new JMenu("画图方向");
		
		JMenuItem item41 = new JMenuItem("压缩");
		JMenuItem item43 = new JMenuItem("打开压缩");
		JMenuItem item45 = new JMenuItem("保存压缩");
		JMenu item44 = new JMenu("压缩方式");
	
		
		JMenu item51 = new JMenu("打开背景"); 
		JMenu item52 = new JMenu("保存背景");

		JRadioButtonMenuItem item211 = new JRadioButtonMenuItem("从上到下");
		JRadioButtonMenuItem item212 = new JRadioButtonMenuItem("从下到上",true);
		JRadioButtonMenuItem item213 = new JRadioButtonMenuItem("从左到右");
		JRadioButtonMenuItem item214 = new JRadioButtonMenuItem("从右到左");
		
		JRadioButtonMenuItem item31 = new JRadioButtonMenuItem("正常",true);
		JRadioButtonMenuItem item32 = new JRadioButtonMenuItem("锐化");
		JRadioButtonMenuItem item33 = new JRadioButtonMenuItem("负片");
		JRadioButtonMenuItem item34 = new JRadioButtonMenuItem("浮雕");
		JRadioButtonMenuItem item35 = new JRadioButtonMenuItem("黑白");
		item32.setEnabled(false);
		item34.setEnabled(false);
		
		JRadioButtonMenuItem item441 = new JRadioButtonMenuItem("五分矩阵",true);
		JRadioButtonMenuItem item442 = new JRadioButtonMenuItem("九方格");
		item442.setEnabled(false);
		JRadioButtonMenuItem item443 = new JRadioButtonMenuItem("四方格");
		item443.setEnabled(false);
		JRadioButtonMenuItem item444 = new JRadioButtonMenuItem("无损压缩");
		item444.setEnabled(false);
		
		JRadioButtonMenuItem item511 = new JRadioButtonMenuItem("正常",true);
		JRadioButtonMenuItem item512 = new JRadioButtonMenuItem("红");
		JRadioButtonMenuItem item513 = new JRadioButtonMenuItem("绿");
		JRadioButtonMenuItem item514 = new JRadioButtonMenuItem("蓝");
		JRadioButtonMenuItem item515 = new JRadioButtonMenuItem("青");
		JRadioButtonMenuItem item516 = new JRadioButtonMenuItem("黄");
		JRadioButtonMenuItem item517 = new JRadioButtonMenuItem("紫");
		
		JRadioButtonMenuItem item521 = new JRadioButtonMenuItem("正常",true);
		JRadioButtonMenuItem item522 = new JRadioButtonMenuItem("红");
		item522.setEnabled(false);
		JRadioButtonMenuItem item523 = new JRadioButtonMenuItem("绿");
		item523.setEnabled(false);
		JRadioButtonMenuItem item524 = new JRadioButtonMenuItem("蓝");
		item524.setEnabled(false);
		JRadioButtonMenuItem item525 = new JRadioButtonMenuItem("青");
		item525.setEnabled(false);
		JRadioButtonMenuItem item526 = new JRadioButtonMenuItem("黄");
		item526.setEnabled(false);
		JRadioButtonMenuItem item527 = new JRadioButtonMenuItem("紫");
		item527.setEnabled(false);
		
		ButtonGroup group = new ButtonGroup();
		ButtonGroup group1 = new ButtonGroup();
		ButtonGroup group2 = new ButtonGroup();
		ButtonGroup group3 = new ButtonGroup();
		ButtonGroup group4 = new ButtonGroup();
		
		group.add(item211);
		group.add(item212);
		group.add(item213);
		group.add(item214);
		
		group1.add(item31);
		group1.add(item32);
		group1.add(item33);
		group1.add(item34);
		group1.add(item35);
		
		group2.add(item441);
		group2.add(item442);
		group2.add(item443);
		group2.add(item444);
		
		group3.add(item511);
		group3.add(item512);
		group3.add(item513);
		group3.add(item514);
		group3.add(item515);
		group3.add(item516);
		group3.add(item517);
		
		group4.add(item521);
		group4.add(item522);
		group4.add(item523);
		group4.add(item524);
		group4.add(item525);
		group4.add(item526);
		group4.add(item527);

		// 开始添加控件到窗体中
		item21.add(item211);
		item21.add(item212);
		item21.add(item213);
		item21.add(item214);
		
		item44.add(item441);
		item44.add(item442);
		item44.add(item443);
		item44.add(item444);

		item51.add(item511);
		item51.add(item512);
		item51.add(item513);
		item51.add(item514);
		item51.add(item515);
		item51.add(item516);
		item51.add(item517);
		
		item52.add(item521);
		item52.add(item522);
		item52.add(item523);
		item52.add(item524);
		item52.add(item525);
		item52.add(item526);
		item52.add(item527);
		
		
		menu1.add(item11);
		menu1.add(item14);
		menu1.add(item12);
		menu1.add(item13);
		
		menu2.add(item21);
		
		menu3.add(item31);
		menu3.add(item32);
		menu3.add(item33);
		menu3.add(item34);
		menu3.add(item35);
		
		menu4.add(item41);
		menu4.add(item43);
		menu4.add(item45);
		menu4.add(item44);		
		
		menu5.add(item51);
		menu5.add(item52);

		bar.add(menu1);
		bar.add(menu2);
		bar.add(menu3);
		bar.add(menu4);
		bar.add(menu5);
		
		frame.add(can, BorderLayout.CENTER);
		frame.add(label, BorderLayout.SOUTH);

		// 为控件添加监听程序
		item11.addActionListener(new Open(frame, can, menu1, label, style,style1,style2,info));
		
		item211.addActionListener(new Style(style, item211, item212, item213, item214));
		item212.addActionListener(new Style(style, item211, item212, item213, item214));
		item213.addActionListener(new Style(style, item211, item212, item213, item214));
		item214.addActionListener(new Style(style, item211, item212, item213, item214));
		
		item31.addActionListener(new PictureStyle(style1, item31, item32, item33, item34, item35));
		item32.addActionListener(new PictureStyle(style1, item31, item32, item33, item34, item35));
		item33.addActionListener(new PictureStyle(style1, item31, item32, item33, item34, item35));
		item34.addActionListener(new PictureStyle(style1, item31, item32, item33, item34, item35));
		item35.addActionListener(new PictureStyle(style1, item31, item32, item33, item34, item35));
		
		item511.addActionListener(new OpenBackground(style2, item511, item512, item513, item514, item515, item516, item517));
		item512.addActionListener(new OpenBackground(style2, item511, item512, item513, item514, item515, item516, item517));
		item513.addActionListener(new OpenBackground(style2, item511, item512, item513, item514, item515, item516, item517));
		item514.addActionListener(new OpenBackground(style2, item511, item512, item513, item514, item515, item516, item517));
		item515.addActionListener(new OpenBackground(style2, item511, item512, item513, item514, item515, item516, item517));
		item516.addActionListener(new OpenBackground(style2, item511, item512, item513, item514, item515, item516, item517));
		item517.addActionListener(new OpenBackground(style2, item511, item512, item513, item514, item515, item516, item517));
		
		
		item12.addActionListener(new Clear(can));
		
		item13.addActionListener(new MyExit());
		
		
		
		MyCompress compress = new MyCompress(label,frame,can, info,toCompress,style,style1,style2);
		item41.addActionListener(compress);
		
		item45.addActionListener(new CompressSave(info, frame));
		item43.addActionListener(new OpenCompress(frame, can,label,style,style1,style2));
		

		frame.add(bar, BorderLayout.NORTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		bar.setVisible(true);
		frame.setVisible(true);
	}
}


//选择图片的类
class Open implements ActionListener
{
	JFrame frame;
	Canvas can;
	JMenu menu;
	JLabel label;
	Sstyle style;
	Sstyle1 style1;
	Sstyle2 style2;
	
	//向外界传递信息的变量
	TransInfo info;
	
	

	Open(JFrame frame, Canvas can, JMenu menu, JLabel label,Sstyle style,Sstyle1 style1,Sstyle2 style2,TransInfo info)
	{
		this.frame = frame;
		this.can = can;
		this.menu = menu;
		this.label = label;
		this.style = style;
		this.style1 = style1;
		this.style2 = style2;
		this.info = info;			
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String str = new String("");

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

		// 判断是否为bmp图片
		if (by[0] == 66 && by[1] == 77)
		{
			str = str + "   Bmp图像文件";
		} else
		{
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(frame,
					"打开的文件格式不正确，软件只支持24位bmp文件格式！打开的文件不是bmp图像！", "错误！",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (by[0x1c] == 24 && by[0x1c + 1] == 0)
		{
			str = str + "    位深度：24；";
		} else
		{
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(frame,
					"打开的文件格式不正确，软件只支持24位bmp文件格式！打开的文件不是24位bmp图像!", "错误！",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// 宽度的转换
		int c = by[0x12];
		int a = by[0x12 + 1];
		int f = by[0x12 + 2];
		int e1 = by[0x12 + 3];
		if (c < 0)
		{
			c = c + 256;
		}
		if (a < 0)
		{
			a = a + 256;
		}
		if (f < 0)
		{
			f = f + 256;
		}
		if (e1 < 0)
		{
			e1 = e1 + 256;
		}

		// 高度的转换
		int cc = by[0x16];
		int aa = by[0x16 + 1];
		int ff = by[0x16 + 2];
		int ee = by[0x16 + 3];
		if (cc < 0)
		{
			cc = cc + 256;
		}
		if (aa < 0)
		{
			aa = aa + 256;
		}
		if (ff < 0)
		{
			ff = ff + 256;
		}
		if (ee < 0)
		{
			ee = ee + 256;
		}

		// 获得文件的宽度，从文件偏移量12H开始，占4个字节
		int width = c + a * 256 + f * 256 * 256 + e1 * 256 * 256 * 256;
		// 获得文件的高度，从文件偏移量16H开始，占4个字节
		int height = cc + aa * 256 + ff * 256 * 256 + ee * 256 * 256 * 256;
		
		int cha = Math.abs((width * 3) % 4 - 4) % 4;
		int pos = 0x36;

		//将图片信息放在合适的数组中
		byte[] by1 = new byte[length];
		for (int i = 0; i < length; i++)
		{
			by1[i] = by[i];
		}
		by = null;
		
		//向外界传递信息
		info.setOutWidth(width);
		info.setOutHeight(height);
		info.setOutBy2(by1);
		info.setOutCha(cha);
		info.setOutPos(pos);
		
		Graphics g = can.getGraphics();
		can.setSize(width, height);
		frame.pack();
		str = str + "   宽度：" + width + "像素；  高度：" + height + "像素";
		label.setText(str);
		MyPaint myPaint = new MyPaint();
		myPaint.setParameter(width, height, by1, cha, pos, style, style1, style2, label, g);
		myPaint.start();
	}

}

// 定义一个线程用于画图
class MyPaint extends Thread
{
	int x;
	int y;
	byte by[];
	byte picColor[];
	byte allColor[][];
	int cha;
	int pos;
	Sstyle style;
	Sstyle1 style1;
	Sstyle2 style2;
	JLabel label;
	Graphics g;
	int k = 0;
	
	
	public void setParameter(int x,int y,byte by[],int cha,int pos,Sstyle style,Sstyle1 style1,Sstyle2 style2,JLabel label,Graphics g)
	{
		this.x = x;
		this.y = y;
		this.by = by;
		this.cha = cha;
		this.pos = pos;
		this.style = style;
		this.style1 = style1;
		this.style2 = style2;
		this.label = label;
		this.g = g;
	}
	
	public void setParameter(int width, int height,byte[] picColor,byte[][] allColor,JLabel label,Graphics g)
	{
		this.x = width;
		this.y = height;
		this.picColor = picColor;
		this.allColor = allColor;
		this.label = label;
		this.g = g;
	}
	


	@Override
	public void run()
	{
		Graphics2D gra = (Graphics2D) g;
		Color color[][] = new Color[y][x];

		int i;
		int j;
		if(x == 0 && y == 0 && by == null)
		{
			
		}
		else
		{
			if(style.getStyle().equals("down > top"))
			{
				for (i = 0; i < y; i++)
				{			
					for (j = 0; j < x; j++)
					{	
						int bb = 0;
						int gg = 0;
						int rr = 0;
						if(style2.getBackground() == "normal")
						{
							bb = by[pos];
							gg = by[pos + 1];
							rr = by[pos + 2];
						}
						else if(style2.getBackground() == "red")
						{
							bb = 0;
							gg = 0;
							rr = by[pos + 2];
						}
						else if(style2.getBackground() == "green")
						{
							bb = 0;
							gg = by[pos + 1];
							rr = 0;
						}
						else if(style2.getBackground() == "blue")
						{
							bb = by[pos];
							gg = 0;
							rr = 0;
						}
						else if(style2.getBackground() == "cyan")
						{
							bb = by[pos];
							gg = by[pos + 1];
							rr = 0;
						}
						else if(style2.getBackground() == "yellow")
						{
							bb = 0;
							gg = by[pos + 1];
							rr = by[pos + 2];
						}
						else if(style2.getBackground() == "purple")
						{
							bb = by[pos];
							gg = 0;
							rr = by[pos + 2];
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
						Rectangle2D rec = new Rectangle(j, y - i, 1, 1);
						gra.draw(rec);
						pos += 3;
						
					}
					pos += cha;
				}
				x = 0;
				y = 0;
				by = null;
			}
			
			
			if(style.getStyle().equals("top > down"))
			{
				for (i = 0; i < y; i++)
				{
					for (j = 0; j < x; j++)
					{
						int bb = 0;
						int gg = 0;
						int rr = 0;
						if(style2.getBackground() == "normal")
						{
							bb = by[pos];
							gg = by[pos + 1];
							rr = by[pos + 2];
						}
						else if(style2.getBackground() == "red")
						{
							bb = 0;
							gg = 0;
							rr = by[pos + 2];
						}
						else if(style2.getBackground() == "green")
						{
							bb = 0;
							gg = by[pos + 1];
							rr = 0;
						}
						else if(style2.getBackground() == "blue")
						{
							bb = by[pos];
							gg = 0;
							rr = 0;
						}
						else if(style2.getBackground() == "cyan")
						{
							bb = by[pos];
							gg = by[pos + 1];
							rr = 0;
						}
						else if(style2.getBackground() == "yellow")
						{
							bb = 0;
							gg = by[pos + 1];
							rr = by[pos + 2];
						}
						else if(style2.getBackground() == "purple")
						{
							bb = by[pos];
							gg = 0;
							rr = by[pos + 2];
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
						pos += 3;
					}
					pos += cha;
					
					
				}
				for(int o = y - 1;o >= 0;o--)
				{
					for(int p = 0;p < x;p++)
					{
						gra.setPaint(color[o][p]);
						Rectangle2D rec = new Rectangle(p,y - o, 1, 1);
						gra.draw(rec);
					}
				}
				x = 0;
				y = 0;
				by = null;
			}
			
			if(style.getStyle().equals("left > right"))
			{
				for (i = 0; i < y; i++)
				{
					for (j = 0; j < x; j++)
					{
						int bb = 0;
						int gg = 0;
						int rr = 0;
						if(style2.getBackground() == "normal")
						{
							bb = by[pos];
							gg = by[pos + 1];
							rr = by[pos + 2];
						}
						else if(style2.getBackground() == "red")
						{
							bb = 0;
							gg = 0;
							rr = by[pos + 2];
						}
						else if(style2.getBackground() == "green")
						{
							bb = 0;
							gg = by[pos + 1];
							rr = 0;
						}
						else if(style2.getBackground() == "blue")
						{
							bb = by[pos];
							gg = 0;
							rr = 0;
						}
						else if(style2.getBackground() == "cyan")
						{
							bb = by[pos];
							gg = by[pos + 1];
							rr = 0;
						}
						else if(style2.getBackground() == "yellow")
						{
							bb = 0;
							gg = by[pos + 1];
							rr = by[pos + 2];
						}
						else if(style2.getBackground() == "purple")
						{
							bb = by[pos];
							gg = 0;
							rr = by[pos + 2];
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
						pos += 3;
					}
					pos += cha;
				}

				for(int p = 0;p < x;p++)
				{
					for(int o = y - 1;o >= 0;o--)
					{	
						gra.setPaint(color[o][p]);
						Rectangle2D rec = new Rectangle(p,y - o, 1, 1);
						gra.draw(rec);
					}
				}
				x = 0;
				y = 0;
				by = null;
			}
			
			
			if(style.getStyle().equals("right > left"))
			{
				for (i = 0; i < y; i++)
				{
					for (j = 0; j < x; j++)
					{					
						int bb = 0;
						int gg = 0;
						int rr = 0;
						if(style2.getBackground() == "normal")
						{
							bb = by[pos];
							gg = by[pos + 1];
							rr = by[pos + 2];
						}
						else if(style2.getBackground() == "red")
						{
							bb = 0;
							gg = 0;
							rr = by[pos + 2];
						}
						else if(style2.getBackground() == "green")
						{
							bb = 0;
							gg = by[pos + 1];
							rr = 0;
						}
						else if(style2.getBackground() == "blue")
						{
							bb = by[pos];
							gg = 0;
							rr = 0;
						}
						else if(style2.getBackground() == "cyan")
						{
							bb = by[pos];
							gg = by[pos + 1];
							rr = 0;
						}
						else if(style2.getBackground() == "yellow")
						{
							bb = 0;
							gg = by[pos + 1];
							rr = by[pos + 2];
						}
						else if(style2.getBackground() == "purple")
						{
							bb = by[pos];
							gg = 0;
							rr = by[pos + 2];
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
						pos += 3;
					}
					pos += cha;
				}
				
				for(int p = x - 1;p >= 0;p--)
				{
					for(int o = y - 1;o >= 0;o--)
					{
						gra.setPaint(color[o][p]);
						Rectangle2D rec = new Rectangle(p,y - o, 1, 1);
						gra.draw(rec);
					}
				}
				x = 0;
				y = 0;
				by = null;
			}
		}
	}	
}



//用于监听画图方向控制信息改变的类
class Style implements ActionListener
{
	Sstyle style;
	JRadioButtonMenuItem item211;
	JRadioButtonMenuItem item212;
	JRadioButtonMenuItem item213;
	JRadioButtonMenuItem item214;
	
	JRadioButtonMenuItem radio;
	
	Style(Sstyle style,JRadioButtonMenuItem item211,JRadioButtonMenuItem item212,JRadioButtonMenuItem item213,JRadioButtonMenuItem item214)
	{
		this.style = style;
		this.item211 = item211;
		this.item212 = item212;
		this.item213 = item213;
		this.item214 = item214;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		radio = (JRadioButtonMenuItem)e.getSource();
		
		if(radio == item211)
		{
			style.setStyle("top > down");
		}
		else if(radio == item212)
		{
			style.setStyle("down > top");
		}
		else if(radio == item213)
		{
			style.setStyle("left > right");
		}
		else if(radio == item214)
		{
			style.setStyle("right > left");
		}
		
	}
}

//此类用于控制画图的方向
class Sstyle
{
	String style = "down > top";

	public String getStyle()
	{
		return style;
	}

	public void setStyle(String style)
	{
		this.style = style;
	}
	
	
}


//此类用于清除画布中的图像
class Clear implements ActionListener
{
	Canvas can;
	
	Clear(Canvas can)
	{
		this.can = can;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		can.getGraphics().clearRect(0, 0,can.getWidth(),can.getHeight());	
	}
}

//此类用于推出程序
class MyExit implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.exit(0);
	}
}


//定义一个用于向外界传递信息的类
class TransInfo
{
	int outWidth = 0;                      //用于保存压缩后图像的宽
	int outHeight = 0;                     //用于保存压缩后图像的高
	byte[] outBy1 = null;                     //用于保存压缩后的图像信息
	byte[] outBy2 = null;                     //用于保存压缩前的图像信息
	int outCha = 0;                        //保存监听程序中创建的值
	int outPos = 0;                        //保存监听程序中创建的值
	public int getOutWidth()
	{
		return outWidth;
	}
	public void setOutWidth(int outWidth)
	{
		this.outWidth = outWidth;
	}
	public int getOutHeight()
	{
		return outHeight;
	}
	public void setOutHeight(int outHeight)
	{
		this.outHeight = outHeight;
	}
	public byte[] getOutBy1()
	{
		return outBy1;
	}
	public void setOutBy1(byte[] outBy1)
	{
		this.outBy1 = outBy1;
	}
	public byte[] getOutBy2()
	{
		return outBy2;
	}
	public void setOutBy2(byte[] outBy2)
	{
		this.outBy2 = outBy2;
	}
	public int getOutCha()
	{
		return outCha;
	}
	public void setOutCha(int outCha)
	{
		this.outCha = outCha;
	}
	public int getOutPos()
	{
		return outPos;
	}
	public void setOutPos(int outPos)
	{
		this.outPos = outPos;
	}
}














