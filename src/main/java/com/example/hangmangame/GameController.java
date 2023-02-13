package com.example.hangmangame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController extends HelloController{

    @FXML
    private Label hiddenPassLabel,infoLabel,usedLettersLabel;
    @FXML
    private TextField letterField;
    @FXML
    private ImageView hangmanView = new ImageView();
    @FXML
    private Button tryAgainButton;


    private final List<String> passwords = getPasswordFromFile("src/main/resources/com/example/hangmangame/wisielec/hasla.txt");
    private final StringBuilder password = drawPassword(passwords);
    private StringBuilder hiddenPassword = hidePassword(password);
    private char letter;
    private List<Character> usedLetters = new ArrayList<Character>();
    private int count = 0;

    ////////////////////////////////
//    Image image = new Image("C:\\Users\\Norbert\\hangmanGame\\src\\main\\resources\\com\\example\\hangmangame\\images\\0.png");
    Image image0 = new Image("C:\\Users\\Norbert\\hangmanGame\\src\\main\\resources\\com\\example\\hangmangame\\images\\0.png");
    Image image1 = new Image("C:\\Users\\Norbert\\hangmanGame\\src\\main\\resources\\com\\example\\hangmangame\\images\\1.png");
    Image image2 = new Image("C:\\Users\\Norbert\\hangmanGame\\src\\main\\resources\\com\\example\\hangmangame\\images\\2.png");
    Image image3 = new Image("C:\\Users\\Norbert\\hangmanGame\\src\\main\\resources\\com\\example\\hangmangame\\images\\3.png");
    Image image4 = new Image("C:\\Users\\Norbert\\hangmanGame\\src\\main\\resources\\com\\example\\hangmangame\\images\\4.png");
    Image image5 = new Image("C:\\Users\\Norbert\\hangmanGame\\src\\main\\resources\\com\\example\\hangmangame\\images\\5.png");
    Image image6 = new Image("C:\\Users\\Norbert\\hangmanGame\\src\\main\\resources\\com\\example\\hangmangame\\images\\6.png");
    Image image7 = new Image("C:\\Users\\Norbert\\hangmanGame\\src\\main\\resources\\com\\example\\hangmangame\\images\\7.png");
    Image image8 = new Image("C:\\Users\\Norbert\\hangmanGame\\src\\main\\resources\\com\\example\\hangmangame\\images\\8.png");
    /////////////////////////////////

    @Override
    public void backToMenu(ActionEvent event) throws IOException {
        super.backToMenu(event);
    }

    @Override
    public void openNewGame(ActionEvent event) throws IOException {
        super.openNewGame(event);
    }

    public static ArrayList<String> getPasswordFromFile(String fileName){


        ArrayList<String> passwords = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = br.readLine()) != null){
                passwords.add(line);
            }
        } catch (IOException e){
            System.out.println("Błąd odczytu pliku: " + e.getMessage());

        }

        return passwords;
    }
    public StringBuilder drawPassword(List<String> passwords){
        StringBuilder pass = new StringBuilder();
        int rand = (int)(Math.random() * passwords.size());
        pass.append(passwords.get(rand));
        System.out.println(pass);
        return pass;
    }

    public StringBuilder hidePassword(StringBuilder password) {
        StringBuilder hiddenPassword = new StringBuilder();
        hiddenPassword.append("_".repeat(Math.max(0, password.length())));
        return hiddenPassword;
    }
    public void showCurrentPassword(){
        hiddenPassLabel.setText(String.valueOf(hiddenPassword));
    }
    public void checkLetter(){
        StringBuilder key = new StringBuilder();
        try {
            key.append(letterField.getText().toUpperCase());
            letter = key.charAt(0);
            System.out.println(letter);
            letterField.clear();
        }catch (RuntimeException e){
            System.out.println("Prosze podac litere");
            count--; // nie zabiera zycia kiedy nie podamy literki
        }
        usedLetters.add(letter);
        usedLettersLabel.setText(usedLetters.toString());
        boolean isCorrect = false;
        for(int i = 0; i < password.length(); i++){
            if(letter == password.charAt(i)){
                hiddenPassword.setCharAt(i,letter);
                isCorrect = true;
                showCurrentPassword();
                infoLabel.setText("Poprawna litera! :)");
            }

        }
        //System.out.println(hiddenPassword + " -> " + password);
        if(hiddenPassword.toString().equals(password.toString())){
            System.out.println("Wygrałeś");
            infoLabel.setTextFill(Color.GREEN);
            infoLabel.setText("Wygrana! :)");

            letterField.setEditable(false);
            tryAgainButton.setDisable(false);
            tryAgainButton.setOpacity(1);
        }
        if(!isCorrect){
            count++;
            System.out.println(count);
            displayView(count);
            infoLabel.setText("Niepoprawna litera! :(");
        }
        if(count == 8){
            System.out.println("Przegrales");
            infoLabel.setTextFill(Color.RED);
            infoLabel.setText("PRZEGRANA! :(");
            letterField.setEditable(false);
            tryAgainButton.setDisable(false);
            tryAgainButton.setOpacity(1);

        }

    }
    public void displayView(int count){
        if(count == 0)
            hangmanView.setImage(image0);
        else if(count == 1)
            hangmanView.setImage(image1);
        else if(count == 2)
            hangmanView.setImage(image2);
        else if(count == 3)
            hangmanView.setImage(image3);
        else if(count == 4)
            hangmanView.setImage(image4);
        else if(count == 5)
            hangmanView.setImage(image5);
        else if(count == 6)
            hangmanView.setImage(image6);
        else if(count == 7)
            hangmanView.setImage(image7);
        else if(count == 8)
            hangmanView.setImage(image8);

    }
}
