package Tetrominos;

import java.util.ArrayList;
import java.util.Collections;

public class Bag {

    private ArrayList<Integer> bagContent;
    private ArrayList<Integer> bagCreater;

    public Bag(){
        bagContent = new ArrayList<>();
        bagCreater = new ArrayList<>();
        generateNewBag();
    }

    public void generateNewBag(){
        if(bagCreater != null) {
            bagCreater.clear();
            for(int i = 0; i < 7; i++) {
                bagCreater.add(i);
            }
            Collections.shuffle(bagCreater);
            bagContent.addAll(bagCreater);
        }

        System.out.print("New bag content: ");
        for(Integer integer: bagContent) {
            System.out.print(integer + " ");
        }
        System.out.println("");
    }

    public Tetromino generateTetromino() {
        if(bagContent.size() < 7) {
            generateNewBag();
        }
        switch (bagContent.get(0)) {

            case 1: bagContent.remove(0);
                    return new ITetromino();
            case 2: bagContent.remove(0);
                    return new JTetromino();
            case 3: bagContent.remove(0);
                    return new LTetromino();
            case 4: bagContent.remove(0);
                    return new OTetromino();
            case 5: bagContent.remove(0);
                    return new STetromino();
            case 6: bagContent.remove(0);
                    return new TTetromino();
            default: bagContent.remove(0);
                    return new ZTetromino();
        }


    }

    public ArrayList<Tetromino> getNext(){
        ArrayList<Tetromino> getNextArray = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            switch (bagContent.get(i)) {
                case 1:
                    getNextArray.add(new ITetromino());
                    break;
                case 2:
                    getNextArray.add(new JTetromino());
                    break;
                case 3:
                    getNextArray.add(new LTetromino());
                    break;
                case 4:
                    getNextArray.add(new OTetromino());
                    break;
                case 5:
                    getNextArray.add(new STetromino());
                    break;
                case 6:
                    getNextArray.add(new TTetromino());
                    break;
                default:
                    getNextArray.add(new ZTetromino());
                    break;
            }
        }
        return getNextArray;
    }
}
