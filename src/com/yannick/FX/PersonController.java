package com.yannick.FX;

import com.jfoenix.controls.JFXTextField;
import com.yannick.IO.PersonIO;
import com.yannick.Person.Person;
import com.yannick.components.Popup;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class PersonController {
    /**
     * The Txt name.
     */
    @FXML
    JFXTextField txtName,
    /**
     * The Txt lastname.
     */
    txtLastname,
    /**
     * The Txt email.
     */
    txtEmail,
    /**
     * The Txt gender.
     */
    txtGender,
    /**
     * The Txt profession.
     */
    txtProfession;
    private List<JFXTextField> textFieldList = new ArrayList<>();
    /**
     * X or Y Offset for dragging custom bar
     */
    private double xOffset, yOffset;
    @FXML
    private Label lblErrorText;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Button btnSaveNewPerson, btnRetrievePerson, btnClearAllFields;


    /**
     * Initialize. Sets all the handlers for the buttons.
     */
    public void initialize() {
        btnRetrievePerson.setOnAction(event -> retrievePersonButtonHandler());
        btnClearAllFields.setOnAction(event -> clearAllFieldsButtonHandler());
        btnSaveNewPerson.setOnAction(event -> saveNewPersonButtonHandler());
        menuBar.setOnMouseDragged(event -> draggableMenu());

    }

    /**
     * Button handler to save a person to the data file. Validates the fields before being able to press submit.
     */

    @FXML
    private void saveNewPersonButtonHandler() {
        addFieldsToList(textFieldList);
        if (validateFields()) {
            Person p = new Person(txtName.getText(), txtLastname.getText(), txtEmail.getText(), txtGender.getText(), txtProfession.getText());
            try {

                lblErrorText.setText("");
                PersonIO.addPersonToFile(p);
                lblErrorText.setText("Succesfully written - " + p.getName() + " to file.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            lblErrorText.setText("Please fill out all the fields!");
        }
    }

    /**
     * <p>Button handler to retrieve the last person from the file. If no person is found in the file it calls the
     * {@link PersonController#noPreviousPersonFound()} method.
     * </p>
     */

    @FXML
    private void retrievePersonButtonHandler() {
        Person p = PersonIO.retrieveLastPersonFromFile();
        try {
            lblErrorText.setText("");
            txtName.setText(p.getName());
            txtLastname.setText(p.getLastName());
            txtEmail.setText(p.getEmail());
            txtGender.setText(p.getGender());
            txtProfession.setText(p.getProfession());
        } catch (Exception e) {
            noPreviousPersonFound();
        }
    }

    /**
     * Label error text for when a person isn't found but the file exists. ( Empty file )
     */
    private void noPreviousPersonFound() {
        lblErrorText.setText("Error: No person found");
    }

    /**
     * Closes the application
     */
    @FXML
    private void close() {
        Platform.exit();
    }

    /**
     * Clears the data from the file without deleting it.
     *
     * @throws FileNotFoundException
     */
    @FXML
    private void clearDataFile() throws FileNotFoundException {
        Popup pw = new Popup("Data file cleared", "The file contents have been cleared");
        pw.getButton().setOnAction(event -> {
            pw.hide();
        });

        pw.show();
        PersonIO.clearPersonFromFile();
    }


    /**
     * Method for custom draggable menu.
     */
    @FXML
    private void draggableMenu() {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        menuBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        menuBar.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

    }

    /**
     * Clears all fields on the GUI.
     */
    @FXML
    public void clearAllFieldsButtonHandler() {
        addFieldsToList(textFieldList);
        for (JFXTextField jf : textFieldList) {
            jf.setText("");
        }
        lblErrorText.setText("Cleared all fields!");
    }

    /**
     * Adds all the text fields to a list so it can easily be traversed for checking validation.
     *
     * @param list
     */
    private void addFieldsToList(List<JFXTextField> list) {
        this.textFieldList = list;
        list.add(txtName);
        list.add(txtLastname);
        list.add(txtGender);
        list.add(txtProfession);
        list.add(txtEmail);
    }

    /**
     * About.
     * Links to my github page.
     *
     * @throws URISyntaxException the uri syntax exception
     * @throws IOException        the io exception
     */
    public void about() throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(new URI("https://github.com/Yannick-Driessens"));
        }
    }

    /**
     * Validate the fields. If one of them is empty it'll end and update the {@link PersonController#lblErrorText} text field.
     *
     * @return true if all fields are filled. False if one of them isn't filled out.
     */
    private boolean validateFields() {
        for (JFXTextField jf : textFieldList) {
            if (!checkIfFieldNotEmpty(jf)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generic method to check if the method isn't used. Helped method for {@link PersonController#validateFields()} method.
     *
     * @param jfxTextField takes the list {@link PersonController#textFieldList} as a paramater
     * @return true if the fields are not empty. False if they are.
     */
    private boolean checkIfFieldNotEmpty(JFXTextField jfxTextField) {
        return jfxTextField.getText() != null && !jfxTextField.getText().trim().isEmpty();
    }

}
