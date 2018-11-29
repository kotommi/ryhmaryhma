package vinkkeri.ui.gui.components;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import vinkkeri.objects.Tip;
import vinkkeri.ui.gui.Display;

public class TipListBox extends VBox {

    private TableView table;

    public TipListBox(ListView lv) {
        //Keskelle sijoittuva listausnäkymä
        lv.tipsList.setEditable(true);
        Label lblHeader = new Label("Tip list");
        ArrayList<TableColumn> columns = new ArrayList<>();

        TableColumn id = new TableColumn("id");
        id.setCellValueFactory(new PropertyValueFactory<Tip, String>("id"));
        columns.add(id);

        TableColumn type = new TableColumn("type");
        type.setCellValueFactory(new PropertyValueFactory<Tip, String>("type"));
        columns.add(type);

        TableColumn title = new TableColumn("Title");
        title.setCellValueFactory(new PropertyValueFactory<Tip, String>("title"));
        columns.add(title);

        TableColumn author = new TableColumn("Author");
        author.setCellValueFactory(new PropertyValueFactory<Tip, String>("author"));
        columns.add(author);

        TableColumn url = new TableColumn("URL");
        url.setCellValueFactory(new PropertyValueFactory<Tip, String>("url"));
        columns.add(url);

        TableColumn isbn = new TableColumn("ISBN");
        isbn.setCellValueFactory(new PropertyValueFactory<Tip, String>("isbn"));
        columns.add(isbn);

        TableColumn tags = new TableColumn("Tags");
        tags.setCellValueFactory(new PropertyValueFactory<Tip, String>("tags"));
        columns.add(tags);

        TableColumn comments = new TableColumn("Comments");
        comments.setCellValueFactory(new PropertyValueFactory<Tip, String>("summary"));
        columns.add(comments);

        TableColumn read = new TableColumn("Read");
        read.setCellValueFactory(new PropertyValueFactory<Tip, String>("read"));
        columns.add(read);

        TableColumn date = new TableColumn("Date");
        date.setCellValueFactory(new PropertyValueFactory<Tip, String>("date"));
        columns.add(date);

        lv.tipsList.getColumns().addAll(columns);

        Button addTipButton = new Button("Add Tip");
        addTipButton.setId("addTip");
        addTipButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Display.setScene("add");
            }
        });

        Button flipReadButton = new Button("Flip Read");
        flipReadButton.setId("flipRead");
        flipReadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lv.tipsList.getSelectionModel().getSelectedItems().stream()
                        .forEach(tip -> {
                            Tip t = (Tip) tip;
                            t.setRead(!t.isRead());
                            lv.display.getController().markRead(t.isRead(), t.getId());
                            lv.tipsList.getItems().clear();
                            Display.refresh();
                        });
            }
        });

        Button removeTipButton = new Button("Delete Tip");
        removeTipButton.setId("deleteTip");
        removeTipButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lv.tipsList.getSelectionModel().getSelectedItems().stream()
                        .forEach(tip -> {
                            lv.display.getController().removeTip((Tip) tip);
                            lv.tipsList.getItems().remove(tip);
                            lv.display.getController().removeTags(((Tip) tip).getTags());
                        });
            }
        });

        setSpacing(5);
        setPadding(new Insets(10, 10, 10, 10));
        getChildren().addAll(lblHeader, lv.tipsList, addTipButton, flipReadButton, removeTipButton);

        this.table = lv.tipsList;
    }
}
