package model.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Coordinate;
import model.Fighter;
import model.Ship;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.InvalidSizeException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

public class GameShipPreTest {

	GameShip gameShip;
	List<Fighter>fleet ;
	GameBoard gameBoard;
	
	@Before
	public void setUp() throws Exception {
		 gameShip = new GameShip("Lanzadera T-4a", Side.IMPERIAL);
		 Fighter.resetNextId();
	}

	/* Se comprueba que el constructor copia de GameShip es correcto y que
	   GameShip es una clase derivada de Ship (no se han duplicado los atributos)
	 */
	//TODO
	@Test
	public void testGameShip() {
		assertEquals ("Lanzadera T-4a", gameShip.getName());
		assertEquals (Side.IMPERIAL, gameShip.getSide());
		assertEquals (0,  gameShip.getWins());
		assertEquals (0,  gameShip.getLosses());
		fleet = (List<Fighter>) gameShip.getFleetTest();
		assertNotNull (fleet);
		fail("Añade la instrucción que comprueba que GameShip es una clase derivada de Ship");
	}

	/* Se comprueba que isFleetDestroyed devuelve true si no hay cazas en
	 * la nave.
	 */
	//TODO
	@Test
	public void testIsFleetDestroyedEmpty() {
		assertTrue(gameShip.isFleetDestroyed());
	}
	
	/* Añade fighters a un GameShip. Luego destruyelos todos y comprueba que 
	 * isFleetDestroyed devuelve true.
	 */
	//TODO
	@Test
	public void testIsFleetDestroyedAllDestroyed() {
		GameShip gs = new GameShip("RGS-07",Side.REBEL);
		gs.addFighters("4/AWing:7/YWing");
		for(Fighter f : gs.getFleetTest())
			f.addShield(-99999);
		if(!gs.isFleetDestroyed()) {
			fail("ERROR: No funciona bien.");
		}
	}
	
	
	/* Añade cazas a una nave. Destruye todos menos uno y comprueba que 
	 * isFleetDestroyed() devuelve false
	 */
	//TODO
	@Test
	public void testIsFleetDestroyedNotAllDestroyed() {
		GameShip gs = new GameShip("RGS-07",Side.REBEL);
		gs.addFighters("4/AWing:7/YWing");
		for(int i = 0; i < gs.getFleetTest().size() - 2; i++)
			gs.getFleetTest().get(i).addShield(-99999);
		if(gs.isFleetDestroyed()) {
			fail("ERROR: No funciona bien.");
		}
	}
	
	/* Se comprueba que se obtiene una lista vacía de una nave sin cazas. Luego se
	 * añaden cazas, se destruyen todos y se comprueba que se sigue devolviendo una
	 * lista vacía.
	 */
	@Test
	public void testGetFightersIdListEmpty() {
		List<Integer> l = gameShip.getFightersId("");
		assertTrue (l.isEmpty());
		gameShip.addFighters("10/TIEFighter:35/TIEInterceptor:5/TIEBomber");
		for (Fighter f : gameShip.getFleetTest()) {
			f.addShield(-300);
		}
		l = gameShip.getFightersId("");
		assertTrue (l.isEmpty());
	}

	/* Añade cazas a un Ship. Comprueba que al invocar a getFightersId("ship") 
	 * se devuelve una lista con los 'id' de todos los cazas del la nave. 
	 * Además comprueba que al invocar a getFightesId("board") se devuelve una lista 
	 * vacía.
	 */
	//TODO
	@Test
	public void testGetFightersIdListNotEmpty1()  {
		gameShip.addFighters("10/TIEFighter:35/TIEInterceptor:5/TIEBomber");	
		
		List<Integer> l = gameShip.getFightersId("ship");
		for(Fighter f : gameShip.getFleetTest())
			assertTrue(l.contains(f.getId()));
		
		List<Integer> lb = gameShip.getFightersId("board");
		assertTrue(lb.isEmpty());
		
	}
	
	/* Se añaden cazas a un Ship. Se añaden todos a un tablero. Se comprueba que al invocar a  getFightersId("board") 
	 * se devuelve una lista con los id de todos los cazas del la nave.
	 * Además se comprueba que getFightersId("ship") devuelve una lista vacía
	 */
	//TODO
	@Test
	public void testGetFightersIdListNotEmpty2() throws InvalidSizeException, FighterAlreadyInBoardException, OutOfBoundsException  {
		gameShip.addFighters("10/TIEFighter:35/TIEInterceptor:5/TIEBomber");	
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());
		
		List<Integer> l = gameShip.getFightersId("ship");
		int k = 0;
		for(int i = 0; i < gameBoard.getSize(); i++) {
			try {
				gameShip.launch(gameShip.getFleetTest().get(k).getId(), new Coordinate(i,0), gameBoard);
				k++;
			} catch (WrongFighterIdException | FighterAlreadyInBoardException | OutOfBoundsException
					| RuntimeException e) {
				e.printStackTrace();
				fail("ERROR: Algo falla");
			}
		}
		
		List<Integer> lb = gameShip.getFightersId("board");
		
		for(Integer i : l)
			assertTrue(lb.contains(i));
		
		List<Integer> ls = gameShip.getFightersId("ship");
		assertTrue(ls.isEmpty());
	}
	
	/* Añade cazas a un Ship. Añade algunos a un tablero. Destruye otros. 
	 * Comprueba que al invocar a:
	 *  - getFightersId("board") se devuelve una lista solo con los que realmente
	 * 		están en el tablero. 
     *  - getFightersId("ship") se devuelve una lista solo con los que no están en el tablero y no están destruídos.
     *  - getFightersId("") se devuelve una lista con todos los no destruídos.
	 */
	//TODO
	@Test
	public void testGetFightersIdListNotEmpty3() throws InvalidSizeException, FighterAlreadyInBoardException, OutOfBoundsException  {
		GameShip gs = new GameShip("IGS-4",Side.IMPERIAL);
		gs.addFighters("6/XWing:7/AWing");
		GameBoard gameBoard = new GameBoard(20);
		try {
			gs.launch(gs.getFleetTest().get(0).getId(), new Coordinate (0,0), gameBoard);
			gs.launch(gs.getFleetTest().get(1).getId(), new Coordinate (1,1), gameBoard);
			gs.launch(gs.getFleetTest().get(2).getId(), new Coordinate (2,2), gameBoard);
			gs.launch(gs.getFleetTest().get(3).getId(), new Coordinate (3,3), gameBoard);
			gs.launch(gs.getFleetTest().get(4).getId(), new Coordinate (4,4), gameBoard);
			gs.launch(gs.getFleetTest().get(5).getId(), new Coordinate (5,5), gameBoard);
			gs.launch(gs.getFleetTest().get(6).getId(), new Coordinate (6,6), gameBoard);
			gs.launch(gs.getFleetTest().get(7).getId(), new Coordinate (7,7), gameBoard);
		} catch (WrongFighterIdException | FighterAlreadyInBoardException | OutOfBoundsException | RuntimeException e) {
			e.printStackTrace();
			fail("ERROR: Algo falla");
		}
		
		gameBoard.getFighter(new Coordinate(0,0)).addShield(-99999);
		gameBoard.getFighter(new Coordinate(2,2)).addShield(-99999);
		gameBoard.getFighter(new Coordinate(4,4)).addShield(-99999);
		gameBoard.getFighter(new Coordinate(7,7)).addShield(-99999);
		
		List<Integer> ls = gs.getFightersId("ship");
		List<Integer> lb = gs.getFightersId("board");
		List<Integer> ld = gs.getFightersId("");
		
		for(Fighter f : gameShip.getFleetTest())
			assertTrue(ls.contains(f.getId()));
		
		assertTrue(lb.contains(gameBoard.getFighter(new Coordinate(1,1)).getId()));
		assertTrue(lb.contains(gameBoard.getFighter(new Coordinate(3,3)).getId()));
		assertTrue(lb.contains(gameBoard.getFighter(new Coordinate(5,5)).getId()));
		assertTrue(lb.contains(gameBoard.getFighter(new Coordinate(6,6)).getId()));
		
		assertTrue(ld.contains(gameBoard.getFighter(new Coordinate(0,0)).getId()));
		assertTrue(ld.contains(gameBoard.getFighter(new Coordinate(2,2)).getId()));
		assertTrue(ld.contains(gameBoard.getFighter(new Coordinate(4,4)).getId()));
		assertTrue(ld.contains(gameBoard.getFighter(new Coordinate(7,7)).getId()));
	}
	
	/* Añade cazas a un GameShip e intenta poner uno, con launch, con una id que no existe. 
	 * Se comprueba que se lanza la excepción WrongFighterIdException y que no lo ha puesto.
	 * Luego destruye uno del GameShip e intenta ponerlo en el tablero. Comprueba que
	 * también se lanza la excepción WrongFighterIdException y que tampoco se ha puesto.
	 */
	//TODO
	@Test
	public void testLaunchWrongFighterIdException() throws InvalidSizeException {
		gameShip.addFighters("4/TIEFighter:3/TIEInterceptor:6/TIEBomber");
		Coordinate c=new Coordinate(4,3);
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());
		try {
			gameShip.launch(20, c, gameBoard);
			fail("ERROR: Debió lanzar la excepción WrongFighterIdException");
		} catch (WrongFighterIdException e1) {
			assertTrue(e1.getMessage().startsWith("ERROR:"));
			assertNull(gameBoard.getFighter(c));
		} catch (Exception e2) {
			fail("ERROR: No debió lanzar la excepción "+e2.getClass().getSimpleName());
		}	
		try {
			gameShip.getFleetTest().get(3).addShield(-9999);
			gameShip.launch(gameShip.getFleetTest().get(3).getId(), c, gameBoard);
			fail("ERROR: Deberia lanzar la excepion WrongFighterIdException");
		} catch (WrongFighterIdException e2) {
			assertTrue(e2.getMessage().startsWith("ERROR:"));
			assertNull(gameBoard.getFighter(c));
		} catch (Exception e2) {
			fail("ERROR: No debió lanzar la excepción "+e2.getClass().getSimpleName());
		}
	}
	
	
	/* Añaden cazas a un GameShip e intenta poner uno, con launch, en una coordenada
	 * fuera del tablero. 
	 * Comprueba que se lanza la excepción OutOfBoundsException.
	 */
	//TODO
	@Test
	public void testLaunchOutOfBoundsException() throws InvalidSizeException {
		GameShip gs = new GameShip("IMPERIAL STOMPER",Side.IMPERIAL);
		gs.addFighters("4/XWing");
		try {
			gs.launch(gs.getFleetTest().get(0).getId(), new Coordinate(99,99), new GameBoard(10));
		} catch (WrongFighterIdException e) {
			fail("ERROR: Deberia lanzar OutOfBoundsException");
		} catch (FighterAlreadyInBoardException e) {
			fail("ERROR: Deberia lanzar OutOfBoundsException");
		} catch (OutOfBoundsException e) {
			System.out.println(e.getMessage());
		} catch (RuntimeException e) {
			fail("ERROR: Deberia lanzar OutOfBoundsException");
		} catch (InvalidSizeException e) {
			fail("ERROR: Deberia lanzar OutOfBoundsException");
		}
	}
	
	/* Se añaden cazas a un GameShip y se pone a patrullar a uno de ellos en un
	 * tablero. Como no está en él, se comprueba que se lanza la excepción 
	 * FighterNotInBoardException y no otra y que el mensaje empieza con la
	 * cadena "ERROR:"
	 */
	@Test
	public void testPatrolNotInBoardException() throws InvalidSizeException {
		gameShip.addFighters("4/TIEFighter:3/TIEInterceptor:6/TIEBomber");
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());
		try {
			gameShip.patrol(13, gameBoard);
			fail("ERROR: Debió lanzar la excepción FighterNotInBoardException");
		} catch (FighterNotInBoardException e1) {
			assertTrue(e1.getMessage().startsWith("ERROR:"));
		} catch (Exception e2) {
			fail("ERROR: No debió lanzar la excepción "+e2.getClass().getSimpleName());
		}	
	}

	/* Añade cazas a un GameShip y pon a patrullar a uno con una id que
	 * no existe en el tablero. Como no está en él, comprueba que se lanza 
	 * la excepción WrongFighterIdException y no otra.
	 */
	//TODO
	@Test
	public void testPatrolWrongFighterIdException() throws InvalidSizeException {
		GameShip gs = new GameShip("REBEL RAM",Side.REBEL);
		gs.addFighters("2/AWing");
		try {
			gs.patrol(9, gameBoard);
			fail("ERROR: No funciona bien.");
		} catch (WrongFighterIdException | FighterNotInBoardException | RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/* Añade cazas a un GameShip y pon un TIEInterceptor en un tablero.
	 * Añade una mejora de 97 al caza del tablero. Comprueba que ya no está en
	 * el tablero y que el ataque ahora es de 133 y el escudo de 109.
	 */
	@Test
	public void testImproveFighter() throws WrongFighterIdException, FighterAlreadyInBoardException, OutOfBoundsException, InvalidSizeException {
		gameShip.addFighters("4/TIEFighter:3/TIEInterceptor:6/TIEBomber");
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());
		
		gameShip.launch(gameShip.getFleetTest().get(5).getId(), new Coordinate(3,3), gameBoard);
		gameShip.improveFighter(gameShip.getFleetTest().get(5).getId(), 97, gameBoard);
		assertNull(gameBoard.getFighter(new Coordinate(3,3)));
		assertEquals(gameShip.getFleetTest().get(5).getAttack(),133);
		assertEquals(gameShip.getFleetTest().get(5).getShield(),109);
	}
	
	/* Se añaden cazas a un GameShip. Se intenta mejorar uno de los cazas del GameShip que
	 * no está en tablero alguno. Se comprueba que ha existido la mejora en dicho caza.
	 * Se intenta mejorar un id de un caza que no existe. Se comprueba que se lanza la excepción
	 * WrongFighterIdException y que lanza el mensaje con el inicio de 'ERROR:'
	 */
	//TODO
	@Test
	public void testImproveFighterExceptions() throws FighterAlreadyInBoardException, OutOfBoundsException, InvalidSizeException {
		gameShip.addFighters("4/TIEFighter:3/TIEInterceptor:6/TIEBomber");
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());	
		try {
			gameShip.improveFighter(6, 97, gameBoard);
		} catch (WrongFighterIdException e) {
			fail("ERROR: No debió lanzar la excepción "+e.getClass().getSimpleName());
		}
		Fighter f=gameShip.getFleetTest().get(5);
		try {
			gameShip.improveFighter(90, 97, gameBoard);
			fail("ERROR: Algo no funciona");
		} catch(WrongFighterIdException e) {
			assertTrue(e.getMessage().startsWith("ERROR:"));
		}
	}
	
	/* Realiza las comprobaciones de los parámetros null en launch, patrol e improveFighter
	 * de GameShip */
	//TODO
	@Test
	public void testRequireNonNull() throws WrongFighterIdException, FighterAlreadyInBoardException, OutOfBoundsException, InvalidSizeException, FighterNotInBoardException  {
		
		try {
			gameShip.launch(2, null, new GameBoard(10));
			fail("ERROR: Debió lanzar NullPointerException");
		}catch (NullPointerException e) {}
		try {
			gameShip.launch(2, new Coordinate(3,2), null);
			fail("ERROR: Debió lanzar NullPointerException");
		}catch (NullPointerException e) {}
		try {
			gameShip.patrol(2, null);
			fail("ERROR: Debia lanzar NullPointerException");
		}catch (NullPointerException e) {}
		try {
			gameShip.improveFighter(2, 50, null);
			fail("ERROR: Debia lanzar NullPointerException");
		} catch	(NullPointerException e) {}
	}
}
