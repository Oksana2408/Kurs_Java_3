package com.geeckbrains.chat.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TextArea textArea;

    @FXML
    TextField textField, loginField;

    @FXML
    Button btnSend;

    @FXML
    PasswordField passField;

    @FXML
    HBox LoginBox;

    @FXML
    ListView <String> clientsList;

    private Network network;
    private boolean authenticated;
    private String nickname;
    private File file;

    public void setAuthenticated(boolean authenticated){
        this.authenticated = authenticated;
        LoginBox.setVisible(!authenticated); //если не аутентифицирован то видит логинбокс
        LoginBox.setManaged(!authenticated);
        textField.setVisible(authenticated); //если аутентифицирован то видит полее ввода текста
        textField.setManaged(authenticated);
        btnSend.setVisible((authenticated));
        btnSend.setManaged(authenticated);
        clientsList.setVisible(authenticated);
        clientsList.setManaged(authenticated);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { //для загрузки сцены
        setAuthenticated(false); //не аутентифицирован
        clientsList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) { //метод для добавления текста в поле при клике на строчку в списке
                if(event.getClickCount()==2){
                    textField.setText("/w " + clientsList.getSelectionModel().getSelectedItem() +" ");
                    textField.requestFocus();
                    textField.selectEnd();
                }
            }
        });
    }

    public void tryToConnect(){
        try {
            if(network != null && network.isConnected()){
                return;
            }
            setAuthenticated(false);
            network = new Network(8189);
            Thread t = new Thread(() -> {
                try {
                    while (true) {
                        String msg = network.readMsg(); //чтение сообщения
                        if(msg.startsWith("/authok ")){
                            nickname = msg.split(" ")[1]; //аутентифицируемся
                            textArea.appendText("Вы зашли в чат под ником " + nickname +"\n");
                            setAuthenticated(true); //аутентификация прошла успешно
                            File file = new File("history_" + nickname + ".txt");
                            file.createNewFile();
                            break;
                        }
                        FileWriter writer = new FileWriter(file, true);
                        writer.write(msg + "\n");
                        writer.flush();
                        writer.close();
                        textArea.appendText(msg + "\n");


                    }while (true) {
                        String msg = network.readMsg(); //чтение сообщения
                        if (msg.startsWith("/")) {
                            if (msg.equals("/end_confirm")) {
                                textArea.appendText("Завершено общение с сервером\n");
                                break;
                            }if (msg.startsWith("/set_nick_to")) {
                                nickname = msg.split(" ")[1]; //аутентифицируемся
                                textArea.appendText("Ваш новый ник:  " + nickname +"\n");
                                continue;
                            }
                            if(msg.startsWith("/clients_list")){
                                Platform.runLater(() ->{
                                    clientsList.getItems().clear();
                                    String [] tokens = msg.split(" ");
                                    for (int i = 1; i < tokens.length ; i++) {
                                        if(!nickname.equals(tokens[i])){
                                            clientsList.getItems().add(tokens[i]);
                                        }
                                    }
                                });
                            }
                        } else {
                            textArea.appendText(msg + "\n");
                        }
                    }
                }catch (IOException e) {
                    Platform.runLater(() ->{ //при изменении интерфейса из потока.
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Соединение с сервером разорвано", ButtonType.OK);
                        alert.showAndWait();
                    });
                }finally {
                    network.close();
                    //Platform.exit(); //закрыть окно приложения
                    setAuthenticated(false); //дает возможность не закрывать окно, а лишь переподключиться
                    nickname = null;
                }
            });
            t.setDaemon(true); //для того чтобы поток завершался при закрытии окна через крестик.
            t.start();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Невозможно подключиться к серверу", ButtonType.OK);
            alert.showAndWait();

        }
    }

    public void sendMsg(javafx.event.ActionEvent actionEvent) {
        try {
            network.sendMsg(textField.getText());
            textField.clear();
            textField.requestFocus();
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Не удалось отправить сообщние, проверьте сетевое подключение", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void tryToAuth(ActionEvent actionEvent) {
        try {
            tryToConnect();
            network.sendMsg("/auth " + loginField.getText() + " " + passField.getText());
            loginField.clear();
            passField.clear();
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Не удалось отправить сообщние, проверьте сетевое подключение", ButtonType.OK);
            alert.showAndWait();
        }
    }
}
