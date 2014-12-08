package org.bh.app.javaop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Robot;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import org.bh.app.javaop.Move.MoveType;
import static org.bh.app.javaop.TextBitmapper.textToImage;

/**
 * Main, made for Java Is OP NetBeans Project, is copyright Blue Husky Programming ©2014 GPLv3 <hr/>
 * 
 * @author Kyli of Blue Husky Programming
 * @version 1.0.0
 *		- 2014-12-03 (1.0.0) - Kyli created Main
 * @since 2014-12-03
 */
public class Main
{
	static
	{
		Font f;
		
		JOptionPane jop = new JOptionPane();
		f = jop.getFont();
		if (f == null)
		{
			jop.showConfirmDialog(null, "Is Java OP?", "Select", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			f = jop.getFont();
			if (f == null)
			{
				System.out.println("There's no hope.");
				System.exit(45);
			}
		}
		
		DEF_FONT = f.deriveFont(40f);
	}
	
	public static final String APP_NAME = "Java Is OP NetBeans Project";
	public static final String APP_VERSION = "1.0.0";
	
	public static final int DELAY = 12;
	public static final Point DRAW_OFFSET = new Point(50, 300);
	public static final Font DEF_FONT;
	public static final String STRING_TO_DRAW = "Java is OP";
	public static final BufferedImage IMAGE_TO_DRAW = textToImage(STRING_TO_DRAW, DEF_FONT);
	public static final Dimension CANVAS_SIZE =
		new Dimension(DRAW_OFFSET.x + (IMAGE_TO_DRAW.getWidth()* 2), DRAW_OFFSET.y + (IMAGE_TO_DRAW.getHeight()* 2));
	
	private static final Move[] MOVE_SET = {
		new Move(MoveType.KEY_TYPE, KeyEvent.VK_WINDOWS),   // open Start
		
		new Move(MoveType.WAIT,     100),                   // wait a bit for Start to open
		
		new Move(MoveType.KEY_TYPE_STR, "paint"),           // type "paint"
		new Move(MoveType.WAIT,     2000),                  // wait a couple seconds for search to complete
		new Move(MoveType.KEY_TYPE, KeyEvent.VK_ENTER),     // select in search results
		   
		new Move(MoveType.WAIT,     1000),                  // wait a second for Paint to open
		   
		new Move(MoveType.KEY_DOWN, KeyEvent.VK_WINDOWS),   // Pit it to the left
		new Move(MoveType.KEY_TYPE, KeyEvent.VK_LEFT),
		new Move(MoveType.KEY_UP,   KeyEvent.VK_WINDOWS),
		
		new Move(MoveType.WAIT,     100),                   // wait a bit for the window to react
		
		new Move(MoveType.KEY_DOWN, KeyEvent.VK_WINDOWS),   // Maximize it
		new Move(MoveType.KEY_TYPE, KeyEvent.VK_UP),
		new Move(MoveType.KEY_UP,   KeyEvent.VK_WINDOWS),
		
		new Move(MoveType.WAIT,     100),                   // wait a bit for the window to react
		
		
		new Move(MoveType.KEY_DOWN, KeyEvent.VK_CONTROL),   // Open resize dialog
		new Move(MoveType.KEY_TYPE, KeyEvent.VK_W),
		new Move(MoveType.KEY_UP,   KeyEvent.VK_CONTROL),
		
		new Move(MoveType.WAIT,     100),                   // wait half a second for the dialog to open
		
		new Move(MoveType.KEY_TYPE, KeyEvent.VK_TAB),       // Navigate to the "maintain aspect ratio" checkbox
		new Move(MoveType.KEY_TYPE, KeyEvent.VK_TAB),
		new Move(MoveType.KEY_TYPE, KeyEvent.VK_TAB),
		
		new Move(MoveType.KEY_TYPE, KeyEvent.VK_SPACE),     // Uncheck
		
		new Move(MoveType.KEY_DOWN, KeyEvent.VK_SHIFT),     // Navigate up to the "Pixels" radio button
		new Move(MoveType.KEY_TYPE, KeyEvent.VK_TAB),
		new Move(MoveType.KEY_TYPE, KeyEvent.VK_TAB),
		new Move(MoveType.KEY_TYPE, KeyEvent.VK_TAB),
		new Move(MoveType.KEY_UP, KeyEvent.VK_SHIFT),
		new Move(MoveType.KEY_TYPE, KeyEvent.VK_RIGHT),
		
		new Move(MoveType.KEY_TYPE, KeyEvent.VK_SPACE),     // Select "Pixels" mode
		
		new Move(MoveType.KEY_TYPE, KeyEvent.VK_TAB),       // Move to width
		new Move(MoveType.KEY_TYPE_STR, CANVAS_SIZE.width), // Type the width
		new Move(MoveType.KEY_TYPE, KeyEvent.VK_TAB),       // Move to height
		new Move(MoveType.KEY_TYPE_STR, CANVAS_SIZE.height),// Type the height
		
		new Move(MoveType.KEY_TYPE, KeyEvent.VK_ENTER),      // Submit
		
		new Move(MoveType.DRAW_STR, STRING_TO_DRAW),         // Draw text
	};
	
	/**
	 * The main launcher for Java Is OP NetBeans Project
	 * 
	 * @param args the command line arguments
	 */
	public static synchronized void main(String[] args)
	{
		/*JFrame f = new JFrame();
		JLabel l = new JLabel("Press a key");
		f.add(l);
		f.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				l.setText(e.getExtendedKeyCode() + " = " + KeyEvent.getKeyText(e.getExtendedKeyCode()));
			}
		});
		f.setVisible(true);
		if(true)return;*/
		try
		{
			String OS = System.getProperty("os.name");
			if (!OS.toLowerCase().contains("windows"))
				throw new UnsupportedOperationException("Cannot prove Java is OP on " + OS);
			
			final Timer TIMER = new Timer(DELAY, new ActionListener()
			{
				private int step = 0, waiting = Integer.MAX_VALUE;
				private final Robot johnny = new Robot();
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if (step >= MOVE_SET.length)
					{
						System.exit(0);
					}
					Move currentMove = MOVE_SET[step];
					
					System.out.println(System.currentTimeMillis() + ": " + currentMove);
					
					switch (currentMove.TYPE)
					{
						case WAIT:
							/*if (waiting <= 0)
							{
								waiting = Integer.MAX_VALUE;
								break;
							}
							else if (waiting > currentMove.moveDataInt())
								waiting = currentMove.moveDataInt();
							else
								waiting -= DELAY;
							return;*/
							johnny.delay(currentMove.moveDataInt());
							break;
						case KEY_DOWN:
							johnny.keyPress(currentMove.moveDataInt());
							break;
						case KEY_UP:
							johnny.keyRelease(currentMove.moveDataInt());
							break;
						case KEY_TYPE:
							johnny.keyPress(currentMove.moveDataInt());
							johnny.keyRelease(currentMove.moveDataInt());
							break;
						case KEY_TYPE_STR:
							String data = currentMove.moveDataStr();
							boolean caps;
							char tuc;
							for (char c : data.toCharArray())
							{
								if (caps = Character.isUpperCase(c))
									johnny.keyPress(KeyEvent.VK_SHIFT);

								johnny.keyPress(tuc = Character.toUpperCase(c));
								johnny.keyRelease(tuc);

								if (caps)
									johnny.keyRelease(KeyEvent.VK_SHIFT);
							}
							break;
						case DRAW_STR:
							
//							JOptionPane.showOptionDialog(null, "", STRING_TO_DRAW, JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, new ImageIcon(image), new Object[]{}, null);
							
							
							final int
								HEIGHT = IMAGE_TO_DRAW.getHeight(),
								WIDTH = IMAGE_TO_DRAW.getWidth();
							
							System.out.println(
								"Drawing (" + WIDTH + " x " + HEIGHT + ") pixels");
							
							drawLoop:
							for (int y = 0; y < HEIGHT; y++)
							{
								for (int x = 0; x < WIDTH; x++)
								{
									int px = IMAGE_TO_DRAW.getRGB(x, y);
									System.out.print("\r\nDRAWING PIXEL (" + x + "," + y + "): \t");
									Color c = new Color(px);
									System.out.print(c);
									
									float[] f = c.getColorComponents(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
									System.out.print(" \t" + f[0]);
									
									if (f[0] > 0.5)
										continue;
									
									int
										TRUE_X = DRAW_OFFSET.x + x,
										TRUE_Y = DRAW_OFFSET.y + y;
									johnny.mouseMove(TRUE_X, TRUE_Y);
									johnny.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
									johnny.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
									johnny.delay(DELAY);
								}
							}
							
							break;
						default:
							throw new AssertionError();
					}
					step++;
				}
			});
			TIMER.start();
		}
		catch (Throwable t)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Fatal Error: " + t.getMessage(), t);
		}
	}
}
