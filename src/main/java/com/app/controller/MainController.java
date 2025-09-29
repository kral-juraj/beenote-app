package com.app.controller;

import com.app.model.Beekeeper;
import com.app.model.Taxacia;
import com.app.service.BeekeeperServiceImpl;
import com.app.service.I18nServiceImpl;
import com.app.service.TaxaciaServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Optional;

public class MainController {
    @FXML private TabPane tabPane;
    @FXML private TableView<Beekeeper> tableBeekeeper;
    @FXML private TableColumn<Beekeeper, String> colFirstName;
    @FXML private TableColumn<Beekeeper, String> colLastName;
    @FXML private TextArea detailsArea;

    @FXML private TableView<Taxacia> tableTaxacia;
    @FXML private TableColumn<Taxacia, String> colDatum;
    @FXML private TableColumn<Taxacia, String> colUl;

    @FXML private ComboBox<String> comboLang;

    @FXML private Button addBeekeeperButton;
    @FXML private Button showDetailsButton;
    @FXML private Button deleteBeekeeperButton;

    @FXML private Button addTaxationButton;
    @FXML private Button showTaxationButton;
    @FXML private Button deleteTaxationButton;

    private final BeekeeperServiceImpl beekeeperService = new BeekeeperServiceImpl();
    private final TaxaciaServiceImpl taxationService = new TaxaciaServiceImpl();
    private final I18nServiceImpl i18n = new I18nServiceImpl();

    @FXML
    public void initialize() {
        // load default locale EN
        i18n.load("EN");
        setupLangCombo();
        colFirstName.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFirstName()));
        colLastName.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getLastName()));
        colDatum.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDatum()));
        colUl.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getUl()));
        refreshBeek();
        refreshTax();
        applyTranslations();
    }

    private void setupLangCombo() {
        comboLang.getItems().setAll("EN","SK");
        comboLang.setValue("EN");
        comboLang.setOnAction(e -> {
            String val = comboLang.getValue();
            i18n.load(val);
            applyTranslations();
        });
    }

    private void applyTranslations() {
        // set texts using translation keys
        Tab t1 = tabPane.getTabs().get(0);
        t1.setText(i18n.getKey("tab.beekeeper"));

        Tab t2 = tabPane.getTabs().get(1);
        t2.setText(i18n.getKey("tab.taxation"));

        // update buttons inside toolbars (assumes structure)
        ToolBar tb1 = (ToolBar) tabPane.getTabs().get(0).getContent();
        addBeekeeperButton.setText(i18n.getKey("btn.addBeekeeper"));
        showDetailsButton.setText(i18n.getKey("btn.showBeekeeper"));
        deleteBeekeeperButton.setText(i18n.getKey("btn.deleteBeekeeper"));

        ToolBar tb2 = (ToolBar) tabPane.getTabs().get(1).getContent();
        addTaxationButton.setText(i18n.getKey("btn.addTaxation"));
        deleteTaxationButton.setText(i18n.getKey("btn.deleteTaxation"));
        showTaxationButton.setText(i18n.getKey("btn.showTaxation"));

    }

    private void refreshBeek() {
        tableBeekeeper.getItems().setAll(beekeeperService.getAll());
    }

    private void refreshTax() {
        tableTaxacia.getItems().setAll(taxationService.getAll());
    }

    @FXML
    private void showDetails() {
        Beekeeper selected = tableBeekeeper.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Beekeeper full = beekeeperService.getDetails(selected.getId());
            detailsArea.setText(
                "ID: " + full.getId() + "\n" +
                i18n.getKey("label.firstName") + ": " + full.getFirstName() + "\n" +
                i18n.getKey("label.lastName") + ": " + full.getLastName() + "\n" +
                i18n.getKey("label.address") + ": " + full.getAddress() + "\n" +
                i18n.getKey("label.hobby") + ": " + full.getHobby()
            );
        }
    }

    @FXML
    private void addBeekeeper() {
        Dialog<Pair<String,String>> dialog = new Dialog<>();
        dialog.setTitle(i18n.getKey("dlg.addBeekeeper.title"));
        ButtonType addButtonType = new ButtonType(i18n.getKey("btn.add"), ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        TextField first = new TextField();
        first.setPromptText(i18n.getKey("label.firstName"));
        TextField last = new TextField();
        last.setPromptText(i18n.getKey("label.lastName"));
        TextField addr = new TextField();
        addr.setPromptText(i18n.getKey("label.address"));
        TextField hobby = new TextField();
        hobby.setPromptText(i18n.getKey("label.hobby"));

        grid.add(new Label(i18n.getKey("label.firstName")), 0, 0);
        grid.add(first, 1, 0);
        grid.add(new Label(i18n.getKey("label.lastName")), 0, 1);
        grid.add(last, 1, 1);
        grid.add(new Label(i18n.getKey("label.address")), 0, 2);
        grid.add(addr, 1, 2);
        grid.add(new Label(i18n.getKey("label.hobby")), 0, 3);
        grid.add(hobby, 1, 3);

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Pair<>(first.getText(), last.getText());
            }
            return null;
        });

        Optional<Pair<String,String>> result = dialog.showAndWait();
        result.ifPresent(pair -> {
            beekeeperService.add(pair.getKey(), pair.getValue(), addr.getText(), hobby.getText());
            refreshBeek();
        });
    }
    @FXML
    private void deleteBeekeeper() {

    }

    @FXML
    private void addTaxacia() {
        Dialog<Pair<String,String>> dialog = new Dialog<>();
        dialog.setTitle(i18n.getKey("dlg.addTaxation.title"));
        ButtonType addButtonType = new ButtonType(i18n.getKey("btn.add"), ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        TextField datum = new TextField();
        datum.setPromptText(i18n.getKey("label.datum"));
        TextField ul = new TextField();
        ul.setPromptText(i18n.getKey("label.ul"));
        TextField poz = new TextField();
        poz.setPromptText(i18n.getKey("label.poznamky"));

        grid.add(new Label(i18n.getKey("label.datum")), 0, 0);
        grid.add(datum, 1, 0);
        grid.add(new Label(i18n.getKey("label.ul")), 0, 1);
        grid.add(ul, 1, 1);
        grid.add(new Label(i18n.getKey("label.poznamky")), 0, 2);
        grid.add(poz, 1, 2);

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Pair<>(datum.getText(), ul.getText());
            }
            return null;
        });

        Optional<Pair<String,String>> result = dialog.showAndWait();
        result.ifPresent(pair -> {
            taxationService.add(datum.getText(), ul.getText(), poz.getText());
            refreshTax();
        });
    }

    @FXML
    private void showTaxation() {

    }

    @FXML
    private void deleteTaxation() {

    }
}
