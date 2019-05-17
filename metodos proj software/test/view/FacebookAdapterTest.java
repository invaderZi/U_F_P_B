/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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
public class FacebookAdapterTest {
    
    public FacebookAdapterTest() {
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
     * Test of vinculateAccount method, of class FacebookAdapter.
     */
    @Test
    public void testVinculateAccount() {
        System.out.println("vinculateAccount");
        String sigaalogin = "";
        String sigaapw = "";
        FacebookAdapter instance = null;
        instance.vinculateAccount(sigaalogin, sigaapw);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyUpdate method, of class FacebookAdapter.
     */
    @Test
    public void testNotifyUpdate() {
        System.out.println("notifyUpdate");
        String usrlogin = "";
        String post = "";
        FacebookAdapter instance = null;
        instance.notifyUpdate(usrlogin, post);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postOnFB method, of class FacebookAdapter.
     */
    @Test
    public void testPostOnFB() {
        System.out.println("postOnFB");
        String login = "";
        String message = "";
        FacebookAdapter instance = null;
        String expResult = "";
        String result = instance.postOnFB(login, message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
