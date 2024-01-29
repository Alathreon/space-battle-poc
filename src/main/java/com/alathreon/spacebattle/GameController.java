package com.alathreon.spacebattle;

import com.alathreon.spacebattle.logic.GameLoop;
import com.alathreon.spacebattle.logic.HumanPlayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private Canvas canvas;
    private GameLoop gameLoop;
    private HumanPlayer player;
    @FXML
    private void onMousePressed(MouseEvent event) {
        player.setTarget(event.getSceneX(), event.getSceneY());
    }
    @FXML
    private void onMouseReleased(MouseEvent event) {
        player.setNoTarget();
    }
    @FXML
    private void onMouseDragged(MouseEvent event) {
        player.setTarget(event.getSceneX(), event.getSceneY());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameLoop = new GameLoop(canvas::getWidth, canvas::getHeight, canvas::getGraphicsContext2D);
        player = new HumanPlayer();
        gameLoop.spawn(player, canvas.getWidth()/2, canvas.getHeight()/2);
        gameLoop.start();
    }
}
