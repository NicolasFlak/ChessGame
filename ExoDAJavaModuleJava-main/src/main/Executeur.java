package main;

import utils.Utilitaires;

public class Executeur {

	public static void main(String[] args) {

		//Nommer les joueurs :
		System.out.println("Bonjour et bienvenue,\n" +
				"Veuillez entrer les noms des joueurs : ");

		Game g = new Game(Game.getJoueur1(),Game.getJoueur2());
		g.lancer();
	}
}