package com.lyh.paintPicture;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButtonMenuItem;

public class OpenBackground implements ActionListener
{
	Sstyle2 color;
	
	JRadioButtonMenuItem item511;
	JRadioButtonMenuItem item512;
	JRadioButtonMenuItem item513;
	JRadioButtonMenuItem item514;
	JRadioButtonMenuItem item515;
	JRadioButtonMenuItem item516;
	JRadioButtonMenuItem item517;
	
	JRadioButtonMenuItem radio;
	
	OpenBackground(Sstyle2 color,JRadioButtonMenuItem item511,JRadioButtonMenuItem item512,JRadioButtonMenuItem item513,JRadioButtonMenuItem item514,JRadioButtonMenuItem item515,JRadioButtonMenuItem item516,JRadioButtonMenuItem item517)
	{
		this.color = color;
		this.item511 = item511;
		this.item512 = item512;
		this.item513 = item513;
		this.item514 = item514;
		this.item515 = item515;
		this.item516 = item516;
		this.item517 = item517;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		radio = (JRadioButtonMenuItem)e.getSource();
		if(radio == item511)
		{
			color.setBackground("normal");
		}
		else if(radio == item512)
		{
			color.setBackground("red");
		}
		else if(radio == item513)
		{
			color.setBackground("green");
		}
		else if(radio == item514)
		{
			color.setBackground("blue");
		}
		else if(radio == item515)
		{
			color.setBackground("cyan");   //青色
		}
		else if(radio == item516)
		{
			color.setBackground("yellow");
		}
		else if(radio == item517)
		{
			color.setBackground("purple");
		}
		
	}
}

//创建一个用于控制图像背景色的类
class Sstyle2
{
	String background = "normal";

	public String getBackground()
	{
		return background;
	}

	public void setBackground(String background)
	{
		this.background = background;
	}
}