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
public class PopupWindow extends Stage {
    private Text message = new Text();
    private Button button = new Button();


    public PopupWindow(String title, String messageContent, String buttonText) {
        this.setTitle(title);
        this.setResizable(false);
        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);

        VBox dialogBox = new VBox(10);
        dialogBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(dialogBox, 300, 200);
        this.setScene(scene);

        message.setText(messageContent);
        button.setText(buttonText);
        scene.getStylesheets().add("com/yannick/FX/popup.css");
        dialogBox.getChildren().addAll(message, button);

    }


}
