package com.lyh.paintPicture;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButtonMenuItem;

public class PictureStyle implements ActionListener
{
	Sstyle1 style;
	
	JRadioButtonMenuItem item31;
	JRadioButtonMenuItem item32;
	JRadioButtonMenuItem item33;
	JRadioButtonMenuItem item34;
	JRadioButtonMenuItem item35;
	
	JRadioButtonMenuItem radio;
	
	PictureStyle(Sstyle1 style,JRadioButtonMenuItem item31,JRadioButtonMenuItem item32,JRadioButtonMenuItem item33,JRadioButtonMenuItem item34,JRadioButtonMenuItem item35)
	{
		this.style = style;
		this.item31 = item31;
		this.item32 = item32;
		this.item33 = item33;
		this.item34 = item34;
		this.item35 = item35;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		radio = (JRadioButtonMenuItem)e.getSource();
		if(radio == item31)
		{
			style.setStyle("normal");
		}
		else if(radio == item32)
		{
			style.setStyle("rihua");
		}
		else if(radio == item33)
		{
			style.setStyle("fupian");
		}
		else if(radio == item34)
		{
			style.setStyle("fudiao");
		}
		else if(radio == item35)
		{
			style.setStyle("heibai");
		}
		
	}
}


class Sstyle1
{
	String style = "normal";

	public String getStyle()
	{
		return style;
	}

	public void setStyle(String style)
	{
		this.style = style;
	}
}