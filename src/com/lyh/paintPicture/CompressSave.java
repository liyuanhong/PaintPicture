package com.lyh.paintPicture;

import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CompressSave implements ActionListener
{
	TransInfo info;
	JFrame frame;
	
	public CompressSave(TransInfo info,JFrame frame)
	{
		this.info = info;
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		byte[] by = info.getOutBy1();
		if(by == null)
		{
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(frame,
					"你还没有压缩图片，请点击压缩在试！", "信息！",
					JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			FileDialog dialog = new FileDialog(frame, "保存", FileDialog.SAVE);
			dialog.setVisible(true);
			String path = dialog.getDirectory();
			String file = dialog.getFile();
			String filePath = path + file;
			
			//将压缩后的图片保存到外部文件中	
			File fileOut = new File(filePath);
			FileOutputStream out;		
			try
			{
				out = new FileOutputStream(fileOut);
				out.write(by);	
			} catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}
}
