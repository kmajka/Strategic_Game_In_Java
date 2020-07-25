package com.bwizard.cegame.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.bwizard.cegame.controls.components.BaseDrawFigure;
import com.bwizard.cegame.logs.LogInfo;

public class FigureManager {
	
	private static final LogInfo logInfo = new LogInfo(FigureManager.class);
	
	private List<BaseDrawFigure> listComponent = null;
	private String pathData;
	private BaseDrawFigure figureTemplate = null;
	
	
	public FigureManager(String pathData, BaseDrawFigure figureTemplate) {
		this.listComponent = new ArrayList<BaseDrawFigure>();
		this.pathData = pathData;
		this.figureTemplate = figureTemplate;
	}
	
	public List<BaseDrawFigure> getFilesInOutDirectories() {
		logInfo.info("start: getFilesInOutDirectories()");
		
		listComponent = new ArrayList<BaseDrawFigure>();
		
		File folder = new File((new File("")).getAbsolutePath() + pathData);
		File[] listOfFiles = folder.listFiles();
		
		int i = 0;
		for (File file : listOfFiles) {
			if (file.isFile()) {
				//figureTemplate - template will be use for all components lying into group
				BaseDrawFigure baseDrawFigure = (BaseDrawFigure)figureTemplate.clone();
				baseDrawFigure.setFileName(file.getName());
				baseDrawFigure.setPositionRow(i++);
				listComponent.add(baseDrawFigure);
			}
		}
            
		logInfo.info("end: getFilesInOutDirectories()");
		return listComponent;
	}
	
	public void dispose() {
		listComponent.clear();
	}
	
	
}
