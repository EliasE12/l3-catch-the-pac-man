package thread;

import ui.CatchPacManController;

public class UpdateGUIRunnable implements Runnable{

    private CatchPacManController catchPacManController;

    public UpdateGUIRunnable(CatchPacManController catchPacManController){
        this.catchPacManController = catchPacManController;
    }
    @Override
    public void run() {
        catchPacManController.updatePacMans();

    }
}
