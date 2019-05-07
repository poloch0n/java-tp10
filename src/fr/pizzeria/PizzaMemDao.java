package fr.pizzeria;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaMemDao implements IPizzaDao {


	static List<Pizza> menu;
	PizzaMemDao(){
		initialisationMenu();
	}
	private void addPizzaMenuWithId(Integer id,String code, String libelle, double prix, CategoriePizza categorie) {
		
		Pizza pizza = new Pizza(id,code,libelle,prix,categorie);
		saveNewPizza(pizza);
	}
	
	@Override
	public List<Pizza> findAllPizzas() {
		
		return menu;
	}

	@Override
	public void saveNewPizza(Pizza newPizza) {
		menu.add(newPizza);
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {

		menu.set(getIndexPizzaOnMenu(codePizza), pizza);
		
	}

	@Override
	public void deletePizza(String codePizza) {
		
		if(menu.size() == 1) {
			//todo delete menu;
			menu = null;
		} else if(menu != null ) {
			menu.remove(menu.get(getIndexPizzaOnMenu(codePizza)));
		}
	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {

		if(checkMenuEmpty()){
			return null;
		}
		int index;
		for (index = 0; index < menu.size(); index++ ) {
			if(menu.get(index).code.equals(codePizza)) {
				return new Pizza(menu.get(index).id,menu.get(index).code,menu.get(index).libelle,menu.get(index).prix,menu.get(index).categorie);
			}
		}
		return null;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
			for (int i = 0; i < menu.size(); i++ ) {
				if(menu.get(i).code.equals(codePizza)) {
					return true;
				}
			}
		return false;
	}

	private Integer getIndexPizzaOnMenu(String codePizza) {
		if(pizzaExists(codePizza)) {
			int index;
			for (index = 0; index < menu.size(); index++ ) {
				if(menu.get(index).code.equals(codePizza)) {
					return index;
				}
			}
		}
		return null;
	}
	
	void initialisationMenu() {
		menu = new ArrayList<Pizza>();
		addPizzaMenuWithId(0, "PEP","Pépéroni", 12.50,CategoriePizza.VIANDE);
		addPizzaMenuWithId(1, "MAR","Margherita", 14.00,CategoriePizza.VIANDE);
		addPizzaMenuWithId(2, "REI","La Reine", 11.50,CategoriePizza.SANS_VIANDE);
		addPizzaMenuWithId(3, "FRO","La 4 fromages", 12.00,CategoriePizza.SANS_VIANDE);
		addPizzaMenuWithId(4, "CAN","La cannibale", 12.50,CategoriePizza.VIANDE);
		addPizzaMenuWithId(5, "SAV","La savoyarde", 13.00,CategoriePizza.VIANDE);
		addPizzaMenuWithId(6, "ORI","L'orientale", 13.50,CategoriePizza.VIANDE);
		addPizzaMenuWithId(7, "IND","L'indienne", 14.00,CategoriePizza.VIANDE);
		
	}
	
	boolean checkMenuEmpty() {
		
		if(menu == null) {
			return true;
		} else {
			return false;
		}
	}

	public String checkInformationPizza(Pizza pizza, boolean unicity, String methode) {
		
		String error = "";
		error += checkFormatInformationPizza(pizza);

		if(unicity) {
			error += checkUnicityInformationPizza(pizza,methode);
		}
		
		return error;
	}

	public String checkFormatInformationPizza(Pizza pizza) {
		String error = "";
		if(pizza.code.equals("")) {
			error += "\r\nLe format du code "+ pizza.code +" est invalide";
		}
		if(pizza.libelle.equals("")) {
			error += "\r\nLe format du libelle " + pizza.libelle + " est invalide";
		}
		if(isNegative(pizza.prix)) {
			error += "\r\nLe format du prix est invalide";
		}
		return error;
	}
	
	private String checkUnicityInformationPizza(Pizza pizza,String methode) {
		String error = "";
		for (Pizza pizzaSaved : menu) {
			if(pizzaSaved.code.equals(pizza.code)) {
				error += "\r\nCe code a déjà été utilisé";
			}
			if(pizzaSaved.libelle.equals(pizza.libelle)) {
				error += "\r\nCe libelle a déjà été utilisé";
			}
			if(methode.equals("add") && pizzaSaved.id == pizza.id) {
					pizza.id ++;
			}
		}
		return error;
	}

	boolean isNegative(double d) {
	     return Double.compare(d, 0.0) < 0;
	}
}
