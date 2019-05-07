package fr.pizzeria;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.FromMenu;
import fr.pizzeria.exception.SavePizzaException;

public class AjouterPizzaService extends MenuService{
	void executeUc(PizzaMemDao dao) throws SavePizzaException{
		showText("Ajout d'une nouvelle pizza");
		Pizza newPizza = FromMenu.getInformationPizza();
		String message = dao.checkInformationPizza(newPizza,true,"add");

		if(!message.equals("")) {
			throw new SavePizzaException(message);
		}
		dao.saveNewPizza(newPizza);
	}
}
