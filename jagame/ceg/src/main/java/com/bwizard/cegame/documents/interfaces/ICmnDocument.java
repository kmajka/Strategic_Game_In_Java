package com.bwizard.cegame.documents.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

public interface ICmnDocument<T> {
	ArrayList<T> createElementFromCellAttribute(T parentElement, HashMap<String, String> hashCellAttribute);
}
