package GUI;

import Controlls.Controller;
import Tetrominos.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jfree.fx.FXGraphics2D;

import java.io.File;
import java.util.ArrayList;


public class GUI extends Application {
    private Scene scene;
    private Draw draw;
    private Controller controller;
    private Bag bag;
    private ArrayList<Tetromino> activeTetrominos;
    private Tetromino currentTetromino;
    private MediaPlayer BGMMP;
    private MediaPlayer moveMP;
    private MediaPlayer rotateMP;
    private MediaPlayer hardDropMP;
    private MediaPlayer holdMP;
    private boolean drawTetrominos;

    @Override
    public void start(Stage stage) throws Exception {
        Canvas canvas = new Canvas(1000, 800);
        this.activeTetrominos = new ArrayList<>();
        this.scene = new Scene(new Group(canvas));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        this.bag = new Bag();
        this.draw = new Draw();
        this.drawTetrominos = true;
        this.draw.drawBackGround(g2d);

        Media BGM = new Media(new File("C:\\Users\\Remco\\Documents\\IdeaProjects\\TMCProjects\\2017_avans_ti_breda-2D_Graphics-2019\\Tetris\\Recources\\tetrisBGM.wav").toURI().toString());
        Media move = new Media(new File("C:\\Users\\Remco\\Documents\\IdeaProjects\\TMCProjects\\2017_avans_ti_breda-2D_Graphics-2019\\Tetris\\Recources\\move.wav").toURI().toString());
        Media rotate = new Media(new File("C:\\Users\\Remco\\Documents\\IdeaProjects\\TMCProjects\\2017_avans_ti_breda-2D_Graphics-2019\\Tetris\\Recources\\rotate.wav").toURI().toString());
        Media hardDrop = new Media(new File("C:\\Users\\Remco\\Documents\\IdeaProjects\\TMCProjects\\2017_avans_ti_breda-2D_Graphics-2019\\Tetris\\Recources\\hardDrop.wav").toURI().toString());
        Media hold = new Media(new File("C:\\Users\\Remco\\Documents\\IdeaProjects\\TMCProjects\\2017_avans_ti_breda-2D_Graphics-2019\\Tetris\\Recources\\hold.wav").toURI().toString());

        this.BGMMP = new MediaPlayer(BGM);
        this.moveMP = new MediaPlayer(move);
        this.rotateMP = new MediaPlayer(rotate);
        this.hardDropMP = new MediaPlayer(hardDrop);
        this.holdMP = new MediaPlayer(hold);

        this.controller = new Controller(this.currentTetromino, this.activeTetrominos, this.scene, this.moveMP, this.rotateMP, this.hardDropMP, this.holdMP);

        //AnimationTimer
        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1) {
                    last = now;
                }
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();
    }

    public void update(double deltaTime) {
        if(!controller.getGameOver()) {
            this.controller.update(this.bag);
            this.drawTetrominos = this.controller.getDrawTetrominos();
        }
        else System.out.println("game over");
        this.BGMMP.setVolume(0.5);
        this.BGMMP.play();
        BGMMP.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                BGMMP.seek(new Duration(9600));
                BGMMP.play();
            }
        });
    }

    public void draw(FXGraphics2D graphics) {
        if(!controller.getGameOver()) {
            if(drawTetrominos) {
                this.draw.drawComboBox(graphics);
                this.draw.drawLittleBackGround(graphics);
                this.draw.drawStage(graphics);
                this.draw.drawTetrominos(graphics, controller.getCurrentTetromino(), controller.getActiveTetrominos());
                this.draw.drawHoldTetromino(graphics, this.controller);
                this.draw.drawNextTetrominos(graphics, this.bag);
                this.drawTetrominos = false;
            }
            this.draw.drawNewLines(graphics, this.controller, controller.getActiveTetrominos());
            this.draw.drawScore(graphics, this.controller, this.controller.getScore(), this.controller.getCombo());
        }
    }
}
