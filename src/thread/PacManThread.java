package thread;

import javafx.geometry.Bounds;
import model.PacMan;
import ui.CatchPacManController;

public class PacManThread extends Thread{

    private CatchPacManController catchPacManController;
    private PacMan pacMan;
    private boolean isActivate = true;

    public PacManThread(PacMan game, CatchPacManController catchPacManController) {
        this.pacMan = game;
        this.catchPacManController = catchPacManController;
    }

    public void run() {
        Bounds bounds = catchPacManController.getBounds();
        while (isActivate) {
            pacMan.move(bounds.getMinX(),bounds.getMaxX(),bounds.getMinY(),bounds.getMaxY());
            try {
                Thread.sleep(pacMan.getWaitTime());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void deactivate(){
        isActivate = false;
    }

}
