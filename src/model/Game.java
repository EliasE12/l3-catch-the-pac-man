package model;

import model.PacMan.Direction;
import java.io.*;
import java.util.ArrayList;

// Clase
public class Game {

    // Constante
    public final static String PATH_DAT = "data/dat/info.dat";
    public final static int TAM_MAX_HALL = 10;

    // Atributos
    private ArrayList<PacMan> listPacMans;
    private Score[] scores;
    private int level;

    // Constructor
    public Game() {
        listPacMans = new ArrayList<PacMan>();
        scores = new Score[TAM_MAX_HALL];
        level = 0;
    }

    // Métodos
    public void loadGame() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(PATH_DAT)));
            listPacMans = (ArrayList<PacMan>) ois.readObject();
            ois.close();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void saveGame() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(PATH_DAT)));
        oos.writeObject(listPacMans);
        oos.close();
    }

    public void importLevel(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        while (line != null) {
            int count = 0;
            if (line.charAt(0) != '#') {
                if(level== 0 && count<=1){
                    level = Integer.parseInt(line);
                    count++;
                }else{
                    String[] parts = line.split(";");
                        double radius = Double.parseDouble(parts[0]);
                        double posX = Double.parseDouble(parts[1]);
                        double posY = Double.parseDouble(parts[2]);
                        int wTime = Integer.parseInt(parts[3]);

                        Direction direction ;
                        if (parts[4].equals("TOP")) {
                            direction = Direction.TOP;
                        } else if (parts[4].equals("RIGTH")) {
                            direction = Direction.RIGTH;
                            System.out.println("entre");
                            System.out.println(direction);
                        } else if (parts[4].equals("BUTTOM")) {
                            direction = Direction.BUTTOM;
                        } else {
                            direction = Direction.LEFT;
                        }
                    System.out.println(direction);
                        int rebound = Integer.parseInt(parts[5]);
                        boolean isStop = Boolean.parseBoolean(parts[6]);
                        PacMan pacMan = new PacMan(radius, posX, posY, wTime, direction, rebound, isStop);
                        listPacMans.add(pacMan);
                    }
            }
            line = br.readLine();
        }
        br.close();
    }

    public ArrayList<PacMan> getListPacMans() {
        return listPacMans;
    }

    public void setListPacMans(ArrayList<PacMan> listPacMans) {
        this.listPacMans = listPacMans;
    }

    public Score[] getScores() {
        return scores;
    }

    public void setScores(Score[] scores) {
        this.scores = scores;
    }


}
