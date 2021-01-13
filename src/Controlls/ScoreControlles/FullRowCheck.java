package Controlls.ScoreControlles;

import Tetrominos.Tetromino;
import javafx.geometry.Point2D;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

public class FullRowCheck {
    private ArrayList<Tetromino> activeTetrominos;
    private int score;
    private int linesCleared;
    private int combo;
    private boolean tetris;
    private MediaPlayer single;

    public FullRowCheck(ArrayList<Tetromino> activeTetrominos, int linesCleared, int combo, boolean tetris) {
        this.activeTetrominos = activeTetrominos;
        this.score = 0;
        this.linesCleared = linesCleared;
        this.combo = combo;
        this.tetris = tetris;
        this.single = new MediaPlayer(new Media(new File("resources\\single.wav").toURI().toString()));

    }

    public ArrayList<Tetromino> checkRows(){

        ArrayList<ArrayList<Point2D>> collectionList = new ArrayList<ArrayList<Point2D>>();
        //Makes arrays for all rows
        for(int i = 0; i < 20; i++) {
            collectionList.add(new ArrayList<>());
        }

        for(Tetromino tetromino : this.activeTetrominos) {
            for(int i = 0; i < 4; i++) {
                //looks in what row each tetromino is
                Point2D point = (Point2D)tetromino.getShape().get(i);
                if(point.getY() < 20 && point.getY() > 0) {
                    //adds the correct tetromino in the correct array list/row
                    collectionList.get((int)point.getY()).add(point);
                }
            }
        }
        int newLinesCleared = 0;
        int i = 0;
        ArrayList<Point2D> newTetromino = new ArrayList<>();
        ArrayList<Tetromino> newActiveArrayList = new ArrayList<>();
        //check all the rows
        for(ArrayList<? extends Point2D> arrayList : collectionList) {
            //if the row is full
            if(arrayList.size() >= 10) {
                newLinesCleared += 1;
                //look up all tetrominos
                for(Tetromino tetromino : this.activeTetrominos) {
                    newTetromino.clear();
                    //and remove the blocks in the row
                    for(int j = 0; j < 4; j++) {
                        Point2D point = (Point2D)tetromino.getShape().get(j);
                        //if the point matches the row
                        if(point.getY() == i){
                            //remove the point
                            point = new Point2D(100, 100);

                        }
                        //if the tetromino is above the removed row, it should go one down
                        else if(point.getY() < i){
                            point = new Point2D(point.getX(), point.getY() + 1);

                        }
                        newTetromino.add(point);

                    }
                    //update the tetromino
                    tetromino.setShape(newTetromino);
                }
            }
            i++;
        }

        switch (newLinesCleared) {
            case (0) :
                this.combo = 0;
                break;
            case(1) :
                if(combo >= 1) {
                    playComboSound();
                    this.score += (40 + (40 * (this.linesCleared / 10))) + (50 * this.combo *  ((this.linesCleared / 10) + 1));
                }
                else {
                    playLineClear();
                    this.score += 40 + (40 * (this.linesCleared / 10));
                }
                this.linesCleared += 1;
                this.combo += 1;
                this.tetris = false;
                System.out.println("combo: " + this.combo + " tetris: " + this.tetris);
                break;
            case(2) :
                if(combo >= 1) {
                    playComboSound();
                    this.score += (100 + (100 * (this.linesCleared / 10))) + (50 * this.combo *  (this.linesCleared / 10));
                }
                else {
                    playLineClear();
                    this.score += 100 + (100 * (this.linesCleared / 10));
                }
                this.linesCleared += 2;
                this.combo += 1;
                this.tetris = false;
                System.out.println("combo: " + this.combo + " tetris: " + this.tetris);
                break;
            case(3) :
                if(combo >= 1) {
                    playComboSound();
                    this.score += (300 + (300 * (this.linesCleared / 10))) + (50 * this.combo *  (this.linesCleared / 10));
                }
                else {
                    playLineClear();
                    this.score += 300 + (300 * (this.linesCleared / 10));
                }
                this.linesCleared += 3;
                this.combo += 1;
                this.tetris = false;
                System.out.println("combo: " + this.combo + " tetris: " + this.tetris);
                break;
            case(4) :
                if(tetris) {
                    this.score += (1200 + (1200 * (this.linesCleared / 10))) * 1.5;
                    playTetrisClear();
                }
                else if(combo >= 1) {
                    playComboSound();
                    this.score += (1200 + (1200 * (this.linesCleared / 10)) + (50 * this.combo *  (this.linesCleared / 10)));
                }
                else {
                    playTetrisClear();
                    this.score += 1200 + (1200 * (this.linesCleared / 10));
                    this.tetris = false;
                }
                this.linesCleared += 4;
                this.combo += 1;
                this.tetris = true;
                System.out.println("combo: " + this.combo + " tetris: " + this.tetris);
                break;
            default: break;
        }

        return checkIfInBounds(this.activeTetrominos);
    }

    //Chekcs if a Tetromino is out of bounds, so the activeTetrominos array stays small
    public ArrayList<Tetromino> checkIfInBounds(ArrayList<Tetromino> activeTetrominos){
        ArrayList<Tetromino> inBoundTetrominos = new ArrayList<>();
        for(Tetromino tetromino : activeTetrominos) {
            //assumes the tetromino is out of bounds
            boolean isOut = true;
            for(int i = 0; i < 4; i++) {
                Point2D point = (Point2D)tetromino.getShape().get(i);
                //if a point is inbounds, isOut is false, and the tetromino counts for activeTetrominos
                if(point.getY() < 100) {
                    isOut = false;
                }
            }
            //if it is still inbounds
            if (!isOut) {
                inBoundTetrominos.add(tetromino);
            }
        }
        return inBoundTetrominos;
    }

    //Does not work Properly
    public void playLineClear(){
        single.play();
        single.seek(single.getStartTime());
    }


    //Does not work properly
    public void playTetrisClear(){
        MediaPlayer tetrisSound = new MediaPlayer(new Media(new File("resources\\tetrisrows.wav").toURI().toString()));
        tetrisSound.play();
        tetrisSound.seek(tetrisSound.getStartTime());
    }

    //Does not work properly
    public void playComboSound(){
        MediaPlayer combo2 = new MediaPlayer(new Media(new File("resources\\combo2.wav").toURI().toString()));
        MediaPlayer combo3 = new MediaPlayer(new Media(new File("resources\\combo3.wav").toURI().toString()));
        MediaPlayer combo4 = new MediaPlayer(new Media(new File("resources\\combo4.wav").toURI().toString()));
        MediaPlayer combo5 = new MediaPlayer(new Media(new File("resources\\combo5.wav").toURI().toString()));
        MediaPlayer combo6 = new MediaPlayer(new Media(new File("resources\\combo6.wav").toURI().toString()));
        MediaPlayer combo7 = new MediaPlayer(new Media(new File("resources\\combo7.wav").toURI().toString()));
        MediaPlayer combo8 = new MediaPlayer(new Media(new File("resources\\combo8.wav").toURI().toString()));
        MediaPlayer combo9 = new MediaPlayer(new Media(new File("resources\\combo9.wav").toURI().toString()));
        MediaPlayer combo10 = new MediaPlayer(new Media(new File("resources\\combo10.wav").toURI().toString()));
        MediaPlayer combo11 = new MediaPlayer(new Media(new File("resources\\combo11.wav").toURI().toString()));
        MediaPlayer combo12 = new MediaPlayer(new Media(new File("resources\\combo12.wav").toURI().toString()));

        switch (this.combo) {
            case (0) : break;
            case (1) :
                combo2.play();
                combo2.seek(combo2.getStartTime());
            case (2) :
                combo2.play();
                combo2.seek(combo2.getStartTime());
                break;
            case (3) :
                combo3.play();
                combo3.seek(combo3.getStartTime());
                break;
            case (4) :
                combo4.play();
                combo4.seek(combo4.getStartTime());
                break;
            case (5) :
                combo5.play();
                combo5.seek(combo5.getStartTime());
                break;
            case (6) :
                combo6.play();
                combo6.seek(combo6.getStartTime());
                break;
            case (7) :
                combo7.play();
                combo7.seek(combo7.getStartTime());
                break;
            case (8) :
                combo8.play();
                combo8.seek(combo8.getStartTime());
                break;
            case (9) :
                combo9.play();
                combo9.seek(combo9.getStartTime());
                break;
            case (10) :
                combo10.play();
                combo10.seek(combo10.getStartTime());
                break;
            case (11) :
                combo11.play();
                combo11.seek(combo11.getStartTime());
                break;
            default:
                combo12.play();
                combo12.seek(combo12.getStartTime());
        }
    }

    public int getScore() {
        return this.score;
    }

    public int getLinesCleared(){
        return this.linesCleared;
    }

    public int getCombo(){
        return this.combo;
    }

    public boolean getTetris(){
        return this.tetris;
    }
}