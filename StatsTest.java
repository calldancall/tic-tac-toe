package ticTacToe;
import org.junit.*;
import static org.junit.Assert.*;


public class StatsTest {

  private Stats stat;

  /* this sets up the test fixture. JUnit invokes this method before
      every testXXX method.  
      The @Before tag tells JUnit to run this method 
     before each test */
  @Before
  public void setUp() throws Exception {
   stat = new Stats(1, 2, 3);
  }
  
  /* The @Test tag tells JUnit this is a test to run */
  @Test
  public void testgetTotalGames() {
   System.out.println("Checking getTotalGames");
   assertEquals(6, stat.getTotalGames());
  }

  @Test
  public void testIncrements() {
   System.out.println("Checking Proper Increment");
   stat.incrementComputerWins();
   assertEquals(7, stat.getTotalGames());
   stat.incrementUserWins();
   assertEquals(8, stat.getTotalGames());
   stat.incrementTies();
   assertEquals(9, stat.getTotalGames()); 
  }
 

  @Test
  public void testReset() {
   System.out.println("Checking Reset");
   stat.reset();
   assertEquals(0, stat.getTotalGames());
  }

  @Test
  public void testAverageGames(){
   System.out.println("Checking AverageGames");
   assertEquals(1.0/6.0*100, stat.averageGames(0), 0.001);
   assertEquals(2.0/6.0*100, stat.averageGames(1), 0.001);
   assertEquals(3.0/6.0*100, stat.averageGames(2), 0.001);
  }
  
  @Test
  public void testResetWrong(){
   System.out.println("Checking resetWrong");
   stat.resetWrong();
   assertEquals(0, stat.getTotalGames());
  }

}