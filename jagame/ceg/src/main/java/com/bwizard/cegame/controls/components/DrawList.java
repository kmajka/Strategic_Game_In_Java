package com.bwizard.cegame.controls.components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import com.bwizard.cegame.controls.components.handlers.DrawListHandler;
import com.bwizard.cegame.controls.components.list.InfoDrawListFigure;
import com.bwizard.cegame.documents.handlers.Arrangement;
import com.bwizard.cegame.documents.layout.GamePanelLayout;
import com.bwizard.cegame.device.model.CursorInfo;
import com.bwizard.cegame.parameters.CmnGlobals;
import com.bwizard.cegame.tools.DrawManager;
import com.bwizard.cegame.utils.ImageUtil;

public class DrawList extends BaseDrawFigure {
	
	private DrawButton drawButton1 = null;
	private DrawButton drawButton2 = null;
	private int buttonSize = 20;
	private Set<InfoDrawListFigure> setInfoDrawListFigure = null;
	private DrawListHandler eventHandler;
	private String topArrow;
	private String bottomArrow;
	private String leftArrow;
	private String rightArrow;
	private String markList;
	private InfoDrawListFigure previousSelectedFigure = null;

	public DrawList() {
		super();
		this.canPressed = true;
		this.autoUnselect = true;
		
		topArrow = "TopArrowDrawList.png";
		bottomArrow = "BottomArrowDrawList.png";
		leftArrow = "LeftArrowDrawList.png";
		rightArrow = "RightArrowDrawList.png";
		markList = "markElement.png";
		
		eventHandler = new DrawListHandler(this);
		
		drawButton1 = new DrawButton();
		drawButton1.setTag("drawButton1");
		drawButton1.setEventHandler(eventHandler);
		
		drawButton2 = new DrawButton();
		drawButton2.setTag("drawButton2");
		drawButton2.setEventHandler(eventHandler);
		
		setInfoDrawListFigure = new HashSet<InfoDrawListFigure>();
		
		drawButton1.setTransparent(true);
		drawButton2.setTransparent(true);
		
	}
		
	public DrawList(CursorInfo cursorInfo, int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.cursorInfo = cursorInfo;
		this.autoUnselect = true;
		
		this.canPressed = true;
		
		eventHandler = new DrawListHandler(this);
		
		drawButton1 = new DrawButton();
		drawButton1.setTag("drawButton1");
		drawButton1.setCursorInfo(cursorInfo);
		drawButton1.setEventHandler(eventHandler);
		
		drawButton2 = new DrawButton();
		drawButton2.setTag("drawButton2");
		drawButton2.setCursorInfo(cursorInfo);
		drawButton2.setEventHandler(eventHandler);
		
		setInfoDrawListFigure = new HashSet<InfoDrawListFigure>();
		
		drawButton1.setTransparent(true);
		drawButton2.setTransparent(true);
	}
	
	private BufferedImage getImageSprite(String file) {
		return ImageUtil.loadImage(CmnGlobals.CONTROLS_LIST_IMAGE_PATH + file);
	}
	
	public Set<InfoDrawListFigure> getItems() {
		return setInfoDrawListFigure;
	}
	
	public void addItem(InfoDrawListFigure item) {
		setInfoDrawListFigure.add(item);
	}
	
	public DrawListHandler getEventHandler() {
		return eventHandler;
	}
	
	@Override
	public void clearSelected() throws Exception {
		
		for(InfoDrawListFigure figure: setInfoDrawListFigure) {	
			if (figure.getBaseDrawFigure() instanceof DrawButton) {
				((DrawButton)figure.getBaseDrawFigure()).setCountPressed(0);
				((DrawButton)figure.getBaseDrawFigure()).setPressed(false);
				((DrawButton)figure.getBaseDrawFigure()).setSelected(false);
				((DrawButton)figure.getBaseDrawFigure()).setSelecting(false);
				previousSelectedFigure = null;
			}
		}
		
		if (eventHandler != null) {
			eventHandler.reset();
		}
	}
	
	@Override
	public void setPressed(boolean pressed) {
		
		if (drawButton1.canPressedFigure()) {
			drawButton1.setPressed(pressed);
		} else if (drawButton2.canPressedFigure()) {
			drawButton2.setPressed(pressed);
		} else {
			
			for(InfoDrawListFigure figure: setInfoDrawListFigure) {
				
				if(figure.getBaseDrawFigure().canPressedFigure()) {
										
					if (figure.getBaseDrawFigure() instanceof DrawButton) {
						
						if (this.isOneChoice()) {
							
							//user pressed other button then earlier, we clear previous button and set image for new button
							if(previousSelectedFigure != null && previousSelectedFigure != figure) {
								((DrawButton)previousSelectedFigure.getBaseDrawFigure()).setCountPressed(0);
								((DrawButton)figure.getBaseDrawFigure()).setCountPressed(0);
							}
							
							//((DrawButton)figure.getBaseDrawFigure()).setImage(getImageSprite(getMarkList()));
							//user pressed "button twice" it should invoke the method
							if (previousSelectedFigure == figure) {
								((DrawButton)figure.getBaseDrawFigure()).setCountPressed(2);
								//((DrawButton)figure.getBaseDrawFigure()).setPressed(pressed);
							} else {
								//"first selected"
								((DrawButton)figure.getBaseDrawFigure()).setCountPressed(1);
								//((DrawButton)figure.getBaseDrawFigure()).setPressed(pressed);
							}
							((DrawButton)figure.getBaseDrawFigure()).setPressed(pressed);
							
							if (disposed) {
								figure = null;
								return;
							}
							//we store actual selected button
							previousSelectedFigure = figure;
						} else {
							((DrawButton)figure.getBaseDrawFigure()).setCountPressed(0);
							((DrawButton)figure.getBaseDrawFigure()).setPressed(pressed);
						}
					}
				}
			}
			
		}
		
	}
	
	@Override
	public void setArrangement(Arrangement arrangement) {
		super.setArrangement(arrangement);
		
		if (Arrangement.VERTICAL.equals(getArrangement())) {
			
			drawButton1.setX(x);
			drawButton1.setY(y);
			drawButton1.setWidth(width);
			drawButton1.setHeight(getButtonSize());
			drawButton1.setCursorInfo(cursorInfo);
			drawButton1.setText("");
			drawButton1.setVisible(true);
			if(drawButton1.getImage() == null) {
				drawButton1.setMainImage(getImageSprite(getTopArrow()));
			}
			
			drawButton2.setX(x);
			drawButton2.setY(y + height - getButtonSize());
			drawButton2.setWidth(width);
			drawButton2.setHeight(getButtonSize());
			drawButton2.setCursorInfo(cursorInfo);
			drawButton2.setText("");
			drawButton2.setVisible(true);
			if(drawButton2.getImage() == null) {
				drawButton2.setMainImage(getImageSprite(getBottomArrow()));
			}
			
		} else if (Arrangement.HORIZONTAL.equals(getArrangement())) {
			
			drawButton1.setX(x);
			drawButton1.setY(y);
			drawButton1.setWidth(getButtonSize());
			drawButton1.setHeight(height);
			drawButton1.setCursorInfo(cursorInfo);
			drawButton1.setText("");
			drawButton1.setVisible(true);
			if(drawButton1.getImage() == null) {
				drawButton1.setMainImage(getImageSprite(getLeftArrow()));
			}
			
			drawButton2.setX(x + width - 20);
			drawButton2.setY(y);
			drawButton2.setWidth(getButtonSize());
			drawButton2.setHeight(height);
			drawButton2.setCursorInfo(cursorInfo);
			drawButton2.setText("");
			drawButton2.setVisible(true);
			if(drawButton2.getImage() == null) {
				drawButton2.setMainImage(getImageSprite(getRightArrow()));
			}
		}
	}
	
	
	@Override
	public void paint(Graphics g) {
		if (visible) {
			DrawManager.drawPanel(g, this, true);			
			super.paint(g);
			
			drawButton1.setEnable(isEnable());
			drawButton1.paint(g);

			drawButton2.setEnable(isEnable());
			drawButton2.paint(g);
			
			for(InfoDrawListFigure figure: setInfoDrawListFigure) {
				
				BaseDrawFigure baseFigure = figure.getBaseDrawFigure();
				
				if (this.getNumberRows() > figure.getCurrentRow() && this.getNumberColumns() > figure.getCurrentColumn() 
						&& figure.getCurrentRow() > -1 && figure.getCurrentColumn() > -1) {
					baseFigure.setVisible(true);
					GamePanelLayout.setProperComponentSize(baseFigure, figure.getCurrentRow(), figure.getCurrentColumn(), buttonSize);
					baseFigure.paint(g);
					
				} else {
					baseFigure.setVisible(false);
				}
			}
			
		}
		
	}

	public int getButtonSize() {
		return buttonSize;
	}

	public void setButtonSize(int buttonSize) {
		this.buttonSize = buttonSize;
	}
	
	public void addComponent(BaseDrawFigure baseDrawFigure) {
		
		baseDrawFigure.setEventHandler(eventHandler);
		InfoDrawListFigure infoDrawListFigure = new InfoDrawListFigure(baseDrawFigure);
		infoDrawListFigure.setCurrentColumn(baseDrawFigure.getPositionColumn());
		infoDrawListFigure.setCurrentRow(baseDrawFigure.getPositionRow());
		infoDrawListFigure.setOryginalColumn(baseDrawFigure.getPositionColumn());
		infoDrawListFigure.setOryginalRow(baseDrawFigure.getPositionRow());
		
		setInfoDrawListFigure.add(infoDrawListFigure);
	}
	
	public String getTopArrow() {
		return topArrow;
	}
	
	public String getBottomArrow() {
		return bottomArrow;
	}

	public void setBottomArrow(String bottomArrow) {
		this.bottomArrow = bottomArrow;
	}
	

	public void setTopArrow(String topArrow) {
		this.topArrow = topArrow;
	}
	
	public String getLeftArrow() {
		return leftArrow;
	}

	public void setLeftArrow(String leftArrow) {
		this.leftArrow = leftArrow;
	}
	
	public String getRightArrow() {
		return rightArrow;
	}

	public void setRightArrow(String rightArrow) {
		this.rightArrow = rightArrow;
	}
	
	public String getMarkList() {
		return markList;
	}
	
	public void setMarkList(String markList) {
		this.markList = markList;
	}
	
	public void dispose() {
		
		if (!disposed) {
			
			for(InfoDrawListFigure d : setInfoDrawListFigure) {
				d.dispose();
			}
			setInfoDrawListFigure.clear();
			setInfoDrawListFigure = null;
			
			if (previousSelectedFigure != null) {
				previousSelectedFigure.dispose();
				previousSelectedFigure = null;
			}
			
			drawButton1.dispose();
			drawButton1 = null;
			drawButton2.dispose();
			drawButton2 = null;
			
			eventHandler = null;
			
			super.dispose();
		}
		
	}
	
}
