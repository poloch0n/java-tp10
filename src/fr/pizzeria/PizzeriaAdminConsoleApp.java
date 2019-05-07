package fr.pizzeria;

import fr.pizzeria.FromMenu;
import fr.pizzeria.ShowMenu;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.MenuServiceFactory;

public class PizzeriaAdminConsoleApp {

	private static PizzaMemDao dao;
	private static MenuServiceFactory msf;
	private static FromMenu fm;
	public static void main(String[] args) throws Exception {
		
		dao = new PizzaMemDao();
		fm = new FromMenu();
		msf = new MenuServiceFactory();
		ShowMenu.showIntroduction();
		while(true) {
			ShowMenu.showMenuOptions();
			int answer = FromMenu.getIntFromMenu();
			try {
				msf.run(answer).executeUc(dao);				
			} catch(PizzaException pizzaException) {
				
			}
		}
	}
}