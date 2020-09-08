package com.bwizard.cegame.documents.layout;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.controls.components.BaseDrawFigure;
import com.bwizard.cegame.controls.components.DrawDialog;
import com.bwizard.cegame.controls.components.DrawList;
import com.bwizard.cegame.controls.components.DrawMap;
import com.bwizard.cegame.documents.handlers.Align;
import com.bwizard.cegame.documents.handlers.Arrangement;
import com.bwizard.cegame.documents.handlers.Dock;
import com.bwizard.cegame.documents.handlers.GlobalPosition;
import com.bwizard.cegame.documents.handlers.InsideDirection;
import com.bwizard.cegame.documents.handlers.LayoutAttribute;
import com.bwizard.cegame.documents.handlers.Load;
import com.bwizard.cegame.documents.handlers.PanelLayout;
import com.bwizard.cegame.documents.interfaces.ICmnDocument;
import com.bwizard.cegame.tools.FigureManager;
import com.bwizard.cegame.utils.BooleanUtil;
import com.bwizard.cegame.utils.ImageUtil;
import com.bwizard.cegame.utils.StringUtil;
import com.bwizard.cegame.view.layout.interfaces.IViewLayout;
import com.bwizard.cegame.window.screen.interfaces.IWindowScreen;

public class GamePanelLayout implements ICmnDocument<BaseDrawFigure>, IViewLayout {
	
	private int rightBorderLayout = 0;
	private int leftBorderLayout = 0;
	private int topBorderLayout = 0;
	private int bottomBorderLayout = 0;
	
	private ArrayList<BaseDrawFigure> listFigureIntoPanelLayout = null;
	private BaseWorldGame baseWorldGame = null;
	private String layoutImagePath;
	
	public GamePanelLayout(BaseWorldGame baseWorldGame) {
		this.baseWorldGame = baseWorldGame;
	}
	
	public void setListFigureIntoPanelLayout(ArrayList<BaseDrawFigure> listFigureIntoPanelLayout) {
		this.listFigureIntoPanelLayout = listFigureIntoPanelLayout;
	}
	
	private BaseDrawFigure getMainParent(BaseDrawFigure baseDrawFigure) {

		BaseDrawFigure baseDrawFigureTmp = baseDrawFigure.getParentComponent();
		if(baseDrawFigureTmp != null) {
			String tag = baseDrawFigureTmp.getTag();
			
			PanelLayout panelLayout = PanelLayout.fromString(tag);
			while(!PanelLayout.PANEL_TOP.equals(panelLayout) && !PanelLayout.PANEL_BOTTOM.equals(panelLayout) 
					&& !PanelLayout.PANEL_LEFT.equals(panelLayout) && !PanelLayout.PANEL_RIGHT.equals(panelLayout)) {
				
				baseDrawFigureTmp = baseDrawFigureTmp.getParentComponent();
				tag = baseDrawFigureTmp.getTag();
				panelLayout = PanelLayout.fromString(tag);
			}
				
		}
		
		return baseDrawFigureTmp;
	}
	
	@Override
	public ArrayList<BaseDrawFigure> createElementFromCellAttribute(BaseDrawFigure parentElement, HashMap<String, String> hashCellAttribute) {

		ArrayList<BaseDrawFigure> list = new ArrayList<BaseDrawFigure>();
		
		String figure = hashCellAttribute.get(LayoutAttribute.FIGURE);
		BaseDrawFigure component = GameLayoutTool.getComponentFigure(figure);
	  
		if (component != null) {
			
			String isTemplate = hashCellAttribute.get(LayoutAttribute.TEMPLATE);
			if (BooleanUtil.YES.equals(isTemplate)) {
			
				Load load = Load.NONE;
				String tmp = hashCellAttribute.get(LayoutAttribute.LOAD);
				if (StringUtil.isNotEmpty(tmp)) {
					load = Load.fromString(tmp);
				}
	
				//String extensionFile = hashCellAttribute.get(LayoutAttribute.EXTENSION_FILE);
				
				String pathData = hashCellAttribute.get(LayoutAttribute.PATH_DATA);
				
				FigureManager fileManager = new FigureManager(pathData, component);
				//load component
				List<BaseDrawFigure> listComponent = fileManager.getFilesInOutDirectories();
				
				if (parentElement instanceof DrawList) {
					
					switch (load) {
					case DIR:
						break;
					case FILE:
						break;
					default:
						break;
					}
					
					for(int pos = 0; pos < listComponent.size(); pos++ ) {
						
						BaseDrawFigure baseElement = listComponent.get(pos);
						component = createElement(parentElement, baseElement, hashCellAttribute);
						
						if (Arrangement.HORIZONTAL.equals(parentElement.getArrangement())) {
							component.setPositionColumn(pos);
						} else if (Arrangement.VERTICAL.equals(parentElement.getArrangement())) {
							component.setPositionRow(pos);
						}
						
						addInfoToMainMenuPanel(component);
						
						list.add(component);
					}
				}
				
			} else {
				component = createElement(parentElement, component, hashCellAttribute);
				
				list.add(component);
			}
		}
		
		return list;
	}
	
	protected void addInfoToMainMenuPanel(BaseDrawFigure component) {
	}
	
	private BaseDrawFigure createElement(BaseDrawFigure parentElement, BaseDrawFigure component, HashMap<String, String> hashCellAttribute) {
		
		final IWindowScreen windowScreen = baseWorldGame.getStateInfoGame().getWindowScreen();
				
		String tag = hashCellAttribute.get(LayoutAttribute.TAG);
		if (StringUtil.isNotEmpty(tag)) {
			component.setTag(tag);
		}
		
		//update all necessary properties
		GameLayoutTool.updateFigure(component, hashCellAttribute);
			
		component.setCursorInfo(baseWorldGame.getStateInfoGame().getCursorInfo());
		if (component instanceof DrawMap) {
			((DrawMap)component).setBaseWorldGame(baseWorldGame);
		}
				
		String mainImage = hashCellAttribute.get(LayoutAttribute.MAIN_IMAGE);
		
		if (StringUtil.isNotEmpty(mainImage)) {
			BufferedImage bi = getImageSprite(mainImage);
			component.setImage(bi);
			component.setMainImage(bi);
			component.setFocusedImage(bi);
			component.setSelectedImage(bi);
		}
		
		String focusedImage = hashCellAttribute.get(LayoutAttribute.FOCUSED_IMAGE);
		
		if (StringUtil.isNotEmpty(focusedImage)) {
			BufferedImage bi = getImageSprite(focusedImage);
			component.setFocusedImage(bi);
			component.setSelectedImage(bi);
		}
		
		String selectedImage = hashCellAttribute.get(LayoutAttribute.SELECTED_IMAGE);
		
		if (StringUtil.isNotEmpty(selectedImage)) {
			component.setSelectedImage(getImageSprite(selectedImage));
		}
		
		String enable = hashCellAttribute.get(LayoutAttribute.ENABLE);
		if (StringUtil.isNullOrEmpty(enable)) {
			enable = BooleanUtil.YES;
		}
		component.setEnable(BooleanUtil.getValue(enable));
		
		String insideDirection = hashCellAttribute.get(LayoutAttribute.INSIDE_DIRECTION);
		if (StringUtil.isNotEmpty(insideDirection)) {
			component.setInsideDirection(InsideDirection.fromString(insideDirection));
		}
		
		String globalPositionX = hashCellAttribute.get(LayoutAttribute.GLOBAL_POSITION_X);
		if (StringUtil.isNotEmpty(globalPositionX)) {
			component.setGlobalPositionX(GlobalPosition.fromString(globalPositionX));
		}
		
		String globalPositionY = hashCellAttribute.get(LayoutAttribute.GLOBAL_POSITION_Y);
		if (StringUtil.isNotEmpty(globalPositionY)) {
			component.setGlobalPositionY(GlobalPosition.fromString(globalPositionY));
		}
		
		component.setParentComponent(parentElement);
		
		//only for 'top', 'left', 'right' and 'bottom' panel
		if (component.getParentComponent() == null) {
			
			String visible = hashCellAttribute.get(LayoutAttribute.VISIBLE);
			component.setVisible(BooleanUtil.getValue(visible));
			
			String heightPercent = hashCellAttribute.get(LayoutAttribute.HEIGHT);
			if (StringUtil.isNotEmpty(heightPercent)) {
				int height = reSize(heightPercent, windowScreen.getHeight());
				component.setHeight(height);
			}
			
			String widthPercent = hashCellAttribute.get(LayoutAttribute.WIDTH);
			if (StringUtil.isNotEmpty(widthPercent)) {
				int width = reSize(widthPercent, windowScreen.getWidth());
				component.setWidth(width);
			}
			
			if (component instanceof DrawDialog) {
				component.setX((int)(windowScreen.getWidth() / 2) - (int)(component.getWidth() / 2));
				component.setY((int)(windowScreen.getHeight() / 2)- (int)(component.getHeight() / 2));
			}
			
		} else {
			
			// find parent component
			BaseDrawFigure parent = component.getParentComponent();

			String text = hashCellAttribute.get(LayoutAttribute.TEXT);
			if (StringUtil.isNotEmpty(text)) {
				component.setText(text);
			}
			
			if (StringUtil.isNotEmpty(component.getPlaceHolder())) {
				component.setText(component.getText() + component.getPlaceHolder());
			}
			
			String align = hashCellAttribute.get(LayoutAttribute.ALIGN);
			if (StringUtil.isNotEmpty(align)) {
				Align alignComponent = Align.fromString(align);
				component.setAlign(alignComponent);
			}
			
			
			String margin = hashCellAttribute.get(LayoutAttribute.MARGIN);
			if (StringUtil.isNotEmpty(margin)) {
				int tmp1 = reSize(margin, parent.getWidth());
				component.setMarginLeft(tmp1);
				
				int tmp2 = reSize(margin, parent.getWidth());
				component.setMarginRight(tmp2);
				
				int tmp3 = reSize(margin, parent.getHeight());
				component.setMarginTop(tmp3);
				
				int tmp4 = reSize(margin, parent.getHeight() + tmp3);
				component.setMarginBottom(tmp4);
				
				//int tmp = reSize(margin);
				//component.setMargin(tmp);
			}
			
			String marginLeft = hashCellAttribute.get(LayoutAttribute.MARGIN_LEFT);
			if (StringUtil.isNotEmpty(marginLeft)) {
				int tmp = reSize(marginLeft, parent.getWidth());
				component.setMarginLeft(tmp);
			}
			
			String marginRight = hashCellAttribute.get(LayoutAttribute.MARGIN_RIGHT);
			if (StringUtil.isNotEmpty(marginRight)) {
				int tmp = reSize(marginRight, parent.getWidth());
				component.setMarginRight(tmp);
			}
			
			String marginTop = hashCellAttribute.get(LayoutAttribute.MARGIN_TOP);
			if (StringUtil.isNotEmpty(marginTop)) {
				int tmp = reSize(marginTop, parent.getHeight());
				component.setMarginTop(tmp);
			}
			
			String marginBottom = hashCellAttribute.get(LayoutAttribute.MARGIN_BOTTOM);
			if (StringUtil.isNotEmpty(marginBottom)) {
				int tmp = reSize(marginBottom, parent.getHeight());
				component.setMarginBottom(tmp);
			}
							
			//first level
			PanelLayout panelLayout = PanelLayout.fromString(parent.getTag());
			
			if (PanelLayout.PANEL_TOP.equals(panelLayout) || PanelLayout.PANEL_BOTTOM.equals(panelLayout) 
					|| PanelLayout.PANEL_LEFT.equals(panelLayout) || PanelLayout.PANEL_RIGHT.equals(panelLayout)) {
				
				String baseGroup = parent.getTag();
				
				int parentPositionX = 0, parentPositionY = 0;
				boolean isGroupExists = false;
				
				String visible = hashCellAttribute.get(LayoutAttribute.VISIBLE);
				component.setVisible(BooleanUtil.getValue(visible));
				
				HashMap<String,BaseDrawFigure> tempMapGroup = new HashMap<String,BaseDrawFigure>();
				
				String group = hashCellAttribute.get(LayoutAttribute.GROUP);
				component.setGroup(group);
				
				if (StringUtil.isNotEmpty(group)) {
					
					for(BaseDrawFigure baseDrawFigure : listFigureIntoPanelLayout) {
						
						//size can be summarize only for the same parent
						BaseDrawFigure mainParent = getMainParent(baseDrawFigure);
						if (mainParent != null && mainParent.getTag().equals(baseGroup)) {
							
							if (!tempMapGroup.containsKey(baseDrawFigure.getGroup())) {
								tempMapGroup.put(baseDrawFigure.getGroup(), baseDrawFigure);
							}
							
							if (group.equals(baseDrawFigure.getGroup())) {
								parentPositionX = baseDrawFigure.getX();
								parentPositionY = baseDrawFigure.getY();
								isGroupExists = true;
								break;
							}
						}
					}
				}
				
				//if group doesn't exists yet we set first element 
				if (!isGroupExists) {
					
					parentPositionX = parent.getX();
					parentPositionY = parent.getY();
					
					BaseDrawFigure mainParent = getMainParent(component);
					if (mainParent != null) {	
						switch (mainParent.getInsideDirection()) {
							case RIGHT:
								for(BaseDrawFigure baseDrawFigure : tempMapGroup.values()) {
									if(StringUtil.isNotEmpty(baseDrawFigure.getGroup())) {
										parentPositionX += baseDrawFigure.getWidth();
									}
								}
								break;
							case DOWN:
								for(BaseDrawFigure baseDrawFigure : tempMapGroup.values()) {
									if(StringUtil.isNotEmpty(baseDrawFigure.getGroup())) {
										parentPositionY += baseDrawFigure.getHeight();
									}
								}
								break;
							default:
								break;
						}
					}
				}
				
				component.setY(parentPositionY);
				component.setX(parentPositionX);
				
				int height = 0;
				String heightPercent = hashCellAttribute.get(LayoutAttribute.HEIGHT);
				if (StringUtil.isNotEmpty(heightPercent)) {
					height = reSize(heightPercent, parent.getHeight());
					component.setHeight(height);
				}
				
				int width = 0;
				String widthPercent = hashCellAttribute.get(LayoutAttribute.WIDTH);
				if (StringUtil.isNotEmpty(widthPercent)) {
					width = reSize(widthPercent, parent.getWidth());
					component.setWidth(width);
				}
				
				//if height > 100%
				if (height > parent.getHeight() && PanelLayout.PANEL_BOTTOM.equals(panelLayout)) {
					component.setY(parentPositionY - (height - parent.getHeight()));
				} else {
					component.setY(parentPositionY);
				}
				
				
				//if width > 100%
				if (width > parent.getWidth() && PanelLayout.PANEL_RIGHT.equals(panelLayout)) {
					component.setX(parentPositionX - (width - parent.getWidth()));
				} else {
					component.setX(parentPositionX);
				}

			} else {
				
				//create list of child for parent
				parentElement.addChildComponent(component);
				
				String positionColumn = hashCellAttribute.get(LayoutAttribute.POSITION_COLUMN);
				if (StringUtil.isNotEmpty(positionColumn)) {
					int tmp = Integer.valueOf(positionColumn);
					component.setPositionColumn(tmp);
				}
				
				String positionRow = hashCellAttribute.get(LayoutAttribute.POSITION_ROW);
				if (StringUtil.isNotEmpty(positionRow)) {
					int tmp = Integer.valueOf(positionRow);
					component.setPositionRow(tmp);
				}
				
				String extensionCellColumn = hashCellAttribute.get(LayoutAttribute.EXTENSION_CELL_COLUMN);
				if (StringUtil.isNotEmpty(extensionCellColumn)) {
					int tmp = Integer.valueOf(extensionCellColumn);
					component.setExtensionCellColumn(tmp);
				}
				
				String extensionCellRow = hashCellAttribute.get(LayoutAttribute.EXTENSION_CELL_ROW);
				if (StringUtil.isNotEmpty(extensionCellRow)) {
					int tmp = Integer.valueOf(extensionCellRow);
					component.setExtensionCellRow(tmp);
				}
				
				//only for DrawListComponent
				if (parent instanceof DrawList) {
					((DrawList)parent).addComponent(component);
				} else {
					setProperComponentSize(component);	
				}
				
				String arrangement = hashCellAttribute.get(LayoutAttribute.ARRANGEMENT);
				if (StringUtil.isNotEmpty(arrangement)) {
					Arrangement arrangementComponent = Arrangement.fromString(arrangement);
					component.setArrangement(arrangementComponent);
				}
						
				component.setVisible(parent.isVisible());
			}
		}
		
		PanelLayout panelLayout = PanelLayout.fromString(tag);
		
		if (PanelLayout.PANEL_TOP.equals(panelLayout) || PanelLayout.PANEL_BOTTOM.equals(panelLayout) 
				|| PanelLayout.PANEL_LEFT.equals(panelLayout) || PanelLayout.PANEL_RIGHT.equals(panelLayout)) {
			
			String visible = hashCellAttribute.get(LayoutAttribute.VISIBLE);
			component.setVisible(BooleanUtil.getValue(visible));
							
			String dock = hashCellAttribute.get(LayoutAttribute.DOCK);
			if (StringUtil.isNotEmpty(dock)) {
				Dock dockComponent = Dock.fromString(dock);
				switch (dockComponent) {
					case TOP:
						topBorderLayout = component.getHeight();
						
						component.setWidth(windowScreen.getWidth());
						component.setX(0);
						component.setY(0);
						break;
					case BOTTOM:
						bottomBorderLayout = component.getHeight();
						
						component.setWidth(windowScreen.getWidth());
						component.setX(0);
						component.setY(windowScreen.getHeight() - bottomBorderLayout);
						break;
					case RIGHT:
						rightBorderLayout = component.getWidth();
						
						component.setHeight(windowScreen.getHeight());
						component.setX(windowScreen.getWidth() - rightBorderLayout);
						component.setY(0);
						break;
					case LEFT:
						leftBorderLayout = component.getWidth();
						
						component.setHeight(windowScreen.getHeight());
						component.setX(0);
						component.setY(0);
						break;
					case FILL:
						break;
					default:
						break;
				}	
			}
		}		
		
		return component;
	}
	
	  
	public static void setProperComponentSize(BaseDrawFigure component) {
		
		BaseDrawFigure parent = component.getParentComponent();
		
		final int parentNumberColumns = parent.getNumberColumns() == 0 ? 1 : parent.getNumberColumns();
		final int parentNumberRows = parent.getNumberRows() == 0 ? 1 : parent.getNumberRows();
		
		final int parentCellWidth = parent.getWidth() / parentNumberColumns;
		final int parentCellHeight = parent.getHeight() / parentNumberRows;
		
		//450(position of screen) + 2(position of column) * 20(width of cell) 
		component.setX(component.getMarginLeft() + parent.getX() + (component.getPositionColumn() * parentCellWidth));
		component.setY(component.getMarginTop() + parent.getY() + (component.getPositionRow() * parentCellHeight));
		
		component.setWidth(parentCellWidth * component.getExtensionCellColumn() - component.getMarginLeft() - component.getMarginRight());
		component.setHeight(parentCellHeight * component.getExtensionCellRow() - component.getMarginBottom() - component.getMarginTop());
		
	}
	
	public static void setProperComponentSize(BaseDrawFigure component, int positionRow, int positionColumn, int margin) {
		
		BaseDrawFigure parent = component.getParentComponent();
		
		final int parentNumberColumns = parent.getNumberColumns();
		final int parentNumberRows = parent.getNumberRows();
		
		int pos1 = 0, pos2 = 0;
		if (Arrangement.HORIZONTAL.equals(parent.getArrangement())) {
			pos1 = margin;
		} else if (Arrangement.VERTICAL.equals(parent.getArrangement())) {
			pos2 = margin;
		}
		
		final int parentCellWidth = (parent.getWidth() - pos1 - pos1) / parentNumberColumns;
		final int parentCellHeight = (parent.getHeight() - pos2 - pos2) / parentNumberRows;
		
		//450(position of screen) + 2(position of column) * 20(width of cell) 
		component.setX(component.getMarginLeft() + pos1 + parent.getX() + (positionColumn * parentCellWidth));
		component.setY(component.getMarginTop() + pos2 + parent.getY() + (positionRow * parentCellHeight));
		
		component.setWidth(parentCellWidth * component.getExtensionCellColumn() - component.getMarginLeft() - component.getMarginRight());
		component.setHeight(parentCellHeight * component.getExtensionCellRow() - component.getMarginBottom() - component.getMarginTop());
		
	}
		
	private int reSize(String str, int size) {
		
		int tmp = 0;
		  
		  if (size > 0 && str.endsWith("%")) {
			  
			  str = str.substring(0, str.length() - 1);
			  tmp = Integer.valueOf(str);
			  tmp = size * tmp / 100;
			  
		  } else if (str.endsWith("px")) {
			  
			  str = str.substring(0, str.length() - 2);
			  tmp = Integer.valueOf(str);
			  
		  } else {
			  
			  tmp = Integer.valueOf(str);
		  }
		  
		  return tmp;
	}
	
	@Override
	public int getViewHeight() {
		return baseWorldGame.getStateInfoGame().getWindowScreen().getHeight() - bottomBorderLayout - topBorderLayout;
	}
		
	@Override
	public int getViewWidth() {
		return baseWorldGame.getStateInfoGame().getWindowScreen().getWidth() - rightBorderLayout - leftBorderLayout;
	}
		  	  
	protected BufferedImage getImageSprite(String file) {
		return ImageUtil.loadImage(getLayoutImagePath() + file);
	}
	
	public String getLayoutImagePath() {
		return layoutImagePath;
	}
	
	public void setLayoutImagePath(String layoutImagePath) {
		this.layoutImagePath = layoutImagePath;
	}

	@Override
	public int getLeftBorderLayout() {
		return leftBorderLayout;
	}

	@Override
	public int getTopBorderLayout() {
		return topBorderLayout;
	}

	@Override
	public int getRightBorderLayout() {
		return rightBorderLayout;
	}

	@Override
	public int getBottomBorderLayout() {
		return bottomBorderLayout;
	}


	
}
