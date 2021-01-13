package GUI;

import Controlls.Controller;
import Tetrominos.*;
import javafx.geometry.Point2D;
import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Draw {
    private int linesCleared;
    private int lastLinesCleared;

    private ArrayList<Tetromino> activePieces;

    private final int CANVAS_WIDTH = 1000;
    private final int CANVAS_HEIGHT = 850;
    private final int TOP_DISTANCE = 20;
    private final int SIDE_DISTANCE = 350;
    private final int FUTURE_PIECE_X = 650;

    private int lastScore;

    private long timer;


    public Draw(){
        this.linesCleared = 0;
        this.lastScore = 0;
        this.activePieces = new ArrayList<>();
        this.timer = System.currentTimeMillis();
    }

    public void drawTetrominos(FXGraphics2D graphics, Tetromino currentTetromino, ArrayList<Tetromino> activeTetrominos) {
        if(currentTetromino != null) {
            Point2D drawCurrentTetromino;
            Tetromino ghostTetromino = null;
            switch (currentTetromino.getPieceName()) {
                case "i" :
                    ghostTetromino = new ITetromino();
                    ghostTetromino.setShape(currentTetromino.getShape());
                    break;
                case "j" :
                    ghostTetromino = new JTetromino();
                    ghostTetromino.setShape(currentTetromino.getShape());
                    break;
                case "l" :
                    ghostTetromino = new LTetromino();
                    ghostTetromino.setShape(currentTetromino.getShape());
                    break;
                case "o" :
                    ghostTetromino = new OTetromino();
                    ghostTetromino.setShape(currentTetromino.getShape());
                    break;
                case "s" :
                    ghostTetromino = new STetromino();
                    ghostTetromino.setShape(currentTetromino.getShape());
                    break;
                case "t" :
                    ghostTetromino = new TTetromino();
                    ghostTetromino.setShape(currentTetromino.getShape());
                    break;
                case "z" :
                    ghostTetromino = new ZTetromino();
                    ghostTetromino.setShape(currentTetromino.getShape());
            }
            Controller ghostController = new Controller(ghostTetromino, activePieces);

            Image image = null;
            try {
                File path = new File("resources\\ghostPiece.png");
                image = ImageIO.read(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(image != null && ghostTetromino != null) {
                ghostTetromino.setImage(image);
            }
            ghostTetromino = ghostController.pressSpace();
            Point2D drawGhostTetromino;
            for(int i = 0; i < 4; i++) {
                drawGhostTetromino = (Point2D)ghostTetromino.getShape().get(i);
                graphics.drawImage(ghostTetromino.getImage(), (int)drawGhostTetromino.getX() * 30 + SIDE_DISTANCE, (int)drawGhostTetromino.getY() * 30 + TOP_DISTANCE, 30, 30, null);
            }
            for(int i = 0; i < 4; i++) {
                drawCurrentTetromino = (Point2D)currentTetromino.getShape().get(i);
                graphics.drawImage(currentTetromino.getImage(), (int)drawCurrentTetromino.getX() * 30 + SIDE_DISTANCE, (int)drawCurrentTetromino.getY() * 30 + TOP_DISTANCE, 30, 30, null);
            }
        }

        if(!activeTetrominos.isEmpty()) {
            for(Tetromino tetromino : activeTetrominos) {
                Point2D drawTetromino;
                for(int i = 0; i < 4; i++) {
                    drawTetromino = (Point2D)tetromino.getShape().get(i);
                    graphics.drawImage(tetromino.getImage(), (int)drawTetromino.getX() * 30 + SIDE_DISTANCE, (int)drawTetromino.getY() * 30 + TOP_DISTANCE, 30, 30, null);
                }
            }
        }
    }

    public void drawStage(FXGraphics2D graphics) {
        //draws the stage
        Rectangle2D playField = new Rectangle2D.Double(SIDE_DISTANCE, TOP_DISTANCE, 300, 600);
        Rectangle2D holdBox = new Rectangle2D.Double(SIDE_DISTANCE - 120, TOP_DISTANCE + 20, 117, 70);

        graphics.setColor(Color.white);
        graphics.fill(new RoundRectangle2D.Double(SIDE_DISTANCE - 123, TOP_DISTANCE, 123, 93, 10, 10));
        graphics.fill(new RoundRectangle2D.Double(SIDE_DISTANCE - 3, TOP_DISTANCE + 5, 306, 598, 5, 5));

        graphics.fill(new RoundRectangle2D.Double(FUTURE_PIECE_X, TOP_DISTANCE, 120, 500, 5, 5));
        graphics.setColor(Color.BLACK);
        graphics.fill(new Rectangle2D.Double(FUTURE_PIECE_X + 3, TOP_DISTANCE + 20, 114, 477));


        graphics.fill(holdBox);
        graphics.draw(playField);
        graphics.fill(playField);
    }


    //Draws all the Hold Tetrominos, using fillHolds
    public void drawHoldTetromino(FXGraphics2D graphics, Controller controller){
        if(controller.getHoldTetromino() != null) {
            switch (controller.getHoldTetromino().getPieceName()) {
                case "i" :
                    fillHolds(new ITetromino(), graphics);
                    break;

                case "j" :
                    fillHolds(new JTetromino(), graphics);
                    break;

                case "l" :
                    fillHolds(new LTetromino(), graphics);
                    break;

                case "o" :
                    fillHolds(new OTetromino(), graphics);
                    break;

                case "s" :
                    fillHolds(new STetromino(), graphics);
                    break;

                case "t" :
                    fillHolds(new TTetromino(), graphics);
                    break;

                case "z" :
                    fillHolds(new ZTetromino(), graphics);

            }
        }

    }


    //draws the next tetrominos using fillNext
    public void drawNextTetrominos(FXGraphics2D graphics, Bag bag) {
        if(bag != null) {
            ArrayList<Tetromino> drawNext = bag.getNext();
            for(int i = 0; i < 5; i++) {
                fillNext(drawNext.get(i), i, graphics);
            }
        }
    }

    //Draws the score
    public void drawScore(FXGraphics2D graphics, Controller controller, int score, int combo) {
        Font ScoreFont = new Font("ScoreFont", Font.BOLD, 20);
        int fontY = 720;
        graphics.setFont(ScoreFont);
        graphics.setColor(Color.white);
        if(score > this.lastScore) {
            drawScoreBox(graphics);
        }
        if(score < 10) graphics.drawString(score + "", (CANVAS_WIDTH / 2) - (ScoreFont.getSize() / 4), fontY);
        else if(score < 100) graphics.drawString(score + "", (CANVAS_WIDTH / 2) - 2 * (ScoreFont.getSize() / 4) -1 , fontY);
        else if(score < 1000) graphics.drawString(score + "", (CANVAS_WIDTH / 2) - 3 * (ScoreFont.getSize() / 4) -2, fontY);
        else if(score < 10000) graphics.drawString(score + "", (CANVAS_WIDTH / 2) - 4 * (ScoreFont.getSize() / 4) -3, fontY);
        else if(score < 100000) graphics.drawString(score + "", (CANVAS_WIDTH / 2) - 5 * (ScoreFont.getSize() / 4) -5, fontY);
        else graphics.drawString(score + "", (CANVAS_WIDTH / 2) - 6 * (ScoreFont.getSize() / 4 - 7) , fontY);

        this.lastScore = score;

        ScoreFont = new Font("ScoreFont", Font.BOLD, 40);
        graphics.setFont(ScoreFont);
        graphics.drawString((int)(Math.floor(controller.getLinesCleared()/10) + 1) + "", (CANVAS_WIDTH / 2) - (ScoreFont.getSize() / 4), fontY + 60 );

        if(combo > 1) {
            ScoreFont = new Font("ScoreFont", Font.BOLD, 30);
            graphics.setFont(ScoreFont);
            graphics.drawString(combo + "", 210, 200);
            ScoreFont = new Font("ScoreFont", Font.PLAIN, 30);
            graphics.setFont(ScoreFont);
            graphics.drawString("combo", (int)(240 + (combo * 1.2) / 4), 200);
        }

    }

    //Fills the hold block in the upper left of screen
    public void fillHolds(Tetromino tetromino, FXGraphics2D graphics){
        Point2D drawHoldTetromino;
        for(int i = 0; i < 4; i++) {
            drawHoldTetromino = (Point2D)tetromino.getShape().get(i);
            if(tetromino.getPieceName().equals("i")) {
                graphics.drawImage(tetromino.getImage(), (int)(drawHoldTetromino.getX() + 1) * 23 + SIDE_DISTANCE - 199, (int)(drawHoldTetromino.getY() + 1) * 23 + 62, 23, 23, null);
            }
            else if(tetromino.getPieceName().equals("o")) {
                graphics.drawImage(tetromino.getImage(), (int)(drawHoldTetromino.getX() + 1) * 23 + SIDE_DISTANCE - 199, (int)(drawHoldTetromino.getY() + 1) * 23 + 50, 23, 23, null);
            }
            else graphics.drawImage(tetromino.getImage(), (int)(drawHoldTetromino.getX() + 1) * 23 + SIDE_DISTANCE - 211, (int)(drawHoldTetromino.getY() + 1) * 23 + 50, 23, 23, null);
        }
    }

    //Fills the next blocks right of the screen
    public void fillNext(Tetromino tetromino, Integer position, FXGraphics2D graphics){
        Point2D drawNextTetromino;
        for(int i = 0; i < 4; i++) {
            drawNextTetromino = (Point2D)tetromino.getShape().get(i);
            if(tetromino.getPieceName().equals("i")) {
                graphics.drawImage(tetromino.getImage(), (int)(drawNextTetromino.getX() + 1) * 23 + 572, (int)((drawNextTetromino.getY() + 1) * 23) + (position * 90) + 97, 23, 23, null);
            }
            else if(tetromino.getPieceName().equals("o")){
                graphics.drawImage(tetromino.getImage(), (int)(drawNextTetromino.getX() + 1) * 23 + 572, (int)((drawNextTetromino.getY() + 1) * 23) + (position * 90) + 85, 23, 23, null);
            }
            else {
                graphics.drawImage(tetromino.getImage(), (int)(drawNextTetromino.getX() + 1) * 23 + 560, (int)((drawNextTetromino.getY() + 1) * 23) + (position * 90) + 85, 23, 23, null);

            }
        }
    }

    public void drawNewLines(FXGraphics2D graphics, Controller controller, ArrayList<Tetromino> activePieces){
        if(System.currentTimeMillis() - this.timer > 2000) {
            drawComboBox(graphics);
            this.timer = System.currentTimeMillis();
            this.lastLinesCleared = 0;
        }
        Font ScoreFont = new Font("ScoreFont", Font.PLAIN, 18);
        graphics.setColor(Color.white);
        if(this.activePieces.size() != activePieces.size()){
            graphics.setFont(ScoreFont);
            switch (controller.getLinesCleared() - this.linesCleared) {
                case (0) :
                case (1) :
                    this.lastLinesCleared = 0;
                    break;
                case (2) :
                    drawComboBox(graphics);
                    graphics.drawString("DOUBLE!", 180, 280);
                    this.lastLinesCleared = 2;
                    this.timer = System.currentTimeMillis();
                    break;
                case (3) :
                    drawComboBox(graphics);
                    graphics.drawString("TRIPLE!", 180, 280);
                    this.lastLinesCleared = 3;
                    this.timer = System.currentTimeMillis();
                    break;
                case (4) :
                    drawComboBox(graphics);
                    graphics.drawString("TETRIS!", 180, 280);
                    this.lastLinesCleared = 4;
                    this.timer = System.currentTimeMillis();
                    break;
            }
        }
        else {
            switch (this.lastLinesCleared) {
                case (0) :
                case (1) :
                    break;
                case (2) :
                    drawComboBox(graphics);
                    graphics.drawString("DOUBLE!", 180, 280);
                    break;
                case (3) :
                    drawComboBox(graphics);
                    graphics.drawString("TRIPLE!", 180, 280);
                    break;
                case (4) :
                    drawComboBox(graphics);
                    graphics.drawString("TETRIS!", 180, 280);
                    break;
            }
        }
        this.activePieces = new ArrayList<Tetromino>(activePieces);
        this.linesCleared = controller.getLinesCleared();
    }

    public void drawBackGround(FXGraphics2D graphics){
        Image image = null;
        try {
            File path = new File("resources\\Background.jpg");
            image = ImageIO.read(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(image != null) {
            graphics.drawImage(image, 0, 0, CANVAS_WIDTH, CANVAS_HEIGHT, null);
        }
    }

    public void drawLittleBackGround(FXGraphics2D graphics) {
        Image image = null;
        try {
            File path = new File("resources\\LittleBackGround.jpg");
            image = ImageIO.read(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(image != null) {
            graphics.drawImage(image, SIDE_DISTANCE, 0, 298, 20, null);
        }
    }

    public void drawScoreBox(FXGraphics2D graphics){
        Image image = null;
        try {
            File path = new File("resources\\ScoreBG.png");
            image = ImageIO.read(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(image != null) {
            graphics.drawImage(image, 400, 680, 200, 100, null);
        }
    }

    public void drawComboBox(FXGraphics2D graphics){
        Image image = null;
        try {
            File path = new File("resources\\ComboBG.png");
            image = ImageIO.read(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(image != null) {
            graphics.drawImage(image, 150, 130, 197, 200, null);
        }
    }

}
