package com.example.hangmangame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField addWordField;
    @FXML
    private Label addWordInfoLabel;

    public void openNewGame(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("new-game.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void backToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addWord(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-word.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void addWordFunction(ActionEvent event){
        StringBuilder newWord = new StringBuilder();
        boolean tryAgain = true;
        while (tryAgain){
        newWord.append(addWordField.getText());
        for(int i = 0; i < newWord.length(); i++){
            if((newWord.charAt(i) <= 64) || (newWord.charAt(i) >= 91 && newWord.charAt(i) <= 96) || (newWord.charAt(i) >= 123 && newWord.charAt(i) <= 127) ){
                addWordInfoLabel.setText("Słowo musi składać się wyłącznie z liter, bez spacji i znaków specjalnych! (np. !@#$%-) ");
            }
            else {
                tryAgain = false;
            }
        }}
        String newWordUpper = newWord.toString().toUpperCase();
        try {
            FileWriter writer = new FileWriter("src/main/resources/com/example/hangmangame/wisielec/hasla.txt", true);
            writer.write(newWordUpper + System.lineSeparator());
            writer.close();
            System.out.println("Hasło zostało dodane do pliku.");
            addWordInfoLabel.setText("HASŁO DODANO POMYSLNIE! MOŻESZ ZACZĄĆ GRE! ");
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas dodawania hasła do pliku.");
            addWordInfoLabel.setText("Wystąpił błąd podczas dodawania hasła do pliku.");
            e.printStackTrace();
        }
        //0-64
        //91-96
        //123-127

    }


}