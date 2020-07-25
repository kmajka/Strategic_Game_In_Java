package com.bwizard.cegame.device.model;

import java.awt.event.KeyEvent;

import com.bwizard.cegame.controller.BaseWorldGame;
import com.bwizard.cegame.controls.components.BaseDrawFigure;
import com.bwizard.cegame.utils.StringUtil;

public class KeysDialogModel extends KeysModel {
	
	public KeysDialogModel(BaseWorldGame baseWorldGame) {
		super(baseWorldGame);
		// TODO Auto-generated constructor stub
	}
		
	@Override
	public void keyReleased(KeyEvent e){
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		BaseDrawFigure b = null;
		switch(arg0.getKeyCode()) {
			case KeyEvent.VK_BACK_SPACE:
				b = getTextEditorFigure();
				if (b != null) {
					int endIndex = b.textProvider.getText().length();
					if (endIndex > 1) {
						String[] str = StringUtil.split(b.textProvider.getText(), b.textProvider.getPlaceHolder());
					    String prev = str.length > 0 ? str[0] : "" ;
					    String next = str.length > 1 ? str[1] : "" ;
					    String firstPartSentence = prev.length() > 0 ? prev.substring(0, prev.length() - 1) : "";
					    
					    b.textProvider.setText(firstPartSentence + b.textProvider.getPlaceHolder() + next);
					}
				}
			break;
			case KeyEvent.VK_RIGHT:
				b = getTextEditorFigure();
				if (b != null) {
					int endIndex = b.textProvider.getText().length();
					if (endIndex > 1) {
						String[] str = StringUtil.split(b.textProvider.getText(), b.textProvider.getPlaceHolder());
					    String prev = str.length > 0 ? str[0] : "" ;
					    String next = str.length > 1 ? str[1] : "" ;
					    String firstSignSentence = next.length() > 0 ? next.substring(0, 1) : "";
					    String restSentence = next.length() > 0 ? next.substring(1) : "";
					    
					    b.textProvider.setText(prev +  firstSignSentence + b.textProvider.getPlaceHolder() + restSentence);
					}
				}
				break;
			case KeyEvent.VK_LEFT:
				b = getTextEditorFigure();
				if (b != null) {
					int endIndex = b.textProvider.getText().length();
					if (endIndex > 1) {
						String[] str = StringUtil.split(b.textProvider.getText(), b.textProvider.getPlaceHolder());
					    String prev = str.length > 0 ? str[0] : "" ;
					    String next = str.length > 1 ? str[1] : "" ;
					    String firstPartSentence = prev.length() > 0 ? prev.substring(0, prev.length() - 1) : "";
					    String lastSignSentence = prev.length() > 0 ? prev.substring(prev.length() - 1) : "";
					    
					    b.textProvider.setText(firstPartSentence + b.textProvider.getPlaceHolder() + lastSignSentence + next);
					}
				}
				break;
			default:
				char c = arg0.getKeyChar();
				if (validate(c)) {
					b = getTextEditorFigure();
					if (b != null) {
						b.textProvider.setText(b.textProvider.getText().replace(b.textProvider.getPlaceHolder(),  c + b.textProvider.getPlaceHolder()));
					}
				}	
		}

	}
	
	private boolean validate(char c) {
		
		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == ' ' ) {
			return true;
		}
		return false;
	}
	
	private BaseDrawFigure getTextEditorFigure() {
		return baseWorldGame.getPanelLayoutManager().getFocusedElement();
	}

}
