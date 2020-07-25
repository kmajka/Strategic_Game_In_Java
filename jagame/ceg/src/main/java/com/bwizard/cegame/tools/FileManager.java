package com.bwizard.cegame.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.bwizard.cegame.logs.LogInfo;

public class FileManager {
	
	private static final LogInfo logInfo = new LogInfo(FileManager.class);
	
	private List<File> listFile = new ArrayList<File>();
	private String pathData;
	
	public FileManager(String pathData) {
		this.pathData = new File("").getAbsolutePath() + pathData;
	}
	
	public InputStream getInputStream() {
		InputStream is = null;
		
		try {			
			is = new FileInputStream(pathData);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return is;
	}
	
	public File getFile() {
		return new File(pathData);
	}
	
	public List<File> getFilesInDirectories() {
		logInfo.info("start: getFilesInDirectories()");
		
		listFile = new ArrayList<File>();

		File folder = new File(pathData);
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
			if (file.isFile()) {
				listFile.add(file);
			}
		}
            
		logInfo.info("end: getFilesInDirectories()");
		return listFile;
	}
	
	public void dispose() {
		if (listFile != null) {
			listFile.clear();
		}
	}
	
}
