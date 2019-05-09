package fr.pizzeria;

import fr.pizzeria.FromMenu;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.exception.ValidateException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.utils.Validator;

public class ModifierPizzaService extends MenuService {
	void executeUc(PizzaMemDao dao) throws UpdatePizzaException, ValidateException, IllegalArgumentException, IllegalAccessException{
		
		showText("Mise à jour d’une pizza");
		if(dao.checkMenuEmpty()) {
			throw new UpdatePizzaException("Vous n'avez pas encore ajouter de pizza, prenez l'option 2");
		}
		
		showText("Veuillez choisir le code de la pizza à modifier");
		String code = FromMenu.getCode();
		//Vérification de l'existance de la pizza
		if(!dao.pizzaExists(code)) {
			throw new UpdatePizzaException("test Le code saisi semble ne correspondre a aucune pizza, pouvez vous réessayer ?\r\n");
		}
		Pizza updatedPizza = FromMenu.getInformationPizza();
		Validator.ToValidate(updatedPizza);
		dao.updatePizza(code,updatedPizza);
			
	}
}
