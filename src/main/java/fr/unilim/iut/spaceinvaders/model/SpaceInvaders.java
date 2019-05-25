package fr.unilim.iut.spaceinvaders.model;

import fr.unililm.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unililm.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unililm.iut.spaceinvaders.utils.MissileException;
import fr.unilim.iut.spaceinvaders.moteurjeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurjeu.Jeu;

public class SpaceInvaders implements Jeu {

	
	int longueur;
	int hauteur;
	Vaisseau vaisseau;
	Missile missile;
	Envahisseur envahisseur;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append(Constante.MARQUE_FIN_LIGNE);
		}
		return espaceDeJeu.toString();
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y)) {
			marque = Constante.MARQUE_VAISSEAU;
		}else if (this.aUnEnvahisseurQuiOccupeLaPosition(x, y)) {
			marque = Constante.MARQUE_ENVAHISSEUR;
		}else if (this.aUnMissileQuiOccupeLaPosition(x, y)) {
			 marque = Constante.MARQUE_MISSILE;
		}else { marque = Constante.MARQUE_VIDE;
		}
		return marque;
	}
	private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
		return this.aUnEnvahisseur() && envahisseur.occupeLaPosition(x, y);
	}

	public boolean aUnEnvahisseur() {
		return envahisseur != null;
	}

	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		return this.aUnMissile() && missile.occupeLaPosition(x, y);
	}
	public boolean aUnMissile() {
		return missile != null;
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}

	public boolean aUnVaisseau() {
		return vaisseau != null;
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}
	private int premiereOrdonnee() {
		return this.hauteur - Constante.LIGNE_DES_ORDONNEES_NUM_8;
		
	}
	private int derniereAbscisse() {
		return this.longueur - 1;
	}
	private int premiereAbscisse() {

		return this.hauteur - (this.hauteur - 1);

	}
	private int abscisseMilieuEcran() {
		return this.longueur / 2;
	}

	public void deplacerVaisseauVersLaDroite() {
		deplacerSpriteVersLaDroite(vaisseau);
	}
	public void deplacerVaisseauVersLaGauche() {
		deplacerSpriteVersLaGauche(vaisseau);
	}
	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {

		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		int longueurVaisseau = dimension.longueur();
		int hauteurVaisseau = dimension.hauteur();

		if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		vaisseau = new Vaisseau(dimension,position,vitesse);
	}

	@Override
	public void evoluer(Commande commandeUser) {

		if (commandeUser.gauche) {
			deplacerVaisseauVersLaGauche();
		}

		if (commandeUser.droite) {
			deplacerVaisseauVersLaDroite();
		}
		if (this.aUnMissile()) {
			deplacerMissile();
			
		}
		if (commandeUser.tir && !this.aUnMissile()) {
	           tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),
						Constante.MISSILE_VITESSE);
		}
		if(this.aUnEnvahisseur()) {
			this.deplacerAutomatiquementUnEnvahisseur();
			
		}	
	}

	@Override
	public boolean etreFini() {

		if(!this.aUnEnvahisseur()) {
			return true;
		}
		return false;
	}

	public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}

	public void initialiserJeu() {
		Position positionVaisseau = new Position(this.longueur/2,this.hauteur-1);
		Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
		positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);
		
		Position positionEnvahisseur = new Position(this.abscisseMilieuEcran(), this.premiereOrdonnee());
		Dimension dimensionEnvahisseur = new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_HAUTEUR);
		this.positionnerUnNouveauEnvahisseur(dimensionEnvahisseur, positionEnvahisseur, Constante.ENVAHISSEUR_VITESSE);
	 }

	public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
		
		   if ((vaisseau.hauteur()+ dimensionMissile.hauteur()) > this.hauteur )
			   throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
							
		   this.missile = this.vaisseau.tirerUnMissile(dimensionMissile,vitesseMissile);
    }

	public Missile recupererMissile() {
		return this.missile;
	}

	public void deplacerMissile() {
		if (0 < missile.ordonneeLaPlusHaute()) {
			missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
		}
		if (0 >= missile.ordonneeLaPlusHaute()) {
			missile = null;
		}
	}
	public void positionnerUnNouveauEnvahisseur(Dimension dimension, Position position, int vitesse) {
		
		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException(
					"La position de l'envahisseur est en dehors de l'espace jeu");

		int longueurEnvahisseur = dimension.longueur();
		int hauteurEnvahisseur = dimension.hauteur();

		if (!estDansEspaceJeu(x + longueurEnvahisseur - 1, y))
			throw new DebordementEspaceJeuException(
					"L'envahisseur déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurEnvahisseur + 1))
			throw new DebordementEspaceJeuException(
					"L'envahisseur déborde de l'espace jeu vers le bas à cause de sa hauteur");

		envahisseur = new Envahisseur(dimension,position,vitesse);

	}
	private void deplacerSpriteVersLaGauche(Sprite sprite) {
		if (0 < sprite.abscisseLaPlusAGauche()) {
			sprite.deplacerHorizontalementVers(Direction.GAUCHE);
		}
		if (!estDansEspaceJeu(sprite.abscisseLaPlusAGauche(), sprite.ordonneeLaPlusHaute())) {
			sprite.positionner(0, sprite.ordonneeLaPlusHaute());
		}
	}
	public void deplacerSpriteVersLaDroite(Sprite sprite) {

		if (sprite.abscisseLaPlusADroite() < (longueur - 1)) {
			sprite.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(sprite.abscisseLaPlusADroite(), sprite.ordonneeLaPlusHaute())) {
				sprite.positionner(longueur - sprite.longueur(), sprite.ordonneeLaPlusHaute());
			}
		}
	}
	

	public void deplacerEnvahisseurVersLaDroite() {
		deplacerSpriteVersLaDroite(envahisseur);
	}

	public void deplacerEnvahisseurVersLaGauche() {

		deplacerSpriteVersLaGauche(envahisseur);

	}
	public Envahisseur recupererEnvahisseur() {
		return this.envahisseur;
	}
	public void deplacerAutomatiquementUnEnvahisseur() {
		if (this.aUnEnvahisseur() && envahisseurSeDeplaceVersLaDroite()) {
			
			this.deplacerSpriteVersLaDroite(envahisseur);
			
			if(this.aUnEnvahisseur() && envahisseur.abscisseLaPlusADroite() >= this.derniereAbscisse()) {
				this.envahisseur.changerDirectionHoziontale();
			}
			

		} else if (this.aUnEnvahisseur() && envahisseurSeDeplaceVersLaGauche()) {

			this.deplacerSpriteVersLaGauche(envahisseur);
			
			if(this.aUnEnvahisseur() && envahisseur.abscisseLaPlusAGauche() <= this.premiereAbscisse()) {
				this.envahisseur.changerDirectionHoziontale();
			}

		}
	}
	private boolean envahisseurSeDeplaceVersLaGauche() {
		return this.aUnEnvahisseur() && this.envahisseur.SeDeplaceVersLaDroite() == false;
	}

	private boolean envahisseurSeDeplaceVersLaDroite() {
		return this.aUnEnvahisseur() && this.envahisseur.SeDeplaceVersLaDroite() == true;
	}
}
