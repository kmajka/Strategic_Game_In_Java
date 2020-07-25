package com.bwizard.wsgame;

import java.util.Arrays;
import java.util.List;

import com.bwizard.cegame.logs.LogInfo;
import com.bwizard.cegame.logs.LogMonitor;
import com.bwizard.cegame.validation.BaseValidator;
import com.bwizard.cegame.validation.interfaces.IValidField;
import com.bwizard.wsgame.documents.names.CmnComponentName;
import com.bwizard.wsgame.documents.names.CmnObjectName;
import com.bwizard.wsgame.documents.names.EditorComponentName;
import com.bwizard.wsgame.documents.names.EditorObjectName;
import com.bwizard.wsgame.documents.names.GameComponentName;
import com.bwizard.wsgame.documents.names.GameObjectName;
import com.bwizard.wsgame.documents.names.MenuComponentName;
import com.bwizard.wsgame.parameters.GameGlobals;
import com.bwizard.wsgame.window.WindowManagerGame;

/**
 * This class provides possibility to run game
 * @author Krzysztof Majka
 * @version 1.0
 */
public class MainGame {
	
	private static final LogInfo logInfo = new LogInfo(MainGame.class);
	
   /**
   * This method demonstrates the game.
   * @param args Unused.
   * @return Nothing.
   * @throws Exception 
   */
	public static void main(String[] args) {
			
		BaseValidator baseValidator = new BaseValidator();
		
		try	{
			LogInfo.configure(GameGlobals.CONFIG_DATA + "log4j.properties");
			
			List<IValidField> listFieldsToCheck = Arrays.asList( new CmnComponentName(), new CmnObjectName(), new EditorComponentName(),
					new EditorObjectName(), new GameComponentName(), new GameObjectName(), new MenuComponentName() );
					
			baseValidator.validateFields(listFieldsToCheck);			
			WindowManagerGame windowManagerGame = new WindowManagerGame();
			windowManagerGame.runGame();

		} catch (Exception ex) {
			LogMonitor.buildInfo(logInfo, ex);
			System.out.println(ex.getMessage());
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
