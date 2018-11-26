package vinkkeri.ui.gui;

import vinkkeri.ui.gui.components.LabelTextInputControl;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vinkkeri.ui.gui.components.TipButtonBar;

/**
 *
 * @author tixkontt
 */
public class GGui extends Application {

    public final Separator separator = new Separator();
    public TableView tipsList = new TableView();
	public Controller controller = new Controller();

    @Override
    public void start(Stage window) throws Exception {
        separator.setMaxWidth(100.0);
        separator.setHalignment(HPos.LEFT);
        window.setTitle("Vinkkeri");
        //  Luodaan päätason asettelu
        BorderPane layout = new BorderPane();
        layout.setPrefSize(800, 150);

        //Keskelle sijoittuva listausnäkymä
        tipsList.setEditable(true);
        Label lblHeader = new Label("Tip list");
        ArrayList<TableColumn> columns = new ArrayList<>();
        initTableColumns(columns, new String[]{"id", "Type", "Title", "Author", "URL", "ISBN", "Tags", "Comments", "Read", "Date", "Related Courses", "Required Courses"});
        tipsList.getColumns().addAll(columns);

        VBox tipslistArea = new VBox();
        tipslistArea.setSpacing(5);
        tipslistArea.setPadding(new Insets(10, 0, 0, 10));
        tipslistArea.getChildren().addAll(lblHeader, tipsList);
        //vasemman palkin komponentit
        VBox menu = new VBox();
        menu.setMaxHeight(600.00);
        menu.setPadding(new Insets(20, 20, 20, 20));
        menu.setSpacing(10);

        //Luodaan päätason komponentit
        ArrayList<LabelTextInputControl> textItems = new ArrayList<>();
        initMenuItems(textItems, new TextField(), "ID", "Id");
        initMenuItems(textItems, new TextField(), "Url", "Url");
        initMenuItems(textItems, new TextField(), "Title", "Title");
        initMenuItems(textItems, new TextField(), "Author", "Author");
        initMenuItems(textItems, new TextField(), "ISBN", "ISBN");
        initMenuItems(textItems, new TextField(), "Tags", "Tags");
        initMenuItems(textItems, new TextArea(), "Comments", "Comments");

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(textItems.get(0).getLabel());
        nodes.add(textItems.get(0).getField());

        ArrayList<RadioButton> rButtons = new ArrayList<>();
        final ToggleGroup tipTypes = new ToggleGroup();
        initRadioButtons(rButtons, new String[]{"Book", "Podcast", "Video", "Blogpost"}, tipTypes);
        nodes.addAll(rButtons);
        nodes.add(separator);

        //luettu /  ei luettu -komponentit
        final ToggleGroup grReadOrNot = new ToggleGroup();
        Label lblReadOrNot = new Label("Read or Not");
        ArrayList<RadioButton> rButtons2 = new ArrayList<>();
        initRadioButtons(rButtons2, new String[]{"Not Read", "Read"}, grReadOrNot);

        TextField txtDate = new TextField("Date");
        DatePicker datePicker = new DatePicker();

		TipButtonBar toolbar = new TipButtonBar(textItems, rButtons, txtDate, datePicker, rButtons2);

        /*---- luodaan layout ----*/
        //lisätään vasemman palkin komponentit:
        // !!!HUOM!!! 1. komponentti jo lisätty manuaalisesti
        for (int i = 1; i < textItems.size(); i++) {
            nodes.add(textItems.get(i).getLabel());
            nodes.add(textItems.get(i).getField());
        }
        menu.getChildren().addAll(nodes);

//        menu.getChildren().add(rbOther);
        //lisätään lukutiedot
        menu.getChildren().add(lblReadOrNot);
        menu.getChildren().addAll(rButtons2);
        menu.getChildren().add(datePicker);

        // menu.getChildren().addAll(btnNewTip, btnSave, btnDelete);
        layout.setLeft(menu);
        layout.setTop(toolbar);
        layout.setCenter(tipslistArea);

        Scene vinkkeri = new Scene(layout, 1800, 800);

        window.setScene(vinkkeri);
        window.show();
    }

    // Initialize all Table columns
    private void initTableColumns(ArrayList<TableColumn> columns, String[] names) {
        for (String name : names) {
            columns.add(new TableColumn(name));
        }
    }

    // Initialize all Label-TextField pairs
    private void initMenuItems(ArrayList<LabelTextInputControl> items, TextInputControl text, String labelName, String promptText) {
        items.add(new LabelTextInputControl(labelName, text, promptText));
    }

    // Initialize radiobuttons that belong to the same group
    private void initRadioButtons(ArrayList<RadioButton> rbs, String[] names, ToggleGroup toggle) {
        for (String name : names) {
            RadioButton rb = new RadioButton(name);
            rbs.add(rb);
            //Tehdään radiobuttoneista ryhmä
            rb.setToggleGroup(toggle);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
