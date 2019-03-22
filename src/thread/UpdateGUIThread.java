package thread;

import javafx.application.Platform;
import ui.CatchPacManController;

public class UpdateGUIThread extends Thread {

    public final static long SPEET_UPDATE = 4;

    private CatchPacManController catchPacManController;

    public UpdateGUIThread(CatchPacManController catchPacManController){
        this.catchPacManController = catchPacManController;
    }

    public void run(){
        while (true){
         UpdateGUIRunnable updateGUIRunnable = new UpdateGUIRunnable(catchPacManController);
         Platform.runLater(updateGUIRunnable);
         try {
             sleep(SPEET_UPDATE);
         }catch (InterruptedException e){
             e.printStackTrace();
         }

        }

    }

}
