package Controlls.MoveControlls;

import Tetrominos.*;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class MoveController {
    private Tetromino currentTetromino;
    private ArrayList<Tetromino> activePieces;
    private Tetromino holdTetromino;
    private Tetromino returnTetromino;
    private int score;

    private boolean shiftAllowed;

    public MoveController(Tetromino currentTetromino, ArrayList<Tetromino> activePieces){
        this.currentTetromino = currentTetromino;
        this.activePieces = activePieces;
        this.holdTetromino = null;
        this.returnTetromino = null;
        this.shiftAllowed = true;
        this.score = 0;
    }

    public Tetromino moveClockWise() {
        //validates initialisation, prevents nullpointers
        if(this.currentTetromino == null) {
            return null;
        }
        switch (this.currentTetromino.getPieceName()) {
            case "i" :
                this.currentTetromino = iMoveClockWise();
                break;
            case "j" :
                this.currentTetromino = jMoveClockWise();
                break;
            case "l" :
                this.currentTetromino = lMoveClockWise();
                break;
            case "s" :
                this.currentTetromino = sMoveClockWise();
                break;
            case "t" :
                this.currentTetromino = tMoveClockWise();
                break;
            case "z" :
                this.currentTetromino = zMoveClockWise();
        }
        return this.currentTetromino;
    }

    public Tetromino moveCounterClockWise(){
        //validates initialisation, prevents nullpointers
        if(this.currentTetromino == null) {
            return null;
        }
        switch (this.currentTetromino.getPieceName()) {
            case "i" :
                this.currentTetromino = iMoveCounterClockWise();
                break;
            case "j" :
                this.currentTetromino = jMoveCounterClockWise();
                break;
            case "l" :
                this.currentTetromino = lMoveCounterClockWise();
                break;
            case "s" :
                this.currentTetromino = sMoveCounterClockWise();
                break;
            case "t" :
                this.currentTetromino = tMoveCounterClockWise();
                break;
            case "z" :
                this.currentTetromino = zMoveCounterClockWise();
        }
        return this.currentTetromino;
    }

    public Tetromino moveDown(){
        if(this.currentTetromino == null) {
            return null;
        }

        ArrayList<Point2D> newPoints = new ArrayList<>();
        Point2D currentPosition;
        Point2D newPosition = new Point2D(0, 0);
        boolean validPosition = true;
        //calculates the new positions
        for(int i = 0; i < 4; i++) {
            currentPosition = (Point2D)this.currentTetromino.getShape().get(i);
            //checks if it is inbounds
            if(currentPosition.getY() != 19) {
                newPosition = new Point2D(currentPosition.getX(), currentPosition.getY() + 1);
                newPoints.add(newPosition);
            }
            else validPosition = false;
            //checks if it collides with another active piece
            if(validPosition) validPosition = checkCollision(newPosition);
        }
        if(validPosition) this.currentTetromino.setShape(newPoints);
        this.score += 1;
        return this.currentTetromino;
    }

    public Tetromino moveLeft() {
        if(this.currentTetromino == null) {
            return null;
        }

        ArrayList<Point2D> newPoints = new ArrayList<>();
        Point2D currentPosition;
        Point2D newPosition = new Point2D(0, 0);
        boolean validPosition = true;
        for(int i = 0; i < 4; i++) {
            currentPosition = (Point2D)this.currentTetromino.getShape().get(i);
            if(currentPosition.getX() > 0) {
                newPosition = new Point2D(currentPosition.getX() - 1, currentPosition.getY());
                newPoints.add(newPosition);
            }
            else validPosition = false;
            if(validPosition) validPosition = checkCollision(newPosition);
        }
        if (validPosition) this.currentTetromino.setShape(newPoints);
        return this.currentTetromino;
    }

    public Tetromino moveRight() {
        if(this.currentTetromino == null) {
            return null;
        }

        ArrayList<Point2D> newPoints = new ArrayList<>();
        Point2D currentPosition;
        Point2D newPosition = new Point2D(0, 0);
        boolean validPosition = true;
        for(int i = 0; i < 4; i++) {
            currentPosition = (Point2D)this.currentTetromino.getShape().get(i);
            if(currentPosition.getX() < 9) {
                newPosition = new Point2D(currentPosition.getX() + 1, currentPosition.getY());
                newPoints.add(newPosition);
            }
            else validPosition = false;
            if(validPosition) validPosition = checkCollision(newPosition);

        }
        if(validPosition) this.currentTetromino.setShape(newPoints);
        return this.currentTetromino;
    }

    public Tetromino spacebar(){
        if(this.currentTetromino == null) {
            return null;
        }
        int counter = 1;
        boolean validPosition = true;
        Point2D newPosition;
        Point2D currentPosition;
        ArrayList<Point2D> newPoints = new ArrayList<>();
        ArrayList<Point2D> newValidPoints = new ArrayList<>();

        while (validPosition) {
            newPoints.clear();
            //looks if the position under the tetromino is valid, will repeat until it isn't
            for(int i = 0; i < 4; i++) {
                currentPosition = (Point2D)currentTetromino.getShape().get(i);
                newPosition = new Point2D(currentPosition.getX(), currentPosition.getY() + counter);
                newPoints.add(newPosition);
                if(validPosition) validPosition = checkCollision(newPosition);
            }
            //only if the new position is valid, the position will update
            if(validPosition) {
                newValidPoints.clear();
                newValidPoints.addAll(newPoints);
                counter++;
                this.score += 2;
            }

        }
        this.currentTetromino.setShape(newValidPoints);
        return this.currentTetromino;
    }

    public Tetromino hold(){
        if(this.holdTetromino == null) {
            this.holdTetromino = this.currentTetromino;
            System.out.println(this.holdTetromino.getPieceName() + " is being stored");
            return null;
        }
        else {
            this.returnTetromino = this.holdTetromino;
            System.out.println("ReturnTetromino is: " + this.returnTetromino.getPieceName());
            this.holdTetromino = this.currentTetromino;
            System.out.println("Holding now: " + this.holdTetromino.getPieceName());
            switch (this.returnTetromino.getPieceName()) {
                case "i" :
                    return new ITetromino();
                case "j" :
                    return new JTetromino();
                case "l" :
                    return new LTetromino();
                case "o" :
                    return new OTetromino();
                case "s" :
                    return new STetromino();
                case "t" :
                    return new TTetromino();
                case "z" :
                    return new ZTetromino();
            }
        }
        return null;
    }

    //checks if the new position is valid
    public boolean checkCollision(Point2D currentPosition) {
        boolean validPosition = true;
        //checks for collision with all the active pieces
        if(!activePieces.isEmpty()) {
            for(Tetromino activeTetromino : this.activePieces) {
                for(int j = 0; j < 4; j++){
                    if(activeTetromino.getShape().get(j).equals(currentPosition)) {
                        validPosition = false;
                    }
                }
            }
        }

        //checks if inbounds
        if(currentPosition.getY() < -2) validPosition = false;
        if(currentPosition.getY() > 19) validPosition = false;
        if(currentPosition.getX() < 0) validPosition = false;
        if(currentPosition.getX() > 9) validPosition = false;
        return validPosition;
    }

    public Tetromino iMoveClockWise(){
        IController icontroller = new IController(this.currentTetromino, this.activePieces);
        return icontroller.iMoveClockWise();
    }

    public Tetromino iMoveCounterClockWise(){
        IController icontroller = new IController(this.currentTetromino, this.activePieces);
        return icontroller.iMoveCounterClockWise();
    }

    public Tetromino jMoveClockWise(){
        JController jController = new JController(this.currentTetromino, this.activePieces);
        return jController.jMoveClockWise();
    }

    public Tetromino jMoveCounterClockWise(){
        JController jController = new JController(this.currentTetromino, this.activePieces);
        return jController.jMoveCounterClockWise();
    }

    public Tetromino lMoveClockWise(){
        LController lController = new LController(this.currentTetromino, this.activePieces);
        return lController.lMoveClockWise();
    }

    public Tetromino lMoveCounterClockWise(){
        LController lController = new LController(this.currentTetromino, this.activePieces);
        return lController.lMoveCounterClockWise();
    }


    public Tetromino sMoveClockWise(){
        SController sController = new SController(this.currentTetromino, this.activePieces);
        return sController.sMoveClockWise();
    }

    public Tetromino sMoveCounterClockWise(){
        SController sController = new SController(this.currentTetromino, this.activePieces);
        return sController.sMoveCounterClockWise();
    }


    public Tetromino tMoveClockWise(){
        TController tController = new TController(this.currentTetromino, this.activePieces);
        return tController.tMoveClockWise();
    }

    public Tetromino tMoveCounterClockWise(){
        TController tController = new TController(this.currentTetromino, this.activePieces);
        return  tController.tMoveCounterClockWise();

    }


    public Tetromino zMoveClockWise(){
        ZController zController = new ZController(this.currentTetromino, this.activePieces);
        return zController.zMoveClockWise();
    }

    public Tetromino zMoveCounterClockWise(){
        ZController zController = new ZController(this.currentTetromino, this.activePieces);
        return zController.zMoveCounterClockWise();
    }


    public void setCurrentTetromino(Tetromino currentTetromino){
        this.currentTetromino = currentTetromino;
    }

    public void setActivePieces(ArrayList<Tetromino> activeTetromino) {
        this.activePieces = activeTetromino;
    }

    public Tetromino getCurrentTetromino(){
        return this.currentTetromino;
    }

    public Tetromino getHoldTetromino() {
        return this.holdTetromino;
    }

    public int getScore(){
        int returnScore = this.score;
        this.score = 0;
        return returnScore;
    }

    public void setScore(){
        this.score = 0;
    }

}
