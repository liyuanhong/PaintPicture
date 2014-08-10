package com.lyh.paintPicture;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//��־���ѹ��ͼ����
class MyCompress implements ActionListener
{
	int x;
	int y;
	byte by[];
	int cha;
	int pos;
	JLabel label;
	JFrame frame;
	int k = 0;
	Canvas can;
	TransInfo info;
	ToCompress toCompress;
	Sstyle style;
	Sstyle1 style1;
	Sstyle2 style2;

	MyCompress(JLabel label, JFrame frame,Canvas can, TransInfo info,
			ToCompress toCompress, Sstyle style,Sstyle1 style1, Sstyle2 style2)
	{
		this.info = info;
		this.label = label;
		this.frame = frame;
		this.can = can;
		this.style = style;
		this.style1 = style1;
		this.style2 = style2;

		this.toCompress = toCompress;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		toCompress.setParameter(label, frame, can, info, style,style1, style2);
		toCompress.compress();

	}
}

class ToCompress
{
	int x;
	int y;
	byte by[];
	int cha;
	int pos;
	JLabel label;
	JFrame frame;
	int k = 0;
	Canvas can;
	TransInfo info;
	Sstyle style;
	Sstyle1 style1;
	Sstyle2 style2;

	public void setParameter(JLabel label, JFrame frame,Canvas can,
			TransInfo info, Sstyle style,Sstyle1 style1,Sstyle2 style2)
	{
		this.info = info;
		this.label = label;
		this.frame = frame;
		this.can = can;
		this.style = style;
		this.style1 = style1;
		this.style2 = style2;
	}

	public void compress()
	{
		this.x = info.getOutWidth();
		this.y = info.getOutHeight();
		this.by = info.getOutBy2();
		this.cha = info.getOutCha();
		this.pos = info.getOutPos();

		Graphics g = can.getGraphics();
		ArrayList<Color> list = new ArrayList<Color>();
		byte[][] tcolor = new byte[125][3];
		byte[][] trgb = new byte[x * y][3];

		int i;
		int j;
		int count = 0;

		if (x == 0 && y == 0 && by == null)
		{
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(frame, "�㻹û�д�ͼƬ,�޷�ѹ����", "��Ϣ��",
					JOptionPane.INFORMATION_MESSAGE);
		} else
		{
			// ����ɫֵ
			for (i = 0; i < y; i++)
			{
				for (j = 0; j < x; j++)
				{
					int bb = by[pos];
					int gg = by[pos + 1];
					int rr = by[pos + 2];

					if (bb < 0)
					{
						bb = bb + 256;
						if (0 <= bb && bb <= 51)
						{
							bb = 26;
						} else if (51 < bb && bb <= 102)
						{
							bb = 77;
						} else if (102 < bb && bb <= 153)
						{
							bb = 128;
						} else if (153 < bb && bb <= 204)
						{
							bb = 179;
						} else if (204 < bb && bb <= 255)
						{
							bb = 230;
						}
					} else
					{
						if (0 <= bb && bb <= 51)
						{
							bb = 26;
						} else if (51 < bb && bb <= 102)
						{
							bb = 77;
						} else if (102 < bb && bb <= 153)
						{
							bb = 128;
						} else if (153 < bb && bb <= 204)
						{
							bb = 179;
						} else if (204 < bb && bb <= 255)
						{
							bb = 230;
						}
					}
					if (gg < 0)
					{
						gg = gg + 256;
						if (0 <= gg && gg <= 51)
						{
							gg = 26;
						} else if (51 < gg && gg <= 102)
						{
							gg = 77;
						} else if (102 < gg && gg <= 153)
						{
							gg = 128;
						} else if (153 < gg && gg <= 204)
						{
							gg = 179;
						} else if (204 < gg && gg <= 255)
						{
							gg = 230;
						}
					} else
					{
						if (0 <= gg && gg <= 51)
						{
							gg = 26;
						} else if (51 < gg && gg <= 102)
						{
							gg = 77;
						} else if (102 < gg && gg <= 153)
						{
							gg = 128;
						} else if (153 < gg && gg <= 204)
						{
							gg = 179;
						} else if (204 < gg && gg <= 255)
						{
							gg = 230;
						}
					}
					if (rr < 0)
					{
						rr = rr + 256;
						if (0 <= rr && rr <= 51)
						{
							rr = 26;
						} else if (51 < rr && rr <= 102)
						{
							rr = 77;
						} else if (102 < rr && rr <= 153)
						{
							rr = 128;
						} else if (153 < rr && rr <= 204)
						{
							rr = 179;
						} else if (204 < rr && rr <= 255)
						{
							rr = 230;
						}
					} else
					{
						if (0 <= rr && rr <= 51)
						{
							rr = 26;
						} else if (51 < rr && rr <= 102)
						{
							rr = 77;
						} else if (102 < rr && rr <= 153)
						{
							rr = 128;
						} else if (153 < rr && rr <= 204)
						{
							rr = 179;
						} else if (204 < rr && rr <= 255)
						{
							rr = 230;
						}
					}
					Color c = new Color(rr, gg, bb);
					int num = i * x + j;
					trgb[num][0] = (byte) rr;
					trgb[num][1] = (byte) gg;
					trgb[num][2] = (byte) bb;

					// ����򻯺����ɫ
					if (list.size() == 0)
					{

						list.add(c);
						tcolor[count][0] = (byte) rr;
						tcolor[count][1] = (byte) gg;
						tcolor[count][2] = (byte) bb;
						count++;
					} else
					{
						if (isColorExit(list, c))
						{

						} else
						{
							list.add(c); // ��ɫ�����ڣ�����ӵ�list��
							tcolor[count][0] = (byte) rr;
							tcolor[count][1] = (byte) gg;
							tcolor[count][2] = (byte) bb;
							count++;
						}
					}
					pos += 3;
				}
				pos += cha;
			}

			// ȥ����ɫ�������Ĳ���
			byte[][] color = new byte[count][3];
			for (int k = 0; k < count; k++)
			{
				color[k][0] = tcolor[k][0];
				color[k][1] = tcolor[k][1];
				color[k][2] = tcolor[k][2];
			}
			tcolor = null;

			// ����ɫ����ɫ�����е�λ�ô�ŵ�һά������
			byte[] theColor = new byte[x * y];
			for (int l = 0; l < x * y; l++)
			{
				theColor[l] = (byte) ColorNum(trgb[l], color);
			}

			// ��ͼƬ��������ɫ��ŵ�һά������
			int len = 3 * list.size();
			byte[] allColor = new byte[len];
			for (int n = 0; n <= (list.size() - 1) * 3; n += 3)
			{
				allColor[n] = color[n / 3][0];
				allColor[n + 1] = color[n / 3][1];
				allColor[n + 2] = color[n / 3][2];
			}

			ByteArrayOutputStream byOut = new ByteArrayOutputStream();
			byte[] byy = null;
			try
			{
				byOut.write("b1".getBytes());
				byOut.write(int2byte(x));
				byOut.write(int2byte(y));
				byOut.write(list.size());
				byOut.write(allColor);
				byOut.write(theColor);
				byy = byOut.toByteArray();
			} catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			info.setOutBy1(byy); // ��ͼƬ����Ϣ���ݵ������
			MyCompressPaint com = new MyCompressPaint();
			com.setParameter(label, byy, g, style,style1, style2);
			com.start();

			x = 0;
			y = 0;
			by = null;
		}
	}

	// �ж���ɫ�Ƿ����
	public boolean isColorExit(ArrayList<Color> list, Color c)
	{
		for (Color ite : list)
		{

			if ((ite.equals(c)))
			{
				return true;
			} else
			{

			}
		}
		return false;
	}

	// ������ɫ���������е��±�
	public int ColorNum(byte[] rgb, byte[][] color)
	{
		for (int i = 0; i < color.length; i++)
		{
			if (rgb[0] == color[i][0] && rgb[1] == color[i][1]
					&& rgb[2] == color[i][2])
			{
				return i;
			}
		}
		return 0;
	}

	public static byte[] int2byte(int res)
	{
		byte[] targets = new byte[4];
		targets[0] = (byte) (res & 0xff);// ���λ
		targets[1] = (byte) ((res >> 8) & 0xff);// �ε�λ
		targets[2] = (byte) ((res >> 16) & 0xff);// �θ�λ
		targets[3] = (byte) (res >>> 24);// ���λ,�޷������ơ�
		return targets;
	}

}

// �������ڽ�ѹ�����ͼ�񻭳���
class MyCompressPaint extends Thread
{
	JLabel label;
	byte[] by1;
	Graphics g;
	Sstyle style;
	Sstyle1 style1;
	Sstyle2 style2;

	public void setParameter(JLabel label, byte[] by1, Graphics g,
			Sstyle style,Sstyle1 style1, Sstyle2 style2)
	{
		this.label = label;
		this.by1 = by1;
		this.g = g;
		this.style = style;
		this.style1 = style1;
		this.style2 = style2;
	}

	// �˷������ڽ�ѹ�����ͼ�񻭳���
	@Override
	public void run()
	{

		String str = new String("");

		byte[] byt1 = new byte[4];
		byte[] byt2 = new byte[4];

		for (int i = 0; i < 4; i++)
		{
			byt1[i] = by1[2 + i];
			byt2[i] = by1[6 + i];
		}

		// ����ļ��Ŀ��
		int width = byte2int(byt1);
		// ����ļ��ĸ߶�
		int height = byte2int(byt2);
		// ��ȡ��ɫ�ĸ���
		int colNum = by1[10];

		// ����һ��һά���飬��ȡ�����е���ɫ
		byte[] allCol = new byte[colNum * 3];
		for (int i = 0; i < colNum * 3; i++)
		{
			allCol[i] = by1[11 + i];
		}
		// ��һά�����е���ɫת��Ϊ��ά�����е���ɫ
		byte allColor[][] = new byte[colNum][3];
		for (int i = 0; i < colNum; i++)
		{
			allColor[i][0] = allCol[i * 3];
			allColor[i][1] = allCol[i * 3 + 1];
			allColor[i][2] = allCol[i * 3 + 2];
		}
		// ����һ��һά�����ȡͼƬ����ɫ��Ϣ
		byte[] picColor = new byte[width * height];
		for (int i = 0; i < width * height; i++)
		{
			picColor[i] = by1[11 + allCol.length + i];
		}

		str = str + "   ��ȣ�" + width + "���أ�  �߶ȣ�" + height + "���أ�"
				+ "    ��ɫ������ " + colNum;
		label.setText(str);

		Graphics2D gra = (Graphics2D) g;
		Color color[][] = new Color[height][width];

		int i;
		int j;
		if (width == 0 || height == 0 || by1 == null)
		{

		} else
		{
			if (style.getStyle().equals("down > top"))
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
						if (style2.getBackground() == "normal")
						{
							rr = by11[0]; // by1[0]
							gg = by11[1]; // by1[1]
							bb = by11[2]; // by1[2]
						} else if (style2.getBackground() == "red")
						{
							bb = 0;
							gg = 0;
							rr = by11[0];
						} else if (style2.getBackground() == "green")
						{
							bb = 0;
							gg = by11[1];
							rr = 0;
						} else if (style2.getBackground() == "blue")
						{
							bb = by11[2];
							gg = 0;
							rr = 0;
						} else if (style2.getBackground() == "cyan")
						{
							gg = by11[1]; // by1[1]
							bb = by11[2]; // by1[2]
							rr = 0;
						} else if (style2.getBackground() == "yellow")
						{
							bb = 0;
							rr = by11[0]; // by1[0]
							gg = by11[1]; // by1[1]
						} else if (style2.getBackground() == "purple")
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

	public static int byte2int(byte[] res)
	{
		// һ��byte��������24λ���0x??000000��������8λ���0x00??0000

		int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00)
				| ((res[2] << 24) >>> 8) | (res[3] << 24);
		return targets;
	}
}
