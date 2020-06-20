package com.yannick.components;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Popup extends Stage {
    private Text message = new Text();
    private Button button = new Button("OK");

    /**
     * <p>Custom popup window which extends {@link Stage}.  Has a Vbox with a centered alignment and a fixed with of 300 and height of 200.
     * Window has priority over other stage
     * </p>
     *
     * @param title          The title of the stage
     * @param messageContent The message to display on the popup
     * @param buttonText     The text of the button. Can be whatever you want but default = "OK".
     */
    public Popup(String title, String messageContent, String buttonText) {
        this.setTitle(title);
        message.setText(messageContent);
        button.setText(buttonText);
        this.setResizable(false);
        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);

        VBox dialogBox = new VBox(10);
        dialogBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(dialogBox, 300, 200);
        this.setScene(scene);

        scene.getStylesheets().add("com/yannick/FX/popup.css");
        dialogBox.getChildren().addAll(message, button);

    }

    /**
     * Constructor for popup without specific button text. Uses the default value.
     *
     * @param title          The title for this popup.
     * @param messageContent The message to be displayed.
     */
    public Popup(String title, String messageContent) {
        this.setTitle(title);
        message.setText(messageContent);
        this.setResizable(false);
        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);

        VBox dialogBox = new VBox(10);
        dialogBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(dialogBox, 300, 200);
        this.setScene(scene);

        scene.getStylesheets().add("com/yannick/FX/popup.css");
        dialogBox.getChildren().addAll(message, button);

    }


}
