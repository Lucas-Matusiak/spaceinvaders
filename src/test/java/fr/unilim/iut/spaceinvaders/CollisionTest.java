package fr.unilim.iut.spaceinvaders;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Collision;
import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;

public class CollisionTest {
	
	private SpaceInvaders spaceinvaders;
	@Before
    public void initialisation() {
	    spaceinvaders = new SpaceInvaders(15, 10);
    }
	 @Test
	 public void test_MissileAvanceVerticalement_EstInclusDansAbscisseEnvahisseur_EtNeDetectePasLaCollisionAvecEnvahisseur() {
		 
		 spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
	 	   spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(5,2),new Position(7,1), 3);
	 	   
	 	   spaceinvaders.tirerUnMissile(new Dimension(1,2),1);
	 	   for (int i = 1; i <=3 ; i++) {
			    spaceinvaders.deplacerMissile();
		    }
	 	   
	 	   
	 	  spaceinvaders.deplacerMissile();
	 	 
	 	  assertTrue(Collision.abscisseSprite1EstInclusDansAbscisseDeSprite2(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	      assertFalse(Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	 }
	 @Test
	 public void test_MissileAvanceVerticalement_EstDansInclusDansOrdonneeEnvahisseur_EtNeDetectePasLaCollisionAvecEnvahisseur() {
		 
		 spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(2,9), 1);
	 	   spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(5,2),new Position(7,1), 3);
	 	   
	 	   spaceinvaders.tirerUnMissile(new Dimension(1,2),1);
	 	   for (int i = 1; i <=5 ; i++) {
			    spaceinvaders.deplacerMissile();
		    }
	 	  spaceinvaders.deplacerMissile();
	 	 
	 	 assertTrue(Collision.ordonneeLaPlusBasseSprite1EstInclusDansOrdonneeSprite2(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	     assertFalse(Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	 }
	 
	 @Test
	 public void test_MissileAvanceVerticalement_OrdonneeLaPlusBasseMissileInclusDansOrdonneeEtAbscisseEnvahisseur_DetecteCollisionAvecEnvahisseur() {
		 
		 spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
	 	   spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(5,2),new Position(7,1), 3);
	 	   
	 	   spaceinvaders.tirerUnMissile(new Dimension(1,2),1);
	 	   for (int i = 1; i <=5 ; i++) {
			    spaceinvaders.deplacerMissile();
		    }
	 	  spaceinvaders.deplacerMissile();
	 	  
	 	 assertTrue(Collision.ordonneeLaPlusBasseSprite1EstInclusDansOrdonneeSprite2(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	     assertTrue(Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	 }
	 
	 @Test
	 public void test_MissileAvanceVerticalement_OrigineEnOrdonneeEtAbscisseLaPlusADroiteMissileInclusDansOrdonneeEtAbscisseEnvahisseur_DetecteCollisionAvecEnvahisseur() {
		 
		 spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(4,9), 1);
	 	   spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(5,2),new Position(7,2), 3);
	 	   
	 	   spaceinvaders.tirerUnMissile(new Dimension(2,2),1);
	 	   for (int i = 1; i <=5 ; i++) {
			    spaceinvaders.deplacerMissile();
		    }
	 	  spaceinvaders.deplacerMissile();
	 	  
	 	  assertTrue(Collision.abscisseLaPlusADroiteSprite1EstInclusDansAbscisseDeSprite2(spaceinvaders.recupererMissile(),spaceinvaders.recupererEnvahisseur()));
	      assertTrue(Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	      
	 }
	 
	 @Test
	 public void test_MissileAvanceVerticalement_OrdonneeLaPlusBasseEtAbscisseLaPlusADroiteMissileInclusDansOrdonneeEtAbscisseEnvahisseur_DetecteCollisionAvecEnvahisseur() {
		 
		 spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(4,9), 1);
	 	   spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(5,2),new Position(7,1), 3);
	 	   
	 	   spaceinvaders.tirerUnMissile(new Dimension(2,2),1);
	 	   for (int i = 1; i <=5 ; i++) {
			    spaceinvaders.deplacerMissile();
		    }
	 	  spaceinvaders.deplacerMissile();
	 	 
	      assertTrue(Collision.detecterCollision(spaceinvaders.recupererMissile(), spaceinvaders.recupererEnvahisseur()));
	 } 
}
