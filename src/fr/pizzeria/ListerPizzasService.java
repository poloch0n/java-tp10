package fr.pizzeria;

import fr.pizzeria.model.Pizza;

public class ListerPizzasService extends MenuService{
	void executeUc(PizzaMemDao dao) throws IllegalArgumentException, IllegalAccessException {
		if(dao.checkMenuEmpty()) {
			showText("Vous n'avez pas encore ajouter de pizza, prenez l'option 2");
			return;
		}
		showText("Liste des pizzas");
		for(Pizza pizza: dao.findAllPizzas()) {
			String msg = pizza.ToString();
			//String text = pizza.code + " -> " + pizza.libelle + " (" + pizza.prix + " �) option : "+pizza.categorie.toString();
			showText(msg);
		}
	}
}
