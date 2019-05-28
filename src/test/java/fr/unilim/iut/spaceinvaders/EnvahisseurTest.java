package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
public class EnvahisseurTest {
	
	private SpaceInvaders spaceinvaders;

	@Before
    public void initialisation() {
	    spaceinvaders = new SpaceInvaders(15, 10);
    }
	 @Test
	  public void  test_unNouveauEnvahisseurEstCorrectementPositionneDansEspaceJeu() {
		  
		  SpaceInvaders spaceinvaders = new SpaceInvaders(15, 10);
		  spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2,1), new Position(7,1), 1);
		   
	       assertEquals("" +
	       "...............\n" + 
	       ".......EE......\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
		  
	  }

	@Test
    public void test_EnvahisseurAvanceAutomatiquement_VersLaDroite() {

	   spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,1),new Position(7,1), 3);

	   spaceinvaders.deplacerAutomatiquementUnEnvahisseur();
	   
       Assert.assertEquals("" + 
       "...............\n" + 
       "..........EEE..\n" +
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
   }
 
@Test
    public void test_EnvahisseurAvanceAutomatiquement_VersLaGauche() {

	   spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,1),new Position(7,1), 3);
	   spaceinvaders.deplacerEnvahisseurVersLaGauche();
       Assert.assertEquals("" + 
       "...............\n" + 
       "....EEE........\n" +
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
   }
 
@Test
 public void test_Envahisseur_ArriveAuBoutDeLaLigneVersLaDroite_PuisChangeDirectionVersLaGauche() {

	   spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,1),new Position(11,1), 3);
	   
	   spaceinvaders.deplacerAutomatiquementUnEnvahisseur();
	   
       Assert.assertEquals("" + 
       "...............\n" + 
       "............EEE\n" +
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
       
       
       Assert.assertFalse(spaceinvaders.recupererEnvahisseur().SeDeplaceVersLaDroite());
   }

@Test
public void test_Envahisseur_ArriveAuBoutDeLaLigneVersLaGauche_PuisChangeDirectionVersLaDroite() {

	   spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,1),new Position(1,1), 3);
	   
	   spaceinvaders.recupererEnvahisseur().changerDirectionHoziontale();
	   spaceinvaders.deplacerAutomatiquementUnEnvahisseur();
	   
      Assert.assertEquals("" + 
      "...............\n" + 
      "EEE............\n" +
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" + 
      "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
      
      
      Assert.assertTrue(spaceinvaders.recupererEnvahisseur().SeDeplaceVersLaDroite());
  }
	
}