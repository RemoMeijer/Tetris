package Controlls.MoveControlls;
import Controlls.Controller;
import Tetrominos.Tetromino;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class WallKicks {
    private Tetromino currentTetromino;
    private ArrayList<Tetromino> activeTetrominos;
    private boolean validPosition;

    //Source: https://tetris.wiki/Super_Rotation_System#Wall_Kicks (note that the y positions are reversed)
    public WallKicks() {}

    public boolean checkCollision(Point2D newPosition){
        Controller controller = new Controller(currentTetromino, activeTetrominos);
        return controller.checkCollision(newPosition);
    }

    public ArrayList<Point2D> positions(ArrayList<Point2D> newPoints, Tetromino currentTetromino, int xOff, int yOff) {
        for(int j = 0; j < 4 && validPosition; j++) {
            Point2D currentPosition = (Point2D) currentTetromino.getShape().get(j);
            Point2D newPosition = new Point2D(currentPosition.getX() + xOff, currentPosition.getY() + yOff);
            newPoints.add(newPosition);
            validPosition = checkCollision(newPosition);
        }
        return newPoints;
    }

    public boolean validation(ArrayList<Point2D> newPoints, int rotation){
        if(this.validPosition) {
            this.currentTetromino.setShape(newPoints);
            this.currentTetromino.setRotation(rotation);
        }
        return this.validPosition;
    }

    //Wallkick tests for rotation 0 to 1
    public Tetromino tests_0to1(Tetromino currentTetromino, Tetromino oldTetromino, ArrayList<Tetromino> activeTetrominos){
        this.currentTetromino = currentTetromino;
        this.activeTetrominos = activeTetrominos;
        ArrayList<Point2D> newPoints = new ArrayList<>();
        this.validPosition = true;
        //Preforms 4 tests
        for(int i = 0; i < 4; i++){
            //First test
            if(i == 0){
                //Looks if the piece can move one over
                newPoints = positions(newPoints, currentTetromino, - 1, 0);
                //Will set the piece one over if its a valid position
                if(validation(newPoints, 1)) break;
            }
            //If the fist test fails, the second test will be done
            if(i == 1) {
                //Looks if it can move one over and one up
                newPoints = positions(newPoints, currentTetromino, - 1, - 1);
                if(validation(newPoints, 1)) break;
            }
            //Fist two tests failed, will do a third test
            if(i == 2) {
                //Looks if it can go two down
                newPoints = positions(newPoints, currentTetromino, 0, 2);
                if(validation(newPoints, 1)) break;
            }
            //Preforms last test
            if(i == 3) {
                //Looks if it can go one to the right and two down
                newPoints = positions(newPoints, currentTetromino, 1, 2);
                if(validation(newPoints, 1)) break;

                //All the test failed, so the old Tetromino will be called and the rotation fails
                else {
                    currentTetromino = oldTetromino;
                }
            }
            this.validPosition = true;
            newPoints.clear();
        }

        return currentTetromino;
    }

    //Wallkick tests for rotation 1 to 2, see Wallkicks 0 to 1, but different offsets
    public Tetromino tests_1to2(Tetromino currentTetromino, Tetromino oldTetromino, ArrayList<Tetromino> activeTetrominos){
        this.currentTetromino = currentTetromino;
        this.activeTetrominos = activeTetrominos;
        ArrayList<Point2D> newPoints = new ArrayList<>();
        this.validPosition = true;
        for(int i = 0; i < 4; i++){
            if(i == 0){
                newPoints = positions(newPoints, currentTetromino, 1, 0);
                if(validation(newPoints, 2)) break;
            }
            if(i == 1) {
                newPoints = positions(newPoints, currentTetromino, 1, 1);
                if(validation(newPoints, 2)) break;
            }
            if(i == 2) {
                newPoints = positions(newPoints, currentTetromino, 0, -2);
                if(validation(newPoints, 2)) break;

            }
            if(i == 3) {
                newPoints = positions(newPoints, currentTetromino, 1, -2);
                if(validation(newPoints, 2)) break;
                else {
                    currentTetromino = oldTetromino;
                }
            }
            this.validPosition = true;
            newPoints.clear();
        }
        return currentTetromino;
    }


    //Wallkick tests for rotation 2 to 3, see Wallkicks 0 to 1, but different offsets
    public Tetromino tests_2to3(Tetromino currentTetromino, Tetromino oldTetromino, ArrayList<Tetromino> activeTetrominos){
        this.currentTetromino = currentTetromino;
        this.activeTetrominos = activeTetrominos;
        ArrayList<Point2D> newPoints = new ArrayList<>();
        this.validPosition = true;
        for(int i = 0; i < 4; i++){
            if(i == 0){
                newPoints = positions(newPoints, currentTetromino, 1, 0);
                if(validation(newPoints, 3)) break;
            }
            if(i == 1) {
                newPoints = positions(newPoints, currentTetromino, 1, -1);
                if(validation(newPoints, 3)) break;
            }
            if(i == 2) {
                newPoints = positions(newPoints, currentTetromino, 0, 2);
                if(validation(newPoints, 3)) break;

            }
            if(i == 3) {
                newPoints = positions(newPoints, currentTetromino, 1, 2);
                if(validation(newPoints, 3)) break;
                else {
                    currentTetromino = oldTetromino;
                }
            }
            this.validPosition = true;
            newPoints.clear();
        }
        return currentTetromino;
    }


    //Wallkick tests for rotation 3 to 0, see Wallkicks 0 to 1, but different offsets
    public Tetromino tests_3to0(Tetromino currentTetromino, Tetromino oldTetromino, ArrayList<Tetromino> activeTetrominos){
        this.currentTetromino = currentTetromino;
        this.activeTetrominos = activeTetrominos;
        ArrayList<Point2D> newPoints = new ArrayList<>();
        this.validPosition = true;
        for(int i = 0; i < 4; i++){
            if(i == 0){
                newPoints = positions(newPoints, currentTetromino, -1, 0);
                if(validation(newPoints, 0)) break;
            }
            if(i == 1) {
                newPoints = positions(newPoints, currentTetromino, -1, 1);
                if(validation(newPoints, 0)) break;
            }
            if(i == 2) {
                newPoints = positions(newPoints, currentTetromino, 0, -2);
                if(validation(newPoints, 0)) break;

            }
            if(i == 3) {
                newPoints = positions(newPoints, currentTetromino, -1, -2);
                if(validation(newPoints, 0)) break;
                else {
                    currentTetromino = oldTetromino;
                }
            }
            this.validPosition = true;
            newPoints.clear();
        }
        return currentTetromino;
    }

    //Wallkick tests for rotation 0 to 3, see Wallkicks 0 to 1, but different offsets
    public Tetromino tests_0to3(Tetromino currentTetromino, Tetromino oldTetromino, ArrayList<Tetromino> activeTetrominos){
        this.currentTetromino = currentTetromino;
        this.activeTetrominos = activeTetrominos;
        ArrayList<Point2D> newPoints = new ArrayList<>();
        this.validPosition = true;
        for(int i = 0; i < 4; i++){
            if(i == 0){
                newPoints = positions(newPoints, currentTetromino, 1, 0);
                if(validation(newPoints, 3)) break;
            }
            if(i == 1) {
                newPoints = positions(newPoints, currentTetromino, -1, 1);
                if(validation(newPoints, 3)) break;
            }
            if(i == 2) {
                newPoints = positions(newPoints, currentTetromino, 0, -2);
                if(validation(newPoints, 3)) break;

            }
            if(i == 3) {
                newPoints = positions(newPoints, currentTetromino, -1, 2);
                if(validation(newPoints, 3)) break;
                else {
                    currentTetromino = oldTetromino;
                }
            }
            this.validPosition = true;
            newPoints.clear();
        }
        return currentTetromino;
    }

    //Wallkick tests for rotation 3 to 2, see Wallkicks 0 to 1, but different offsets
    //This one is the same as 3 to 0
    public Tetromino tests_3to2(Tetromino currentTetromino, Tetromino oldTetromino, ArrayList<Tetromino> activeTetrominos){
        this.currentTetromino = currentTetromino;
        this.activeTetrominos = activeTetrominos;
        ArrayList<Point2D> newPoints = new ArrayList<>();
        this.validPosition = true;
        for(int i = 0; i < 4; i++){
            if(i == 0){
                newPoints = positions(newPoints, currentTetromino, -1, 0);
                if(validation(newPoints, 2)) break;
            }
            if(i == 1) {
                newPoints = positions(newPoints, currentTetromino, -1, 1);
                if(validation(newPoints, 2)) break;
            }
            if(i == 2) {
                newPoints = positions(newPoints, currentTetromino, 0, -2);
                if(validation(newPoints, 2)) break;

            }
            if(i == 3) {
                newPoints = positions(newPoints, currentTetromino, -1, -2);
                if(validation(newPoints, 2)) break;
                else {
                    currentTetromino = oldTetromino;
                }
            }
            this.validPosition = true;
            newPoints.clear();
        }
        return currentTetromino;
    }

    //Wallkick tests for rotation 2 to 1, see Wallkicks 0 to 1, but different offsets
    public Tetromino tests_2to1(Tetromino currentTetromino, Tetromino oldTetromino, ArrayList<Tetromino> activeTetrominos){
        this.currentTetromino = currentTetromino;
        this.activeTetrominos = activeTetrominos;
        ArrayList<Point2D> newPoints = new ArrayList<>();
        this.validPosition = true;
        for(int i = 0; i < 4; i++){
            if(i == 0){
                newPoints = positions(newPoints, currentTetromino, -1, 0);
                if(validation(newPoints, 2)) break;
            }
            if(i == 1) {
                newPoints = positions(newPoints, currentTetromino, -1, -1);
                if(validation(newPoints, 2)) break;
            }
            if(i == 2) {
                newPoints = positions(newPoints, currentTetromino, 0, 2);
                if(validation(newPoints, 2)) break;

            }
            if(i == 3) {
                newPoints = positions(newPoints, currentTetromino, -1, 2);
                if(validation(newPoints, 2)) break;
                else {
                    currentTetromino = oldTetromino;
                }
            }
            this.validPosition = true;
            newPoints.clear();
        }
        return currentTetromino;
    }

    //Wallkick tests for rotation 1 to 0, see Wallkicks 0 to 1, but different offsets
    public Tetromino tests_1to0(Tetromino currentTetromino, Tetromino oldTetromino, ArrayList<Tetromino> activeTetrominos){
        this.currentTetromino = currentTetromino;
        this.activeTetrominos = activeTetrominos;
        ArrayList<Point2D> newPoints = new ArrayList<>();
        this.validPosition = true;
        for(int i = 0; i < 4; i++){
            if(i == 0){
                newPoints = positions(newPoints, currentTetromino, 1, 0);
                if(validation(newPoints, 0)) break;
            }
            if(i == 1) {
                newPoints = positions(newPoints, currentTetromino, 1, 1);
                if(validation(newPoints, 0)) break;
            }
            if(i == 2) {
                newPoints = positions(newPoints, currentTetromino, 0, -2);
                if(validation(newPoints, 0)) break;

            }
            if(i == 3) {
                newPoints = positions(newPoints, currentTetromino, 1, -2);
                if(validation(newPoints, 0)) break;
                else {
                    currentTetromino = oldTetromino;
                }
            }
            this.validPosition = true;
            newPoints.clear();
        }
        return currentTetromino;
    }

    //I pieces have different wallKicks
    public Tetromino iTests_0to1(Tetromino currentTetromino, Tetromino oldTetromino, ArrayList<Tetromino> activeTetrominos){
        this.currentTetromino = currentTetromino;
        this.activeTetrominos = activeTetrominos;
        ArrayList<Point2D> newPoints = new ArrayList<>();
        this.validPosition = true;
        //Preforms 4 tests
        for(int i = 0; i < 4; i++){
            //First test
            if(i == 0){
                //Looks if the piece can move one over
                newPoints = positions(newPoints, currentTetromino, - 2, 0);
                //Will set the piece one over if its a valid position
                if(validation(newPoints, 1)) break;
            }
            //If the fist test fails, the second test will be done
            if(i == 1) {
                //Looks if it can move one over and one up
                newPoints = positions(newPoints, currentTetromino, 1, 0);
                if(validation(newPoints, 1)) break;
            }
            //Fist two tests failed, will do a third test
            if(i == 2) {
                //Looks if it can go two down
                newPoints = positions(newPoints, currentTetromino, -2, 1);
                if(validation(newPoints, 1)) break;
            }
            //Preforms last test
            if(i == 3) {
                //Looks if it can go one to the right and two down
                newPoints = positions(newPoints, currentTetromino, 1, -2);
                if(validation(newPoints, 1)) break;

                    //All the test failed, so the old Tetromino will be called and the rotation fails
                else {
                    currentTetromino = oldTetromino;
                }
            }
            this.validPosition = true;
            newPoints.clear();
        }

        return currentTetromino;
    }

    public Tetromino iTests_1to2(Tetromino currentTetromino, Tetromino oldTetromino, ArrayList<Tetromino> activeTetrominos){
        this.currentTetromino = currentTetromino;
        this.activeTetrominos = activeTetrominos;
        ArrayList<Point2D> newPoints = new ArrayList<>();
        this.validPosition = true;
        for(int i = 0; i < 4; i++){
            if(i == 0){
                newPoints = positions(newPoints, currentTetromino, -1, 0);
                if(validation(newPoints, 2)) break;
            }
            if(i == 1) {
                newPoints = positions(newPoints, currentTetromino, 2, 0);
                if(validation(newPoints, 2)) break;
            }
            if(i == 2) {
                newPoints = positions(newPoints, currentTetromino, -1, -2);
                if(validation(newPoints, 2)) break;

            }
            if(i == 3) {
                newPoints = positions(newPoints, currentTetromino, 2, 1);
                if(validation(newPoints, 2)) break;
                else {
                    currentTetromino = oldTetromino;
                }
            }
            this.validPosition = true;
            newPoints.clear();
        }
        return currentTetromino;
    }

    public Tetromino iTests_2to3(Tetromino currentTetromino, Tetromino oldTetromino, ArrayList<Tetromino> activeTetrominos){
        this.currentTetromino = currentTetromino;
        this.activeTetrominos = activeTetrominos;
        ArrayList<Point2D> newPoints = new ArrayList<>();
        this.validPosition = true;
        for(int i = 0; i < 4; i++){
            if(i == 0){
                newPoints = positions(newPoints, currentTetromino, 2, 0);
                if(validation(newPoints, 3)) break;
            }
            if(i == 1) {
                newPoints = positions(newPoints, currentTetromino, -1, 0);
                if(validation(newPoints, 3)) break;
            }
            if(i == 2) {
                newPoints = positions(newPoints, currentTetromino, 2, -1);
                if(validation(newPoints, 3)) break;

            }
            if(i == 3) {
                newPoints = positions(newPoints, currentTetromino, -1, 2);
                if(validation(newPoints, 3)) break;
                else {
                    currentTetromino = oldTetromino;
                }
            }
            this.validPosition = true;
            newPoints.clear();
        }
        return currentTetromino;
    }

    public Tetromino iTests_3to0(Tetromino currentTetromino, Tetromino oldTetromino, ArrayList<Tetromino> activeTetrominos){
        this.currentTetromino = currentTetromino;
        this.activeTetrominos = activeTetrominos;
        ArrayList<Point2D> newPoints = new ArrayList<>();
        this.validPosition = true;
        for(int i = 0; i < 4; i++){
            if(i == 0){
                newPoints = positions(newPoints, currentTetromino, 1, 0);
                if(validation(newPoints, 0)) break;
            }
            if(i == 1) {
                newPoints = positions(newPoints, currentTetromino, -2, 0);
                if(validation(newPoints, 0)) break;
            }
            if(i == 2) {
                newPoints = positions(newPoints, currentTetromino, 1, 2);
                if(validation(newPoints, 0)) break;

            }
            if(i == 3) {
                newPoints = positions(newPoints, currentTetromino, -2, -1);
                if(validation(newPoints, 0)) break;
                else {
                    currentTetromino = oldTetromino;
                }
            }
            this.validPosition = true;
            newPoints.clear();
        }
        return currentTetromino;
    }

    public Tetromino iTests_0to3(Tetromino currentTetromino, Tetromino oldTetromino, ArrayList<Tetromino> activeTetrominos){
        this.currentTetromino = currentTetromino;
        this.activeTetrominos = activeTetrominos;
        ArrayList<Point2D> newPoints = new ArrayList<>();
        this.validPosition = true;
        for(int i = 0; i < 4; i++){
            if(i == 0){
                newPoints = positions(newPoints, currentTetromino, -1, 0);
                if(validation(newPoints, 3)) break;
            }
            if(i == 1) {
                newPoints = positions(newPoints, currentTetromino, 2, 0);
                if(validation(newPoints, 3)) break;
            }
            if(i == 2) {
                newPoints = positions(newPoints, currentTetromino, -1, -2);
                if(validation(newPoints, 3)) break;

            }
            if(i == 3) {
                newPoints = positions(newPoints, currentTetromino, -2, 1);
                if(validation(newPoints, 3)) break;
                else {
                    currentTetromino = oldTetromino;
                }
            }
            this.validPosition = true;
            newPoints.clear();
        }
        return currentTetromino;
    }

    public Tetromino iTests_3to2(Tetromino currentTetromino, Tetromino oldTetromino, ArrayList<Tetromino> activeTetrominos){
        this.currentTetromino = currentTetromino;
        this.activeTetrominos = activeTetrominos;
        ArrayList<Point2D> newPoints = new ArrayList<>();
        this.validPosition = true;
        for(int i = 0; i < 4; i++){
            if(i == 0){
                newPoints = positions(newPoints, currentTetromino, -2, 0);
                if(validation(newPoints, 2)) break;
            }
            if(i == 1) {
                newPoints = positions(newPoints, currentTetromino, 1, 0);
                if(validation(newPoints, 2)) break;
            }
            if(i == 2) {
                newPoints = positions(newPoints, currentTetromino, -2, 1);
                if(validation(newPoints, 2)) break;

            }
            if(i == 3) {
                newPoints = positions(newPoints, currentTetromino, 1, -2);
                if(validation(newPoints, 2)) break;
                else {
                    currentTetromino = oldTetromino;
                }
            }
            this.validPosition = true;
            newPoints.clear();
        }
        return currentTetromino;
    }

    public Tetromino iTests_2to1(Tetromino currentTetromino, Tetromino oldTetromino, ArrayList<Tetromino> activeTetrominos){
        this.currentTetromino = currentTetromino;
        this.activeTetrominos = activeTetrominos;
        ArrayList<Point2D> newPoints = new ArrayList<>();
        this.validPosition = true;
        for(int i = 0; i < 4; i++){
            if(i == 0){
                newPoints = positions(newPoints, currentTetromino, 1, 0);
                if(validation(newPoints, 2)) break;
            }
            if(i == 1) {
                newPoints = positions(newPoints, currentTetromino, -2, 0);
                if(validation(newPoints, 2)) break;
            }
            if(i == 2) {
                newPoints = positions(newPoints, currentTetromino, 1, 2);
                if(validation(newPoints, 2)) break;

            }
            if(i == 3) {
                newPoints = positions(newPoints, currentTetromino, -2, -1);
                if(validation(newPoints, 2)) break;
                else {
                    currentTetromino = oldTetromino;
                }
            }
            this.validPosition = true;
            newPoints.clear();
        }
        return currentTetromino;
    }

    public Tetromino iTests_1to0(Tetromino currentTetromino, Tetromino oldTetromino, ArrayList<Tetromino> activeTetrominos){
        this.currentTetromino = currentTetromino;
        this.activeTetrominos = activeTetrominos;
        ArrayList<Point2D> newPoints = new ArrayList<>();
        this.validPosition = true;
        for(int i = 0; i < 4; i++){
            if(i == 0){
                newPoints = positions(newPoints, currentTetromino, 2, 0);
                if(validation(newPoints, 0)) break;
            }
            if(i == 1) {
                newPoints = positions(newPoints, currentTetromino, -1, 0);
                if(validation(newPoints, 0)) break;
            }
            if(i == 2) {
                newPoints = positions(newPoints, currentTetromino, 2, -1);
                if(validation(newPoints, 0)) break;

            }
            if(i == 3) {
                newPoints = positions(newPoints, currentTetromino, -1, 2);
                if(validation(newPoints, 0)) break;
                else {
                    currentTetromino = oldTetromino;
                }
            }
            this.validPosition = true;
            newPoints.clear();
        }
        return currentTetromino;
    }

}
