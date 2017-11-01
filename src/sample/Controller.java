package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;


public class Controller {
    @FXML
    private boolean editing;
    @FXML
    private TableView<Person> table;
    @FXML
    private TableColumn<Person, String> name;
    @FXML
    private TableColumn<Person, String> surname;
    @FXML
    TextField nameTf;
    @FXML
    TextField surnameTf;

    ObservableList<Person>items = FXCollections.observableArrayList(new Person("andrey", "melnychenko"),new Person("dana", "kirmasova"));

    @FXML
    private void add(){
        if (editing) {
            table.getSelectionModel().getSelectedItem().set(nameTf.getText(), surnameTf.getText());
            editing = false;
        } else {
            items.add(new Person(nameTf.getText(), surnameTf.getText())); }
        nameTf.clear();
        surnameTf.clear();
        table.refresh();

    }

    @FXML
    public void initialize(){
        table.itemsProperty().setValue(items);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.setRowFactory(param -> {
            TableRow<Person> row = new TableRow<>();
            MenuItem remove = new MenuItem("Удалить");
            MenuItem edit = new MenuItem("Редактировать");
            edit.setOnAction(event -> {
                nameTf.setText(table.getSelectionModel().getSelectedItem().getName());
                surnameTf.setText(table.getSelectionModel().getSelectedItem().getSurName());
                editing = true;
            });
            remove.setOnAction(event -> items.remove(table.getSelectionModel().getSelectedItem()));
            ContextMenu menu = new ContextMenu(remove, edit);
            row.contextMenuProperty().setValue(menu);
            return row;
        });
        name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        surname.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getSurName()));


    }

}
