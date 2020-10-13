package Controlls;

import Controlls.MoveControlls.*;
import Controlls.ScoreControlles.ScoreController;
import Tetrominos.*;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;

public class Controller {
    private MoveController moveController;
    private ScoreController scoreController;
    private ArrayList<Tetromino> activeTetrominos;
    private Tetromino currentTetromino;
    private Tetromino holdTetromino;
    private Tetromino returnTetromino;

    private MediaPlayer moveMP;
    private MediaPlayer rotateMP;
    private MediaPlayer hardDropMP;
    private MediaPlayer holdMP;

    private boolean shiftAllowed;
    private boolean drawTetrominos;
    private boolean gameover;
    private int levelSpeed;
    private int score;
    private long startTime;
    private Scene scene;


    public Controller(Tetromino currentTetromino, ArrayList<Tetromino> activePieces, Scene scene, MediaPlayer move, MediaPlayer rotate, MediaPlayer hardDrop, MediaPlayer hold) {
        this.activeTetrominos = activePieces;
        this.currentTetromino = currentTetromino;
        this.holdTetromino = null;
        this.returnTetromino = null;
        this.scene = scene;

        this.moveMP = move;
        this.rotateMP = rotate;
        this.hardDropMP = hardDrop;
        this.holdMP = hold;

        this.moveController = new MoveController(this.currentTetromino, this.activeTetrominos);
        this.scoreController = new ScoreController(this.activeTetrominos);

        this.shiftAllowed = true;
        this.gameover = false;
        this.levelSpeed = 1000;
        this.score = 0;
    }

    public Controller(Tetromino currentTetromino, ArrayList<Tetromino> activePieces) {
        this(currentTetromino, activePieces, null, null, null, null, null);
    }


    public void update(Bag bag){
        //generates new currentTetromino with the bag
        newCurrentTetromino(bag);

        //makes the piece move down automatically
        if(System.currentTimeMillis() - startTime > levelSpeed ) {
            Point2D oldPosition;
            oldPosition = (Point2D)currentTetromino.getShape().get(0);
            setCurrentTetromino();
            //the piece moves down after a time witch is the level speed
            currentTetromino = this.moveController.moveDown();
            drawTetrominos = true;
            startTime = System.currentTimeMillis();

            //will lock the piece and generate a new piece when the piece doesnt move
            if(currentTetromino.getShape().get(0).equals(oldPosition)) {
                if(oldPosition.getY() == 0 || oldPosition.getY() ==  -1) {
                    this.gameover = true;
                }
                activeTetrominos.add(currentTetromino);
                currentTetromino = null;
                setCurrentTetromino();
            }
        }

        this.levelSpeed = calculateLevel();

        //Checks what key on the keyboard is pressed
        this.scene.setOnKeyPressed(e -> {
            if(!gameover) {
                this.drawTetrominos = true;
                setCurrentTetromino();
                if(this.currentTetromino== null) {
                    newCurrentTetromino(bag);
                }
                switch (e.getCode()) {
                    case UP:
                    case X:
                        this.currentTetromino = this.moveController.moveClockWise();
                        this.rotateMP.play();
                        this.rotateMP.seek(this.rotateMP.getStartTime());
                        break;
                    case DOWN:
                        this.moveController.setScore();
                        this.currentTetromino = this.moveController.moveDown();
                        this.score += this.moveController.getScore();
                        break;
                    case LEFT:
                        this.currentTetromino = this.moveController.moveLeft();
                        this.moveMP.play();
                        this.moveMP.seek(this.moveMP.getStartTime());
                        break;
                    case RIGHT:
                        this.moveMP.play();
                        this.moveMP.seek(this.moveMP.getStartTime());
                        this.currentTetromino = this.moveController.moveRight();
                        break;
                    case Z:
                        this.currentTetromino = this.moveController.moveCounterClockWise();
                        this.rotateMP.play();
                        this.rotateMP.seek(this.rotateMP.getStartTime());
                        break;
                    case SHIFT:
                        if(this.shiftAllowed) {
                            this.currentTetromino = this.moveController.hold();
                            setCurrentTetromino();
                            newCurrentTetromino(bag);
                            this.shiftAllowed = false;
                            this.holdMP.play();
                            this.holdMP.seek(this.holdMP.getStartTime());
                        }
                        break;
                    case SPACE:
                        this.moveController.setScore();
                        Point2D oldPos = (Point2D)currentTetromino.getShape().get(0);
                        this.currentTetromino = this.moveController.spacebar();
                        if(oldPos.getY() == 0 || oldPos.getY() == -1) {
                            Point2D newPos = (Point2D)currentTetromino.getShape().get(0);
                            if(newPos.getY() == oldPos.getY()) {
                                this.gameover = true;
                            }
                        }
                        this.activeTetrominos.add(this.currentTetromino);
                        this.currentTetromino = null;
                        this.score += this.moveController.getScore();
                        this.hardDropMP.play();
                        this.hardDropMP.seek(this.hardDropMP.getStartTime());
                        break;
                }
            }

        });
        this.activeTetrominos = checkFullRow(this.activeTetrominos, this.score);
        this.score += this.scoreController.getScore();
    }

    public void newCurrentTetromino(Bag bag){
        if(currentTetromino == null) {
            this.shiftAllowed = true;
            this.currentTetromino = bag.generateTetromino();
            this.startTime = System.currentTimeMillis();
        }
    }

    public boolean getDrawTetrominos(){
        return this.drawTetrominos;
    }

    public ArrayList<Tetromino> getActiveTetrominos(){
        return this.activeTetrominos;
    }

    public boolean checkCollision(Point2D currentPosition) {
       return this.moveController.checkCollision(currentPosition);
    }

    public void setCurrentTetromino(){
        this.moveController.setCurrentTetromino(this.currentTetromino);
        this.moveController.setActivePieces(this.activeTetrominos);
    }

    public Tetromino getCurrentTetromino(){
        return this.currentTetromino;
    }

    public Tetromino getHoldTetromino() {
        return this.moveController.getHoldTetromino();
    }

    public ArrayList<Tetromino> checkFullRow(ArrayList<Tetromino> activeTetrominos, int score){
        return this.scoreController.checkFullRow(activeTetrominos, score);
    }

    public int getScore(){
        return this.score;
    }

    public int getCombo(){
        return this.scoreController.getCombo();
    }

    public int getLinesCleared(){
        return this.scoreController.getLinesCleared();
    }

    public int calculateLevel(){
        return this.scoreController.calculateLevel();
    }

    public boolean getGameOver(){
        return this.gameover;
    }

    public Tetromino pressSpace(){
        return this.moveController.spacebar();
    }
}
