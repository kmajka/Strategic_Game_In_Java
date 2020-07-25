package com.bwizard.cegame.controls.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.bwizard.cegame.controls.components.BaseDrawFigure;
import com.bwizard.cegame.documents.handlers.Align;
import com.bwizard.cegame.documents.handlers.GlobalPosition;
import com.bwizard.cegame.tools.ArgumentValidator;
import com.bwizard.cegame.utils.StringUtil;

public class TextProvider implements Cloneable {
	
	private String text = "";
	private int fontStyle;
	private GlobalPosition globalPosition;
	
	private String fontName;
	private int fontSize;
	private int baseFontStyle;
	private int focusedFontStyle;
	
	private Color fontColor;
	private Color mainFontColor;
	private Color selectedFontColor;
	private Color focusedFontColor;
	
	private int maxLength = 0;
	
	private String placeHolder;
	
	private int width;
	private int height;
	private int x;	
	private int y;
	
	private Font font;
	
	private Align align;
	
	private boolean cursorOverFigure;
	
	public TextProvider() {
		fontSize = 16;
		fontName = "Times New Roman";
		baseFontStyle = 0;
		align = Align.CENTER;
		fontColor = Color.BLACK;
		mainFontColor = Color.BLACK;
		focusedFontColor = Color.BLACK;
		selectedFontColor = Color.BLACK;
		focusedFontStyle = 0;		
		placeHolder = "";
	}
	
	public boolean isNeeded() {
		return StringUtil.isNotEmpty(text);
	}
		
	public void updateFontColor(BaseDrawFigure baseFigure) {
		
		if(baseFigure.getMainFontColor() != null) {
			setMainFontColor(baseFigure.getMainFontColor());
		}
		
		if(baseFigure.getFocusedFontColor() != null) {
			setFocusedFontColor(baseFigure.getFocusedFontColor());
		}
		
		if(baseFigure.getSelectedFontColor() != null) {
			setSelectedFontColor(baseFigure.getSelectedFontColor());
		}
		
	}
	
	public void updateFont(BaseDrawFigure baseFigure) {
		
		//clear font - during first getting font ,the font will be created
		font = null;
		
		ArgumentValidator.checkForNull("fontName", baseFigure.getFontName());
		ArgumentValidator.checkForNull("fontSize", baseFigure.getFontSize());
		ArgumentValidator.checkForNull("fontStyle", baseFigure.getFontStyle());
		
		setFontName(baseFigure.getFontName());
		setFontSize(baseFigure.getFontSize());
		setBaseFontStyle( baseFigure.getFontStyle());
		setFocusedFontStyle(baseFigure.getFocusedFontStyle());

	}
	
	public void displayRealText(Graphics g, BaseDrawFigure b) {
				
		g.setFont(getFont());
		g.setColor(getFontColor(true));
				
		FontMetrics metrics = g.getFontMetrics(font);
		int hgt = metrics.getHeight() * 2;
		int h = hgt;
		final int w = b.getMarginLeft();
		boolean res = false;
		StringBuilder sentence = new StringBuilder();
		StringBuilder word = new StringBuilder();
		boolean isWord = false;
		for (char c : text.toCharArray()) {
			res = true;
			
			word.append(c);
			if (' ' ==  c ) {
				isWord = true;
			}
			
			if (metrics.stringWidth(sentence.toString()) + metrics.stringWidth(word.toString())  > width - b.getMarginLeft() - b.getMarginRight()) {
				g.drawString(sentence.toString(), x + w, y + h);
				h += hgt;
				sentence.setLength(0);
				res = false;
			} else {
				if (isWord) {
					sentence.append(word);
					word.setLength(0);
					isWord = false;
				}
			}
				
		}
		
		if (res) {
			sentence.append(word);
			g.drawString(sentence.toString() , x + w, y + h);
		}
		
	}
	
	public void display(Graphics g) {
		
		g.setFont(getFont());
		g.setColor(getFontColor(true));
		
		FontMetrics metrics = g.getFontMetrics(font);
		int hgt = metrics.getHeight();
		
		//for better visual view there is needed to move text depend on the size font
		int temp = ((int)(g.getFont().getSize() / 10) * 2) + 2;
		
		int w = metrics.stringWidth(text);
		int widthMoveText = 0;
		int hightMoveText = 0;
		
		switch (align) {
		case RIGHT:
			widthMoveText = width - w;
			hightMoveText = (height - hgt) / 2;
			break;
		case CENTER:
			widthMoveText = (width - w) / 2;
			hightMoveText = (height - hgt) / 2;
			break;
		case LEFT:
			widthMoveText = 0;
			hightMoveText = (height - hgt) / 2;
			break;
		default:
			widthMoveText = (width - w) / 2;
			hightMoveText = (height - hgt) / 2;
			break;
		}
		
		g.drawString(text, x + widthMoveText, y + height - hightMoveText - temp);
		
	}
	
	public Object clone() {
		
		TextProvider cloned = null;
		try {
			cloned = (TextProvider)super.clone();
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cloned;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		if(canUpdateText(text)) {
			this.text = text;
		}
	}
	
	private boolean canUpdateText(String text) {
		//check with special character '_'
		return maxLength == 0 || (text.length() <= maxLength + 1);
	}

	
	public int getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(int fontStyle) {
		this.fontStyle = fontStyle;
	}

	public GlobalPosition getGlobalPosition() {
		return globalPosition;
	}

	public void setGlobalPosition(GlobalPosition globalPosition) {
		this.globalPosition = globalPosition;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public int getBaseFontStyle() {
		return baseFontStyle;
	}

	public void setBaseFontStyle(int baseFontStyle) {
		this.baseFontStyle = baseFontStyle;
		this.fontStyle = baseFontStyle;
	}

	public Color getFontColor() {
		return fontColor;
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	public Color getMainFontColor() {
		return mainFontColor;
	}

	public void setMainFontColor(Color mainFontColor) {
		this.mainFontColor = mainFontColor;
	}

	public Color getSelectedFontColor() {
		return selectedFontColor;
	}

	public void setSelectedFontColor(Color selectedFontColor) {
		this.selectedFontColor = selectedFontColor;
	}

	public Color getFocusedFontColor() {
		return focusedFontColor;
	}

	public void setFocusedFontColor(Color focusedFontColor) {
		this.focusedFontColor = focusedFontColor;
	}

	private void prepareFont() {
		font = new Font(fontName, fontStyle, fontSize);
		//font = new Font("Times New Roman", 3, 50);
	}
	
	private Font getFont() {
		if(font == null) {
			prepareFont();
		}
		return font;
	}
	
	public void setCursorOverFigure(boolean cursorOverFigure) {
		this.cursorOverFigure = cursorOverFigure;
	}
	
	public boolean isCursorOverFigure() {
		return this.cursorOverFigure;
	}
	
	private Color getFontColor(boolean actualState) {
		if (actualState) {
			if(isCursorOverFigure()) {
				if ((getFocusedFontStyle() > -1 && getFontStyle() != getFocusedFontStyle()) || getFontColor() != getFocusedFontColor()) {
					if(getFocusedFontStyle() > -1) {
						setFontStyle(getFocusedFontStyle());
					}
					setFontColor(getFocusedFontColor());
					prepareFont();
				}
			} else {
				
				if (getFontStyle() != getBaseFontStyle() || getFontColor() != getMainFontColor()) {
					setFontStyle(getBaseFontStyle());
					setFontColor(getMainFontColor());
					prepareFont();
				}	
			}
		}
		return fontColor;
	}

	public int getFocusedFontStyle() {
		return focusedFontStyle;
	}

	public void setFocusedFontStyle(int focusedFontStyle) {
		this.focusedFontStyle = focusedFontStyle;
	}

	public Align getAlign() {
		return align;
	}

	public void setAlign(Align align) {
		this.align = align;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public String getPlaceHolder() {
		return placeHolder;
	}

	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
	}

}
