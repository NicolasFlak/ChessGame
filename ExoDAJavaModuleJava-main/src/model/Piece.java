package model;

//import java.util.*;


import data.Couleur;
import data.Echiquier;
import data.Mouvement;
//import echec.Mouvement;
import data.Position;

import java.util.ArrayList;
import java.util.List;

public class Piece implements Mouvement {
	protected Couleur couleur;
	protected Position position;
	private static List<Piece> toDelete=new ArrayList<Piece>();
	public static List<Position> posPrisesBlanc = new ArrayList<Position>(); 
	public static List<Position> posPrisesNoir = new ArrayList<Position>(); 
	public static List<Piece> tabPiece = new ArrayList<Piece>();				
	protected List<Position> mouvementExecutable =new ArrayList<Position>();


	public Piece(int x,int y,Couleur c)
	{
		position = new Position(x,y);
		couleur =c ;
		tabPiece.add(this);
	}


	public static List<Piece> getColoredPiece(Couleur c)
	{
		List<Piece> temp;
		if (c.equals(Couleur.BLACK))
			temp=getBlackPiece();
		else
			temp=getWhitePiece();
		return temp;
	}

	public static List<Piece> getWhitePiece()
	{
		List<Piece> tempTab = new ArrayList<Piece>();
		for(Piece temp:tabPiece)
		{
			if(temp.couleur.equals(Couleur.WHITE))
				tempTab.add(temp);
		}
		return tempTab;
	}
	public static List<Piece> getBlackPiece()
	{
		List<Piece> tempTab = new ArrayList<Piece>();
		for(Piece temp:tabPiece)
		{
			if(temp.couleur.equals(Couleur.BLACK))
				tempTab.add(temp);
		}
		return tempTab;
	}

	public Position getPosition() {
		return position;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	protected boolean bloqueAmi(Position mouvement) 
	{
		List<Piece> tempTab=new ArrayList<Piece>();
		List <Position> posPrises= new ArrayList<Position>();
		if (this.couleur==Couleur.WHITE) {
			tempTab= Piece.getWhitePiece();
			posPrises=posPrisesBlanc;
		}
		else if(this.couleur==Couleur.BLACK) {
			tempTab=Piece.getBlackPiece();
			posPrises=posPrisesNoir;
		}

		for (Piece count:tempTab)
		{
			if (count.position.equals(mouvement)) {
				posPrises.add(count.position);
				return true;
			}
		}
		return false;
	}

	protected boolean bloqueEnnemi(Position mouvement) 
	{
		List<Piece> tempTab=new ArrayList<Piece>();

		if (this.couleur==Couleur.BLACK)
			tempTab= Piece.getWhitePiece();
		else if(this.couleur==Couleur.WHITE)
			tempTab=Piece.getBlackPiece();

		for (Piece count:tempTab)
		{
			if (count.position.equals(mouvement))
				return true;
		}

		return false;
	}



	public static void updateAll() 
	{
		for (Piece count: tabPiece)
		{
			count.mouvementExecutable=count.getMouvementPossible();
			if(count.couleur.equals(Couleur.WHITE))
				posPrisesBlanc.addAll(count.mouvementExecutable);
			else
				posPrisesNoir.addAll(count.mouvementExecutable);
		}

		if(!toDelete.isEmpty())
		{
			for (Piece p:toDelete)
				tabPiece.remove(p);
		}

	}

	public boolean bouger(Position pos, Echiquier echiquier)
	{
		boolean ok=false;

		for (Position count: this.mouvementExecutable)
		{
			if(pos.equals(count))	
			{
				position=count;
				echiquier.getCase(count.getX(), count.getY()).positionner(this);
				ok=true;
			}
		}
		return ok;
	}

	public static List<Piece> getToDelete() {
		return toDelete;
	}

	public static List<Position> getPosPrisesBlanc() {
		return posPrisesBlanc;
	}

	public static List<Position> getPosPrisesNoir() {
		return posPrisesNoir;
	}

	public static List<Piece> getTabPiece() {
		return tabPiece;
	}

	public List<Position> getMouvementExecutable() {
		return mouvementExecutable;
	}

	public void destroy()
	{
		toDelete.add(this);
	}

	@Override
	public String toString() {
		return "Piece [couleur=" + couleur + ", position=" + position + "]";
	}

	@Override
	public List<Position> getMouvementPossible() {
		return null;
	}

}
