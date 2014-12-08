package org.bh.app.javaop;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * TextBitmapper, made for Java Is OP NetBeans Project, is copyright Blue Husky Programming ©2014 GPLv3 <hr/>
 * 
 * @author Kyli of Blue Husky Programming
 * @version 1.0.0
 *		- 2014-12-04 (1.0.0) - Kyli created TextBitmapper
 * @since 2014-12-04
 */
public class TextBitmapper
{
	static final GraphicsEnvironment GE;
	static
	{
		GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
	}
	public static BufferedImage textToImage(String text, Font font)
	{
		if (font == null)
			font = new Font("Courier New", Font.PLAIN, 12);
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), false, false);
		Rectangle2D bounds = font.getStringBounds(text, frc);
		BufferedImage bi = new BufferedImage(
			(int)(bounds.getWidth() + .5),
			(int)(bounds.getHeight() + .5),
			BufferedImage.TYPE_BYTE_BINARY
		);
		Graphics2D g = GE.createGraphics(bi);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
		g.setColor(Color.BLACK);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics();
		g.drawString(text, 0, fm.getAscent());
		g.dispose();
		return bi;
	}
}
