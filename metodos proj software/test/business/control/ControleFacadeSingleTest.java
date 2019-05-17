/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.control;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ziaod
 */
public class ControleFacadeSingleTest {
       
    ControleFacadeSingleTest instance;

    public ControleFacadeSingleTest() {
     instance = ControleFacadeSingleTest.getInstance(new ControleRelatorio(), new ControleDeUsuario());

    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class ControleFacadeSingle.
     */
    
    
    
     @Test
    public void testAddNewUser() throws Exception {
        //FIRST TEST
        //Expected result: login and password passes checking.
        System.out.println("addNewUser [TEST #1]");
        String login = "avocadolover";
        String pw = "myavoc4d0";
        assertEquals(true, instance.addNewUser(login, pw));
        
        //SECOND TEST
        //Expected result: EXCEPTION: login is empty
        System.out.println("addNewUser [TEST #2]");
        login = "";
        pw = "myavoc4d0";
        try{
            instance.addNewUser(login, pw);
            fail("Expected a LoginException to be thrown.");
        } catch(LoginException e) {
            assertThat(e.getMessage(), is("[INVALID LOGIN]: Login must have up to 20 characters."));
        }
        
        //THIRD TEST
        //Expected result: EXCEPTION: login has more than 20 characters
        System.out.println("addNewUser [TEST #3]");
        login = "lalalappapqweqwqelala";
        try{
            instance.addNewUser(login, pw);
            fail("Expected a LoginException to be thrown.");
        } catch(LoginException e) {
            assertThat(e.getMessage(), is("[INVALID LOGIN]: Login must have up to 20 characters."));
        }
        
        //FOURTH TEST
        //Expected result: EXCEPTION: login contains numbers
        System.out.println("addNewUser [TEST #4]");
        login = "4v0c4d0mst";
        try{
            instance.addNewUser(login, pw);
            fail("Expected a LoginException to be thrown.");
        } catch(LoginException e) {
            assertThat(e.getMessage(), is("[INVALID LOGIN]: Login must not have numbers"));
        }
        
        //FIFTH TEST
        //Expected result: EXCEPTION: number of characters in password is below 8
        System.out.println("addNewUser [TEST #5]");
        login = "elavocado";
        pw = "my4v0c4";
        try{
            instance.addNewUser(login, pw);
            fail("Expected a PasswordException to be thrown.");
        } catch(PasswordException e) {
            assertThat(e.getMessage(), is("[INVALID PASSWORD]: Password must have between 8 and 12 characters."));
        }
        
        //SIXTH TEST
        //Expected result: EXCEPTION: number of characters in password is above 12
        System.out.println("addNewUser [TEST #6]");
        pw = "my4v0c4d0plays";
        try{
            instance.addNewUser(login, pw);
            fail("Expected a PasswordException to be thrown.");
        } catch(PasswordException e) {
            assertThat(e.getMessage(), is("[INVALID PASSWORD]: Password must have between 8 and 12 characters."));
        }
        
        //SEVENTH TEST
        //Expected result: EXCEPTION: password is empty
        System.out.println("addNewUser [TEST #7]");
        pw = "";
        try{
            instance.addNewUser(login, pw);
            fail("Expected a PasswordException to be thrown.");
        } catch(PasswordException e) {
            assertThat(e.getMessage(), is("[INVALID PASSWORD]: Password must have between 8 and 12 characters."));
        }
        
        //EIGHTH TEST
        //Expected result: EXCEPTION: password contains only letters
        System.out.println("addNewUser [TEST #8]");
        pw = "avocadoplay";
        try{
            instance.addNewUser(login, pw);
            fail("Expected a PasswordException to be thrown.");
        } catch(PasswordException e) {
            assertThat(e.getMessage(), is("[INVALID PASSWORD]: Password must contain at least 2 numbers."));
        }
        
        //NINTH TEST
        //Expected result: EXCEPTION: password contains only one number.
        System.out.println("addNewUser [TEST #9]");
        pw = "avocad0pla";
        System.out.println(pw.length());
        try{
            instance.addNewUser(login, pw);
            fail("Expected a PasswordException to be thrown.");
        } catch(PasswordException e) {
            assertThat(e.getMessage(), is("[INVALID PASSWORD]: Password must contain at least 2 numbers."));
        }
        
        //TENTH TEST
        //Expected result: PASS with 11 characters
        System.out.println("addNewUser [TEST #10]");
        pw = "avoc4dopl4y";
        assertEquals(true, instance.addNewUser(login, pw));
        
        //ELEVENTH TEST
        //Expected result: PASS with 9 characters
        System.out.println("addNewUser [TEST #11]");
        login = "elavocados";
        pw = "oc4ol4yre";
        assertEquals(true, instance.addNewUser(login, pw));
    }

    /**
     * Test of deleteUser method, of class FacadeManager.
     */
    @Test
    public void testDeleteUser() throws Exception {
        //TEST 1
        //Expected result: user successfully deleted
        System.out.println("deleteUser [TEST #1]");
        String login = "avocadolover";
        assertEquals(true, instance.deleteUser(login));
        
        //TEST 2
        //Expected result: [EXCEPTION] empty login
        System.out.println("deleteUser [TEST #2]");
        login = "";
        try{
            instance.deleteUser(login);
            fail("Expected a LoginException to be thrown.");
        } catch (LoginException e) {
            assertThat(e.getMessage(), is("[INVALID LOGIN]: Login must have up to 20 characters."));
        }
        
        //TEST 3
        //Expected result: [EXCEPTION] login exceeds 20 characters
        System.out.println("deleteUser [TEST #3]");
        login = "pikotaropenpineapplep";
        try{
            instance.deleteUser(login);
            fail("Expected a LoginException to be thrown.");
        } catch (LoginException e) {
            assertThat(e.getMessage(), is("[INVALID LOGIN]: Login must have up to 20 characters."));
        }
        
        //TEST 4
        //Expected result:[EXCEPTION] login has numbers
        System.out.println("deleteUser [TEST #4]");
        login = "ppappp4ppp12";
        try{
            instance.deleteUser(login);
            fail("Expected a LoginException to be thrown.");
        } catch (LoginException e) {
            assertThat(e.getMessage(), is("[INVALID LOGIN]: Login must not have numbers"));
        }
        
        //TEST 5
        //Expected result: [EXCEPTION] User Not Found
        System.out.println("deleteUser [TEST #5]");
        login = "ppapppap";
        try{
            instance.deleteUser(login);
            fail("Expected a LoginException to be thrown.");
        } catch (LoginException e) {
            assertThat(e.getMessage(), is("[INVALID LOGIN]: User not found."));
        }
        
    }

    /**
     * Test of search method, of class FacadeManager.
     */
    @Test
    public void testSearch() {
        //TEST 1
        //Expected result: user found
        System.out.println("search [TEST #1]");
        String login = "elavocados";
        assertEquals("elavocados", instance.search(login));
        
        //TEST 2
        //Expected result: user not found
        System.out.println("search [TEST #2]");
        login = "ppapppap";
        assertEquals(null, instance.search(login));
    }

    /**
     * Test of getUsers method, of class FacadeManager.
     */
    @Test
    public void testGetUsers() throws Exception {
        System.out.println("getUsers");
        Map<String, String> expResult = null;
        Map<String, String> result = instance.getUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    public void testGetInstance() {
        System.out.println("getInstance");
        ControleDeUsuario userMng = null;
        ControleFacadeSingle expResult = null;
        ControleFacadeSingle result = ControleFacadeSingle.getInstance(userMng);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    private void addNewUser(String login, String pw) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
