package Controlls.ScoreControlles;

import Tetrominos.Tetromino;

import java.util.ArrayList;

public class ScoreController {
    private int score;
    private int linesCleared;
    private int combo;
    private boolean tetris;
    private ArrayList<Tetromino> lastArray;

    public ScoreController(ArrayList<Tetromino> activeTetrominos) {
        this.score = 0;
        this.linesCleared = 0;
        this.combo = 0;
        this.tetris = false;
        this.lastArray = new ArrayList<> (activeTetrominos);
    }

    public ArrayList<Tetromino> checkFullRow(ArrayList<Tetromino> activeTetrominos, int score){
        if(lastArray.size() == activeTetrominos.size() - 1) {
            FullRowCheck fullRowCheck = new FullRowCheck(activeTetrominos, this.linesCleared, this.combo, this.tetris);
            ArrayList<Tetromino> returnArray = fullRowCheck.checkRows();
            this.score = fullRowCheck.getScore();
            this.linesCleared = fullRowCheck.getLinesCleared();
            this.combo = fullRowCheck.getCombo();
            this.tetris = fullRowCheck.getTetris();
            this.lastArray = activeTetrominos;
            return returnArray;
        }
        this.lastArray = new ArrayList<>(activeTetrominos);
        return activeTetrominos;
    }

    public int getScore() {
        int returnScore = this.score;
        this.score = 0;
        return returnScore;
    }

    public int getLinesCleared(){
        return this.linesCleared;
    }

    public int calculateLevel(){
        return Math.max(1000 - ((linesCleared / 10) * 100), 100);
    }

    public int getCombo() {
        return this.combo;
    }
}