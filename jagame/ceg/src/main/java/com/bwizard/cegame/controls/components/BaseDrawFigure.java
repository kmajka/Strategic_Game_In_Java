package com.bwizard.cegame.controls.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import com.bwizard.cegame.controls.components.interfaces.IEventHandler;
import com.bwizard.cegame.controls.text.TextProvider;
import com.bwizard.cegame.device.model.CursorInfo;
import com.bwizard.cegame.documents.handlers.Align;
import com.bwizard.cegame.documents.handlers.Arrangement;
import com.bwizard.cegame.documents.handlers.Dock;
import com.bwizard.cegame.documents.handlers.FigureType;
import com.bwizard.cegame.documents.handlers.GlobalPosition;
import com.bwizard.cegame.documents.handlers.InsideDirection;
import com.bwizard.cegame.documents.handlers.Load;
import com.bwizard.cegame.tools.DrawManager;
import com.bwizard.cegame.utils.PointInfo;

public abstract class BaseDrawFigure implements Cloneable {
	
	protected IEventHandler eventHandler = null;
	protected CursorInfo cursorInfo;
	public TextProvider textProvider = null;
	
	protected int width;
	protected int height;
	protected int x;	
	protected int y;
	
	//member used to for displaying image
	private BufferedImage image = null;
	//additional image uses e.g. in custom menu
	private BufferedImage image2 = null;
	//main image for component 
	private BufferedImage mainImage = null;
	//image show under cursor 
	private BufferedImage focusedImage = null;
	//image show after selected component 
	private BufferedImage selectedImage = null;
	
	protected String tag;
	
	//figure was painted
	private boolean painted = false;
	
	//the figure can be visible in the game
	protected boolean visible;
	
	//lock can be used as mark properties to skip during updating
	private boolean lock;
	
	private String group;
	private InsideDirection insideDirection;
	private GlobalPosition globalPositionX;
	private GlobalPosition globalPositionY;
	private boolean temporaryDisplaying;
	private Arrangement arrangement;
	private Dock dock;
	
	//the figure can be disabled/enabled (e.g. can be visible but not enable in the game)
	private boolean enable;

	//when the cursor IS OVER the figure (IT COULD BE FOCUSED)
	protected boolean focusing;
	
	//when the cursor HAS BEEN FOCUSED
	//private boolean focused;
	
	//the user CAN click on the figure
	protected boolean canPressed;
	
	//cursor down
	private boolean selecting;
	
	//user CLICKED on the figure - cooperate with 'autoUnselect' and invoke event  //e.g. checkbox[true] , button[true], label[false], map[false]
	//cursor up
	protected boolean selected;
	
	//counter of pressed figure 
	private int countPressed = 0;

	//the figure will be automatically UNSELECTED after selecting  //e.g. checkbox[false] , button[true], label[false]
	protected boolean autoUnselect;
		
	private int positionColumn;
	private int positionRow;
	
	private int marginLeft;
	private int marginRight;
	private int marginTop;
	private int marginBottom;
	private BaseDrawFigure parentComponent;
	private Set<BaseDrawFigure> childComponents;
	private int extensionCellRow;
	private int extensionCellColumn;
	
	private Color borderColor;
	private boolean border; 
	private int numberColumns;
	private int numberRows;
	
	private Load load;
	private String extensionFile;
	private String pathData;
	private String fileName;
	//it can be used for store some value e.g. after clicking button text will be displayed in bottom panel (main menu) 
	private String descriptionField1;
	private boolean template;
	
	private boolean transparent;
	
	private Color backgroundColor;
	private Color mainBackgroundColor;
	private Color focusedBackgroundColor;
	private Color selectedBackgroundColor;
		
	private String tagDefaultFigure;
	private boolean oneChoice;
	
	private FigureType figureType;
	protected boolean disposed = false;
	private boolean defaultFocused;
	
	
	public BaseDrawFigure() {
		
		textProvider = new TextProvider();
		childComponents = new HashSet<BaseDrawFigure>();
				
		setLock(false);
		enable = true;
		canPressed = false;
		autoUnselect = false;
		globalPositionX = GlobalPosition.CENTER;
		globalPositionY = GlobalPosition.CENTER;
		temporaryDisplaying = false;
		transparent = false;
		oneChoice = false;
		mainBackgroundColor = Color.BLACK;
		focusedBackgroundColor = Color.BLACK;
		selectedBackgroundColor = Color.BLACK;
		backgroundColor = Color.BLACK;
		extensionCellRow = 1;
		extensionCellColumn = 1;
		insideDirection = InsideDirection.NONE;
		border = false;
	}
	
	public Object clone() {
		
		BaseDrawFigure cloned = null;
		try {
			cloned = (BaseDrawFigure)super.clone();
			cloned.textProvider = (TextProvider)textProvider.clone();	
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cloned;
	}	
	
	public boolean isCursorOverFigure() {
		if (enable && visible && cursorInfo != null && cursorInfo.getScreenCursorPositionX() > x && cursorInfo.getScreenCursorPositionX() < (x + width) &&
				cursorInfo.getScreenCursorPositionY() > y && cursorInfo.getScreenCursorPositionY() < (y + height)) {
			return true;
		} 
		return false;
	}	
	
	public boolean isCursorOverFigure(PointInfo pointInfo) {
		if (enable && visible && pointInfo.getX() > x && pointInfo.getX() < (x + width) &&
				pointInfo.getY() > y && pointInfo.getY() < (y + height)) {
			return true;
		} 
		return false;
	}
	
	public boolean canPressedFigure() {
		if (enable && visible && canPressed && cursorInfo.getScreenCursorPositionX() > x && cursorInfo.getScreenCursorPositionX() < (x + width) &&
				cursorInfo.getScreenCursorPositionY() > y && cursorInfo.getScreenCursorPositionY() < (y + height)) {
			return true;
		} 
		return false;
	}
	
	public void unPressedFigure() {
		if (cursorInfo.getScreenCursorPositionX() > x && cursorInfo.getScreenCursorPositionX() < x + width &&
				cursorInfo.getScreenCursorPositionY() > y && cursorInfo.getScreenCursorPositionY() < y + height) {
			selected = false;
		} 
	}
	
	protected void invokeEvent() {
		
		if (eventHandler != null && this.selected) {
			eventHandler.invokeEvent(this, tag);
		}
	}
	
	protected void invokeEvent(String currentTag) throws Exception {
		
		if (eventHandler != null && this.selected) {
			eventHandler.invokeEvent(this, currentTag);
		}
	}
	
	public void setPressed(boolean selected) {
		
		this.selected = selected;
		
		invokeEvent();
		
		if (autoUnselect) {
			this.selected = false;
		}
	}
	
	public void setPressed(boolean selected, String currentTag) throws Exception {
		
		this.selected = selected;
		
		invokeEvent(currentTag);
		
		if (autoUnselect) {
			this.selected = false;
		}
	}
	
	public void setText(String text) {
		textProvider.setText(text);
	}
	
	public String getText() {
		return textProvider.getText();
	}
	
	public void setCursorInfo(CursorInfo cursorInfo) {
		this.cursorInfo = cursorInfo;
	}
	
	public void setEventHandler(IEventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}
	
	public void paint(Graphics g) {
		if(isBorder()) {
			Color tmp = g.getColor();
			DrawManager.drawRect(g, x, y, width, height, borderColor);
			g.setColor(tmp);
		}
		
	}
	
	public boolean hasEvent() {
		return true;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
		for(BaseDrawFigure baseDrawFigure : this.childComponents) {
			baseDrawFigure.setVisible(visible);
		}
	}
	
	public void setSelected(boolean select) {
		this.selected = select;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public IEventHandler getEventHandler() {
		return eventHandler;
	}
	
	public void setWidth(int width) {
		textProvider.setWidth(width);
		this.width = width;
	}
	public void setHeight(int height) {
		textProvider.setHeight(height);
		this.height = height;
	}
	public void setX(int x) {
		textProvider.setX(x);
		this.x = x;
	}
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		textProvider.setY(y);
		this.y = y;
	}
	public int getY() {
		return y;
	}
	
	public void setPositionColumn(int val) {
		positionColumn = val;
	}

	public int getPositionColumn() {
		return positionColumn;
	}

	public Dock getDock() {
		return dock;
	}

	public void setDock(Dock dock) {
		this.dock = dock;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public int getPositionRow() {
		return positionRow;
	}

	public void setPositionRow(int positionRow) {
		this.positionRow = positionRow;
	}

	public int getExtensionCellRow() {
		return extensionCellRow;
	}

	public void setExtensionCellRow(int extensionCellRow) {
		this.extensionCellRow = extensionCellRow;
	}

	public int getExtensionCellColumn() {
		return extensionCellColumn;
	}

	public void setExtensionCellColumn(int extensionCellColumn) {
		this.extensionCellColumn = extensionCellColumn;
	}

	public boolean isBorder() {
		return border;
	}

	public void setBorder(boolean border) {
		this.border = border;
	}

	public int getNumberColumns() {
		return numberColumns;
	}

	public void setNumberColumns(int numberColumns) {
		this.numberColumns = numberColumns;
	}

	public int getNumberRows() {
		return numberRows;
	}

	public void setNumberRows(int numberRows) {
		this.numberRows = numberRows;
	}

	public void setMargin(int margin) {
		this.marginLeft = margin;
		this.marginRight = margin;
		this.marginTop = margin;
		this.marginBottom = margin;
	}
	
	public int getMarginLeft() {
		return marginLeft;
	}

	public void setMarginLeft(int marginLeft) {
		this.marginLeft = marginLeft;
	}

	public int getMarginRight() {
		return marginRight;
	}

	public void setMarginRight(int marginRight) {
		this.marginRight = marginRight;
	}

	public int getMarginTop() {
		return marginTop;
	}

	public void setMarginTop(int marginTop) {
		this.marginTop = marginTop;
	}

	public int getMarginBottom() {
		return marginBottom;
	}

	public void setMarginBottom(int marginBottom) {
		this.marginBottom = marginBottom;
	}

	public BaseDrawFigure getParentComponent() {
		return parentComponent;
	}

	public void setParentComponent(BaseDrawFigure parentComponent) {
		this.parentComponent = parentComponent;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public InsideDirection getInsideDirection() {
		return insideDirection;
	}

	public void setInsideDirection(InsideDirection insideDirection) {
		this.insideDirection = insideDirection;
	}

	public Set<BaseDrawFigure> getChildComponents() {
		return childComponents;
	}

	public void addChildComponent(BaseDrawFigure childComponent) {
		this.childComponents.add(childComponent);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		if (this.image != image) {
			this.image = image;
		}
	}

	public BufferedImage getImage2() {
		return image2;
	}

	public void setImage2(BufferedImage image2) {
		if (this.image2 != image2) {
			this.image2 = image2;
		}
	}
	
	public Arrangement getArrangement() {
		return arrangement;
	}

	public void setArrangement(Arrangement arrangement) {
		this.arrangement = arrangement;
	}

	public Color getMainBackgroundColor() {
		return mainBackgroundColor;
	}

	public void setMainBackgroundColor(Color mainBackgroundColor) {
		this.mainBackgroundColor = mainBackgroundColor;
	}

	public Color getSelectedBackgroundColor() {
		return selectedBackgroundColor;
	}

	public void setSelectedBackgroundColor(Color selectedBackgroundColor) {
		this.selectedBackgroundColor = selectedBackgroundColor;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public boolean isPainted() {
		return painted;
	}

	public void setPainted(boolean painted) {
		this.painted = painted;
	}

	public GlobalPosition getGlobalPositionX() {
		return globalPositionX;
	}

	public void setGlobalPositionX(GlobalPosition globalPositionX) {
		this.globalPositionX = globalPositionX;
	}

	public GlobalPosition getGlobalPositionY() {
		return globalPositionY;
	}

	public void setGlobalPositionY(GlobalPosition globalPositionY) {
		this.globalPositionY = globalPositionY;
	}

	public boolean isTemporaryDisplaying() {
		return temporaryDisplaying;
	}

	public void setTemporaryDisplaying(boolean temporaryDisplaying) {
		this.temporaryDisplaying = temporaryDisplaying;
	}

	public boolean isTransparent() {
		return transparent;
	}

	public void setTransparent(boolean transparent) {
		this.transparent = transparent;
	}
	
	public void setCanPressed(boolean canPressed) {
		this.canPressed = canPressed;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
		for(BaseDrawFigure baseDrawFigure : this.childComponents) {
			baseDrawFigure.setEnable(enable);
		}
	}

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public Load getLoad() {
		return load;
	}

	public void setLoad(Load load) {
		this.load = load;
	}

	public String getExtensionFile() {
		return extensionFile;
	}

	public void setExtensionFile(String extensionFile) {
		this.extensionFile = extensionFile;
	}

	public boolean isTemplate() {
		return template;
	}

	public void setTemplate(boolean template) {
		this.template = template;
	}

	public String getPathData() {
		return pathData;
	}

	public void setPathData(String pathData) {
		this.pathData = pathData;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDescriptionField1() {
		return descriptionField1;
	}

	public void setDescriptionField1(String descriptionField1) {
		this.descriptionField1 = descriptionField1;
	}

	public int getFontStyle() {
		return textProvider.getFontStyle();
	}

	public void setFontStyle(int fontStyle) {
		textProvider.setFontStyle(fontStyle);
		textProvider.setBaseFontStyle(fontStyle);
	}
	
	public int getFocusedFontStyle() {
		return textProvider.getFocusedFontStyle();
	}

	public void setFocusedFontStyle(int focusedFontStyle) {
		textProvider.setFocusedFontStyle(focusedFontStyle);
	}

	public String getFontName() {
		return textProvider.getFontName();
	}

	public void setFontName(String fontName) {
		textProvider.setFontName(fontName);
	}

	public int getFontSize() {
		return textProvider.getFontSize();
	}

	public void setFontSize(int fontSize) {
		textProvider.setFontSize(fontSize);
	}	
	
	public int getMaxLength() {
		return textProvider.getMaxLength();
	}

	public void setMaxLength(int maxLength) {
		textProvider.setMaxLength(maxLength);
	}
	
	public String getPlaceHolder() {
		return textProvider.getPlaceHolder();
	}

	public void setPlaceHolder(String placeHolder) {
		textProvider.setPlaceHolder(placeHolder);
	}
	
	public String getTagDefaultFigure() {
		return tagDefaultFigure;
	}

	public void setTagDefaultFigure(String tagDefaultFigure) {
		this.tagDefaultFigure = tagDefaultFigure;
	}

	public Color getFocusedBackgroundColor() {
		return focusedBackgroundColor;
	}

	public void setFocusedBackgroundColor(Color focusedBackgroundColor) {
		this.focusedBackgroundColor = focusedBackgroundColor;
	}

	public Color getMainFontColor() {
		return textProvider.getMainFontColor();
	}

	public void setMainFontColor(Color mainFontColor) {
		textProvider.setMainFontColor(mainFontColor);
	}

	public Color getSelectedFontColor() {
		return textProvider.getSelectedFontColor();
	}

	public void setSelectedFontColor(Color selectedFontColor) {
		textProvider.setSelectedFontColor(selectedFontColor);
	}

	public Color getFocusedFontColor() {
		return textProvider.getFocusedFontColor();
	}

	public void setFocusedFontColor(Color focusedFontColor) {
		textProvider.setFocusedFontColor(focusedFontColor);
	}

	public Color getBackgroundColor() {
		return getBackgroundColor(false);
	}
	
	public Color getBackgroundColor(boolean actualState) {
		if (actualState) {
			updateBackgroundColor();
		}
		return backgroundColor;
	}

	private void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	public void updateBackgroundColor() {
		
		if (enable && cursorInfo.getScreenCursorPositionX() > x && cursorInfo.getScreenCursorPositionX() < x + width &&
				cursorInfo.getScreenCursorPositionY() > y && cursorInfo.getScreenCursorPositionY() < y + height) {
			if(selecting) {
				setBackgroundColor(selectedBackgroundColor);
			} else {
				setBackgroundColor(focusedBackgroundColor);
			}
		} else {
			setBackgroundColor(mainBackgroundColor);
		}
	}

	public boolean isSelecting() {
		return selecting;
	}

	public void setSelecting(boolean selecting) {
		this.selecting = selecting;
	}

	public int getCountPressed() {
		return countPressed;
	}

	public void setCountPressed(int countPressed) {
		this.countPressed = countPressed;
	}

	public boolean isOneChoice() {
		return oneChoice;
	}

	public void setOneChoice(boolean oneChoice) {
		this.oneChoice = oneChoice;
	}

	public BufferedImage getFocusedImage() {
		return focusedImage;
	}

	public void setFocusedImage(BufferedImage focusedImage) {
		this.focusedImage = focusedImage;
	}

	public Align getAlign() {
		return textProvider.getAlign();
	}

	public void setAlign(Align align) {
		textProvider.setAlign(align);
	}
	
	public void updateDefaultConfigProperties(BaseDrawFigure configFigure) {
		
		if (configFigure.isTransparent()) {
			setTransparent(configFigure.isTransparent());
		}
		
		updateBackgroundColor(configFigure);
		
		if (configFigure.isBorder()) {
			setBorder(configFigure.isBorder());
		}
		
		if (getAlign() == null) {
			setAlign(configFigure.getAlign());
		}
		
		textProvider.updateFont(configFigure);
		textProvider.updateFontColor(configFigure);

	}
	
	public void updateDefaultProperties() {
		//settextProvider.prepareFont();
	}
	
	private void updateBackgroundColor(BaseDrawFigure baseFigure) {
		
		if(baseFigure.getMainBackgroundColor() != null) {
			setMainBackgroundColor(baseFigure.getMainBackgroundColor());
		}
		
		if(baseFigure.getFocusedBackgroundColor() != null) {
			setFocusedBackgroundColor(baseFigure.getFocusedBackgroundColor());
		}
		
		if(baseFigure.getSelectedBackgroundColor() != null) {
			setSelectedBackgroundColor(baseFigure.getSelectedBackgroundColor());
		}

	}

	public FigureType getFigureType() {
		return figureType;
	}

	public void setFigureType(FigureType figureType) {
		this.figureType = figureType;
	}

	public BufferedImage getSelectedImage() {
		return selectedImage;
	}

	public void setSelectedImage(BufferedImage selectedImage) {
		this.selectedImage = selectedImage;
	}

	public BufferedImage getMainImage() {
		return mainImage;
	}

	public void setMainImage(BufferedImage mainImage) {
		this.mainImage = mainImage;
	}
	
	public void fakedispose() {
		
	}
	
	public void dispose() {
		if (!disposed) {
			if(image != null) {
				image.flush();
				image = null;
			}
			
			if(image2 != null) {
				image2.flush();
				image2 = null;
			}
			
			if(mainImage != null) {
				mainImage.flush();
				mainImage = null;
			}
			
			if(focusedImage != null) {
				focusedImage.flush();
				focusedImage = null;
			}
			
			if(selectedImage != null) {
				selectedImage.flush();
				selectedImage = null;
			}

			if (parentComponent != null) {
				parentComponent = null;
			}
			
			for(BaseDrawFigure item : childComponents) {
				if (item instanceof DrawList) {
					((DrawList)item).dispose();
				} else if (item instanceof BaseDrawFigure) {
					((BaseDrawFigure)item).dispose();
				}
			}
			
			childComponents.clear();
			childComponents = null;
						
			eventHandler = null;
			cursorInfo = null;
			textProvider = null;
			arrangement = null;
			globalPositionX = null;
			globalPositionY = null;
			dock = null;
			borderColor = null;
			backgroundColor = null;
			mainBackgroundColor = null;
			focusedBackgroundColor = null;
			selectedBackgroundColor = null;
			figureType = null;
			
			disposed = true;
		}
	}

	public boolean isDisposed() {
		return disposed;
	}

	public void setDisposed(boolean disposed) {
		this.disposed = disposed;
	}

	public boolean isDefaultFocused() {
		return defaultFocused;
	}

	public void setDefaultFocused(boolean defaultFocused) {
		this.defaultFocused = defaultFocused;
	}
	
	public void clearSelected() throws Exception
	{
	}

//	public boolean isFocused() {
//		return focused;
//	}
//
//	public void setFocused(boolean focused) {
//		this.focused = focused;
//	}

}
