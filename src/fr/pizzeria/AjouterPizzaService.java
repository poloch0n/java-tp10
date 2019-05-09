package fr.pizzeria;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.utils.Validator;
import fr.pizzeria.FromMenu;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.ValidateException;

public class AjouterPizzaService extends MenuService{
	void executeUc(PizzaMemDao dao) throws SavePizzaException, ValidateException, IllegalArgumentException, IllegalAccessException{
		showText("Ajout d'une nouvelle pizza");
		Pizza newPizza = FromMenu.getInformationPizza();
		Validator.ToValidate(newPizza);
		String message = dao.checkInformationPizza(newPizza,true,"add");

		if(!message.equals("")) {
			throw new SavePizzaException(message);
		}
		dao.saveNewPizza(newPizza);
	}
}
