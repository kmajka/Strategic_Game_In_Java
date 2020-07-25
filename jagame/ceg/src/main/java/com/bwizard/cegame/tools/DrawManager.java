package com.bwizard.cegame.tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

import com.bwizard.cegame.controls.components.BaseDrawFigure;
import com.bwizard.cegame.state.StateInfoManager;

public class DrawManager {
		
	public static void drawPanel(Graphics g, BaseDrawFigure baseDrawFigure) {
		drawPanel(g, baseDrawFigure, false);
	}
	
	public static void drawPanel(Graphics g, BaseDrawFigure baseDrawFigure, boolean flat) {
				
		if(!baseDrawFigure.isTransparent()) {	
			g.setColor(baseDrawFigure.getBackgroundColor(true));
			if(flat) {
				g.fillRect(baseDrawFigure.getX(), baseDrawFigure.getY(), baseDrawFigure.getWidth(), baseDrawFigure.getHeight());
			} else {
				g.fill3DRect(baseDrawFigure.getX(), baseDrawFigure.getY(), baseDrawFigure.getWidth(), baseDrawFigure.getHeight(), true);
			}
		}
	}
	
	public static void drawPanel(Graphics g, int x, int y, int w, int h) {
		g.fill3DRect(x, y, w, h, true);
	}
	
	public static void drawImage(Graphics g, BaseDrawFigure baseDrawFigure) {
		drawImage(g, baseDrawFigure, false);
	}
	
	public static void drawImage(Graphics g, BaseDrawFigure baseDrawFigure, boolean realSize) {
		if(baseDrawFigure.getImage() != null) {
			if (realSize) {
				g.drawImage(baseDrawFigure.getImage(), baseDrawFigure.getX(), baseDrawFigure.getY(), null);
			} else {
				g.drawImage(baseDrawFigure.getImage(), baseDrawFigure.getX(), baseDrawFigure.getY(), baseDrawFigure.getWidth(), baseDrawFigure.getHeight(), null);
			}
		}
	}
	
	
	public static void drawImage(Graphics g, BufferedImage image, int x, int y, int w, int h) {
		if(image != null) {
			g.drawImage(image, x, y, w, h, null);
		}
	}	
	
	public static void drawImageInGameView(Graphics g, BufferedImage image, int x, int y, StateInfoManager stateInfoManager, boolean selected, boolean placeFree) {
		
		 final int posX = x + stateInfoManager.getCameraMapInfo().getCameraX() + stateInfoManager.getViewLayout().getLeftBorderLayout();
		 final int posY = y +  + stateInfoManager.getCameraMapInfo().getCameraY() + + stateInfoManager.getViewLayout().getTopBorderLayout();
		 
		//draw picture should depend on left and top layout of game.
		//position (x,y) of figure(picture) should be absolute into map. (cannot depend on any layout)
		g.drawImage(image, posX , posY , image.getWidth(), image.getHeight(), null);
		
		if (selected) {
			
			Graphics2D g2 = (Graphics2D) g;
			Stroke oldStroke = g2.getStroke();
			
			if (placeFree) {
				g2.setColor(Color.YELLOW);
				g2.setStroke(new BasicStroke(1));
			} else {
				g2.setColor(Color.BLACK);
				g2.setStroke(new BasicStroke(5));
			}
			g2.drawRect(posX, posY, image.getWidth(), image.getHeight());
			g2.setStroke(oldStroke);
			
		}
			
	}
	
	public static void drawRect(Graphics g, int x, int y, int w, int h, Color color, int stroke) {
		Graphics2D g2 = (Graphics2D) g;
		Stroke oldStroke = g2.getStroke();
		g2.setStroke(new BasicStroke(stroke));
		g2.setColor(color);
		g2.drawRect(x, y, w, h);
		g2.setStroke(oldStroke);
	}
	
	public static void drawRect(Graphics g, int x, int y, int w, int h, Color color) {
		g.setColor(color);
		g.drawRect(x, y, w, h);
	}
	
	public static void drawLine(Graphics g, int x1, int y1, int x2, int y2, Color color) {
		g.setColor(color);
		g.drawLine(x1, y1, x2, y2);
	}
	
	public static void drawArc(Graphics g, int x, int y, int width, int height, int startAngle, int arcAngle, Color color) {
		g.setColor(color);
		g.drawArc(x, y, width, height, startAngle, arcAngle);
	}
	
	public static void drawString(Graphics g, int x, int y, String str, Color color) {
		g.setColor(color);
		g.drawString(str, x, y);
	}
	
	public static void fillRect(Graphics g, int x, int y, int w, int h) {
		g.fillRect(x, y, w, h);
	}
	
	public static void fillRect(Graphics g, int x, int y, int w, int h, Color color) {
		g.setColor(color);
		g.fillRect(x, y, w, h);
	}
	
}
