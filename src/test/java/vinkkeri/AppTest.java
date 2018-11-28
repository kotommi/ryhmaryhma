/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinkkeri;

import java.util.concurrent.TimeoutException;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import vinkkeri.main.Main;

/**
 *
 * @author Olli K. Kärki
 */
public class AppTest extends ApplicationTest {

    @Before
    public void setUp() throws Exception {
        ApplicationTest.launch(Main.class);
    }
    
    @Override
    public void start(Stage stage) {
        stage.show();
    }
    
    public <T extends Node> T find(final String query) {
        return (T) lookup(query).queryAll().iterator().next();
    }
    
    public void type(String str) {
        
    }
    
    @After
    public void afterTest() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

}
