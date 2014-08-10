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
					"�㻹û��ѹ��ͼƬ������ѹ�����ԣ�", "��Ϣ��",
					JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			FileDialog dialog = new FileDialog(frame, "����", FileDialog.SAVE);
			dialog.setVisible(true);
			String path = dialog.getDirectory();
			String file = dialog.getFile();
			String filePath = path + file;
			
			//��ѹ�����ͼƬ���浽�ⲿ�ļ���	
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
