package com.bwizard.cegame.documents.layout;

import java.util.HashMap;

import com.bwizard.cegame.controls.components.BaseDrawFigure;
import com.bwizard.cegame.controls.components.DrawButton;
import com.bwizard.cegame.controls.components.DrawCheckbox;
import com.bwizard.cegame.controls.components.DrawDialog;
import com.bwizard.cegame.controls.components.DrawLabel;
import com.bwizard.cegame.controls.components.DrawList;
import com.bwizard.cegame.controls.components.DrawMap;
import com.bwizard.cegame.controls.components.DrawPanel;
import com.bwizard.cegame.controls.components.DrawTextEdit;
import com.bwizard.cegame.controls.components.FramePanel;
import com.bwizard.cegame.documents.handlers.Align;
import com.bwizard.cegame.documents.handlers.ComponentName;
import com.bwizard.cegame.documents.handlers.FigureType;
import com.bwizard.cegame.documents.handlers.FontStyle;
import com.bwizard.cegame.documents.handlers.LayoutAttribute;
import com.bwizard.cegame.parameters.CmnGlobals;
import com.bwizard.cegame.utils.BooleanUtil;
import com.bwizard.cegame.utils.ColorUtil;
import com.bwizard.cegame.utils.StringUtil;

public class DocumentLayoutTool {

	public static BaseDrawFigure getComponentFigure(String name) {
		
		BaseDrawFigure component = null;
		
		ComponentName componentName = ComponentName.fromString(name);
		switch (componentName) {
		case BUTTON:
			component = new DrawButton();
			break;
		case CHECKBOX:
			component = new DrawCheckbox();
			break;
		case LABEL:
			component = new DrawLabel();
			break;
		case TEXTEDIT:
			component = new DrawTextEdit();
			break;
		case LIST:
			component = new DrawList();
			break;
		case PANEL:
			component = new DrawPanel();
			break;
		case DIALOG:
			component = new DrawDialog();
			break;
		case MAP:
			component = new DrawMap();
			break;
		case FRAMEPANEL:
			component = new FramePanel();
			break;
		default:
			break;
		}
		
		return component;
	}
	
	
	public static void updateFigure(BaseDrawFigure sourceFigure, BaseDrawFigure copiedFigure) {
		sourceFigure.updateDefaultConfigProperties(copiedFigure);
	}
			
	public static void updateFigure(BaseDrawFigure component, HashMap<String, String> hashCellAttribute) {
			
		if (component instanceof DrawList) {
		
			String topArrow = hashCellAttribute.get(LayoutAttribute.TOP_ARROW);
			if (StringUtil.isNotEmpty(topArrow)) {
				((DrawList)component).setTopArrow(topArrow);
			}
			
			String bottomArrow = hashCellAttribute.get(LayoutAttribute.BOTTOM_ARROW);
			if (StringUtil.isNotEmpty(bottomArrow)) {
				((DrawList)component).setBottomArrow(bottomArrow);
			}
			
			String leftArrow = hashCellAttribute.get(LayoutAttribute.LEFT_ARROW);
			if (StringUtil.isNotEmpty(leftArrow)) {
				((DrawList)component).setLeftArrow(leftArrow);
			}
			
			String rightArrow = hashCellAttribute.get(LayoutAttribute.RIGHT_ARROW);
			if (StringUtil.isNotEmpty(rightArrow)) {
				((DrawList)component).setRightArrow(rightArrow);
			}

			String markList = hashCellAttribute.get(LayoutAttribute.MARK_LIST);
			if (StringUtil.isNotEmpty(markList)) {
				((DrawList)component).setMarkList(markList);
			}
		}
		
		if (component instanceof DrawCheckbox) {
			
			String markCheckbox = hashCellAttribute.get(LayoutAttribute.MARK_CHECKBOX);
			if (StringUtil.isNotEmpty(markCheckbox)) {
				((DrawCheckbox)component).setMarkCheckbox(markCheckbox);
			}
			
			String unMarkCheckbox = hashCellAttribute.get(LayoutAttribute.UNMARK_CHECKBOX);
			if (StringUtil.isNotEmpty(unMarkCheckbox)) {
				((DrawCheckbox)component).setUnMarkCheckbox(unMarkCheckbox);
			}

		}
		
		
		String mainBackgroundColor = hashCellAttribute.get(LayoutAttribute.MAIN_BACKGROUND_COLOR);
		if (StringUtil.isNotEmpty(mainBackgroundColor)) {
			component.setMainBackgroundColor(ColorUtil.getValue(mainBackgroundColor));
			component.setFocusedBackgroundColor(ColorUtil.getValue(mainBackgroundColor));
			component.setSelectedBackgroundColor(ColorUtil.getValue(mainBackgroundColor));
		}
		
		String focusedBackgroundColor = hashCellAttribute.get(LayoutAttribute.FOCUSED_BACKGROUND_COLOR);
		if (StringUtil.isNotEmpty(focusedBackgroundColor)) {
			component.setFocusedBackgroundColor(ColorUtil.getValue(focusedBackgroundColor));
			component.setSelectedBackgroundColor(ColorUtil.getValue(focusedBackgroundColor));
		}
		
		String selectedBackgroundColor = hashCellAttribute.get(LayoutAttribute.SELECTED_BACKGROUND_COLOR);
		if (StringUtil.isNotEmpty(selectedBackgroundColor)) {
			component.setSelectedBackgroundColor(ColorUtil.getValue(selectedBackgroundColor));
		}
		
		String mainFontColor = hashCellAttribute.get(LayoutAttribute.MAIN_FONT_COLOR);
		if (StringUtil.isNotEmpty(mainFontColor)) {
			component.setMainFontColor(ColorUtil.getValue(mainFontColor));
			component.setFocusedFontColor(ColorUtil.getValue(mainFontColor));
			component.setSelectedFontColor(ColorUtil.getValue(mainFontColor));
		}
		
		String focusedFontColor = hashCellAttribute.get(LayoutAttribute.FOCUSED_FONT_COLOR);
		if (StringUtil.isNotEmpty(focusedFontColor)) {
			component.setFocusedFontColor(ColorUtil.getValue(focusedFontColor));
			component.setSelectedFontColor(ColorUtil.getValue(focusedFontColor));
		}
		
		String fontStyle = hashCellAttribute.get(LayoutAttribute.FONT_STYLE);
		if (StringUtil.isNotEmpty(fontStyle)) {
			component.setFontStyle(FontStyle.fromString(fontStyle));
			component.setFocusedFontStyle(FontStyle.fromString(fontStyle));
		}
		
		String focusedFontStyle = hashCellAttribute.get(LayoutAttribute.FOCUSED_FONT_STYLE);
		if (StringUtil.isNotEmpty(focusedFontStyle)) {
			component.setFocusedFontStyle(FontStyle.fromString(focusedFontStyle));
		}
		
		String selectedFontColor = hashCellAttribute.get(LayoutAttribute.SELECTED_FONT_COLOR);
		if (StringUtil.isNotEmpty(selectedFontColor)) {
			component.setSelectedFontColor(ColorUtil.getValue(selectedFontColor));
		}
				
		String isTransparent = hashCellAttribute.get(LayoutAttribute.TRANSPARENT);
		if (StringUtil.isNotEmpty(isTransparent)) {
			component.setTransparent(BooleanUtil.getValue(isTransparent));
		}
		
		String borderColor = hashCellAttribute.get(LayoutAttribute.BORDERCOLOR);
		if (StringUtil.isNotEmpty(borderColor)) {
			component.setBorderColor(ColorUtil.getValue(borderColor));
		}
				
		String border = hashCellAttribute.get(LayoutAttribute.BORDER);
		component.setBorder(BooleanUtil.getValue(border));
		
		String numberColumns = hashCellAttribute.get(LayoutAttribute.NUMBER_COLUMNS);
		if (StringUtil.isNotEmpty(numberColumns)) {
			int tmp = Integer.valueOf(numberColumns);
			component.setNumberColumns(tmp);
		}
		
		String numberRows = hashCellAttribute.get(LayoutAttribute.NUMBER_ROWS);
		if (StringUtil.isNotEmpty(numberRows)) {
			int tmp = Integer.valueOf(numberRows);
			component.setNumberRows(tmp);
		}
		
		String fontName = hashCellAttribute.get(LayoutAttribute.FONT_NAME);
		if (StringUtil.isNotEmpty(fontName)) {
			component.setFontName(fontName);
		}
		
		String fontSize = hashCellAttribute.get(LayoutAttribute.FONT_SIZE);
		if (StringUtil.isNotEmpty(fontSize)) {
			int tmp = Integer.valueOf(fontSize);
			component.setFontSize(tmp);
		}
		
		String maxLength = hashCellAttribute.get(LayoutAttribute.MAX_LENGTH);
		if (StringUtil.isNotEmpty(maxLength)) {
			int tmp = Integer.valueOf(maxLength);
			component.setMaxLength(tmp);
		}
		
		if (component instanceof DrawTextEdit)
		{
			String placeHolder = hashCellAttribute.get(LayoutAttribute.PLACE_HOLDER);
			if (StringUtil.isNotEmpty(placeHolder)) {
				component.setPlaceHolder(placeHolder);
			} else {
				component.setPlaceHolder(CmnGlobals.PLACE_HOLDER);
			}
		}
		
		String align = hashCellAttribute.get(LayoutAttribute.ALIGN);
		if (StringUtil.isNotEmpty(align)) {
			component.setAlign(Align.fromString(align));
		}
		
		String tagDefaultFigure = hashCellAttribute.get(LayoutAttribute.TAG_DEFAULT_FIGURE);
		if (StringUtil.isNotEmpty(tagDefaultFigure)) {
			component.setTagDefaultFigure(tagDefaultFigure);
		}
		
		String oneChoice = hashCellAttribute.get(LayoutAttribute.ONE_CHOICE);
		if (StringUtil.isNotEmpty(oneChoice)) {
			component.setOneChoice(BooleanUtil.getValue(oneChoice));
		}
		
		String figureType = hashCellAttribute.get(LayoutAttribute.FIGURE_TYPE);
		if (StringUtil.isNotEmpty(figureType)) {
			component.setFigureType(FigureType.fromString(figureType));
		} else {
			component.setFigureType(FigureType.NONE);
		}
		
		String tempDisplayig = hashCellAttribute.get(LayoutAttribute.TEMPORARY_DISPLAYING);
		component.setTemporaryDisplaying(BooleanUtil.getValue(tempDisplayig));
		
		String defaultFocused = hashCellAttribute.get(LayoutAttribute.DEFAULT_FOCUSED);
		component.setDefaultFocused(BooleanUtil.getValue(defaultFocused));
		
		//FIGURE_TYPE
	}


	public static int getFontStyle(String fontStyles) {
		
		int res= 0;
		String[] tabFontStyle = fontStyles.split("\\|");
		
		if (tabFontStyle != null && tabFontStyle.length > 0) {
			
			for(String name : tabFontStyle) {
				res += FontStyle.fromString(name);
			}
		}
		
		return res;
	}
	
}
