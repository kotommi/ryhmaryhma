package vinkkeri;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import java.util.concurrent.TimeoutException;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxRobotException;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import vinkkeri.database.SQLiteTagDao;
import vinkkeri.database.SQLiteTipDao;
import vinkkeri.main.Main;
import vinkkeri.objects.Tip;

public class Stepdefs extends ApplicationTest {

    static {
        tagDao = new SQLiteTagDao("jdbc:sqlite:database.db");
        tipDao = new SQLiteTipDao("jdbc:sqlite:database.db");
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
        System.setProperty("prism.order", "sw");
        System.setProperty("prism.text", "t2k");
        System.setProperty("java.awt.headless", "true");

        try {
            ApplicationTest.launch(Main.class);
        } catch (Exception e) {
            // oh no
        }
    }

    static SQLiteTipDao tipDao;
    static SQLiteTagDao tagDao;

    @Override
    public void start(Stage stage) {
        stage.show();
    }

    public <T extends Node> T find(final String query) {
        return (T) lookup(query).queryAll().iterator().next();
    }

    public void type(String str) {
        String split[] = str.split("");
        for (String s : split) {
            super.type(KeyCode.getKeyCode(s));
        }
    }

    // Given When and then -----------------------------------------------------
    @Given("^add tip is clicked$")
    public void given_add_tip_is_clicked() {
        clickOn("#addTip");
    }

    @Given("^listing view is visible$")
    public void listing_view_visible() {

    }

    @When("^add tip button is clicked$")
    public void given_add_tip_button_is_clicked() {
        clickOn("#addTip");
    }

    @When("title text area is clicked$")
    public void title_text_area_is_clicked() {
        clickOn("#titleField");
    }

    @When("url text area is clicked$")
    public void url_text_area_is_clicked() {
        clickOn("#urlField");
    }

    @When("tag text area is clicked$")
    public void tag_text_area_is_clicked() {
        
        clickOn("#tagField");
    }

    @When("^text \"([^\"]*)\" is entered$")
    public void text_is_entered(String text) {
        type(text.toUpperCase());
    }

    @When("^add button is clicked$")
    public void add_button_is_clicked() {
        clickOn("#addButton");
    }

    @When("^back button is clicked$")
    public void back_button_is_clicked() {
        clickOn("#backButton");
    }

    @When("^text \"([^\"]*)\" is clicked$")
    public void text_button_is_clicked(String text) {
        clickOn(text);
    }

    @When("^delete is clicked$")
    public void delete_is_clicked() {
        clickOn("#deleteTip");
    }

    @Then("^new tip with title \"([^\"]*)\" and url \"([^\"]*)\" and tags \"([^\"]*)\" is stored in the program$")
    public void new_tip_with_title_is_stored(String title, String url, String tags) {
        List<Tip> tips = tipDao.getTips();
        boolean exists = false;
        Tip safety = null;
        for (Tip tip : tips) {
            if (tip.getTitle().equals(title)) {
                safety = tip;
                exists = true;
                break;
            }
        }
        if (exists) {
            tipDao.remove(tipDao.getNewestID());
        }
        assertTrue(exists);
        assertTrue(safety.getUrl().equals(url));
        for (String tag : tags.split(",")) {
            assertTrue(safety.getTags().contains(tag));
        }
        clickOn("#backButton");
    }

    @Then("add view is visible")
    public void add_view_is_visible() {
        find("#titleField");
        assertTrue(true);
        clickOn("#backButton");
    }

    @Then("^a tip with title \"([^\"]*)\" is not stored in the program$")
    public void tip_with_title_is_not_stored(String title) {
        verifyThat(title, NodeMatchers.isNull());
    }

    // After -------------------------------------------------------------------
    @After
    public void afterTest() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

}
