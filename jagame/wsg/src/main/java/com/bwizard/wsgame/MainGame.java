package com.bwizard.wsgame;

import com.bwizard.cegame.validation.BaseValidator;
import com.bwizard.cegame.validation.interfaces.IValidField;

import com.bwizard.wsgame.config.GameRunner;
import com.bwizard.wsgame.documents.names.*;

import java.util.Arrays;
import java.util.List;

/**
 * This class provides possibility to run game
 * @author Krzysztof Majka
 * @version 1.0
 */
public class MainGame {
	
   /**
   * This method run the game.
   * @param args Unused.
   * @return Nothing.
   */
	public static void main(String[] args) {

		List<IValidField> listFieldsToCheck = Arrays.asList(new CmnComponentName(),
															new CmnObjectName(),
															new EditorComponentName(),
															new EditorObjectName(),
															new GameComponentName(),
															new GameObjectName(),
															new MenuComponentName());

        BaseValidator baseValidator = new BaseValidator();
		if (baseValidator.validateConstantFields(listFieldsToCheck)) {
			GameRunner game = new GameRunner();
			game.start();
		}
	}
	
	/*

	 1.figury powinny byc tworzone przez
	 	Rectanagle2D obj = new Rectanagle2D();
	 	g2.draw(obj)
	 zamiast g2.drawRect()
	 
	 ////////////////////////////////////
	 2. zastosowac FutureTask zamiast ThreadScheduler (ok)
	 
	 ///////////////////////////////////
	 3. odmalowywanie obrazu na osobnym watku
	 rezygnacja z jendego watku, kolejny watek do wyzanczania trasy, sztucznej inteligencji
	 
	 //////////////////////////////////
	 4. zastapic kolekcje objektow kolekcja synchroniczna  => 1 pisarz kilka czytelnikow
	   - zlikwidowac atrybut toDeleted() który kasuje niepotrzebne elementy na koncu petli 
	 
	 //////////////////////////////////
	 5. Zastosowanie obciecia obrazu  setClip
	 
	 //////////////////////////////////
	 6. Wykorzystanie metody contains która sprawdzy czy dany prostokt zawiera się w innym objekcie zamiast ręcznego porownywania pozycji wierzchołków
	 
	 //////////////////////////////////
	 7. wykorzystanie StringUtils i Math do obliczen zamiast swoich klas
	 
	 //////////////////////////////////
	 8.DataBuilderFactory zamiast swojej klasy
	   
	 //////////////////////////////////
	 9. zamiast skali 3 katalogi z obrazkami (opracowac sposob tworzenia takich obrazków)

	 //////////////////////////////////
	 10. metoda clone operuje na serializacji
	 
	 
	*/
	

	
}
