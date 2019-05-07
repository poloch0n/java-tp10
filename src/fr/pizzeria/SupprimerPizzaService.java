package fr.pizzeria;

import fr.pizzeria.FromMenu;
import fr.pizzeria.exception.DeletePizzaException;

public class SupprimerPizzaService extends MenuService{
	void executeUc(PizzaMemDao dao) throws DeletePizzaException {
		showText("Suppression d’une pizza");

		if(dao.checkMenuEmpty()) {
			throw new DeletePizzaException("Vous n'avez pas encore ajoutez de pizza, prenez l'option 2");
		}
		showText("Veuillez choisir le code de la pizza à supprimer");

		String code = FromMenu.getCode();
		//Vérification de l'existance de la pizza
		if(!dao.pizzaExists(code)) {
			throw new DeletePizzaException("Le code saisi semble ne correspondre a aucune pizza, pouvez vous réessayer ?\r\n\"");
		}

		dao.deletePizza(code);
		showText("what is done can't be undone. you won't see him again");
	
	}
}
