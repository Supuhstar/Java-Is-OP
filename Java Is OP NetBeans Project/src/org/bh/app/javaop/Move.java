package org.bh.app.javaop;

import java.awt.event.KeyEvent;

/**
 * Move, made for Java Is OP NetBeans Project, is copyright Blue Husky Programming ©2014 GPLv3 <hr/>
 * 
 * @author Kyli of Blue Husky Programming
 * @version 1.0.0
 *		- 2014-12-03 (1.0.0) - Kyli created Move
 * @since 2014-12-03
 */
public class Move
{
	public static enum MoveType
	{
		KEY_TYPE, KEY_TYPE_STR, KEY_DOWN, KEY_UP,
		MOUSE_CLICK, MOUSE_DOWN, MOUSE_UP,
		DRAW_STR,
		WAIT
	}
	
	public final MoveType TYPE;
	public final Object MOVE_DATA;

	public Move(MoveType initType, Object moveData)
	{
		TYPE = initType;
		MOVE_DATA = moveData;
	}
	
	public String moveDataStr()
	{
		return String.valueOf(MOVE_DATA);
	}
	
	public int moveDataInt()
	{
		return (Integer)MOVE_DATA;
	}

	@Override
	public String toString()
	{
		switch (TYPE)
		{
			case KEY_DOWN:
			case KEY_UP:
			case KEY_TYPE:
				return TYPE.name() + ": " + KeyEvent.getKeyText(moveDataInt());
			case DRAW_STR:
			case KEY_TYPE_STR:
				return TYPE.name() + ": " + moveDataStr();
			case MOUSE_CLICK:
			case MOUSE_DOWN:
			case MOUSE_UP:
				return TYPE.name();
			case WAIT:
				return TYPE.name() + " for " + (moveDataInt() / 1000d) + " seconds";
			default:
				throw new AssertionError();
		}
	}
}
