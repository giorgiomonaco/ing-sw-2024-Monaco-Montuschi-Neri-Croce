package it.polimi.ingsw.client.view.TUI.TuiViews;

import java.io.Serializable;

public class DisconnectionView implements TuiView, Serializable {
    @Override
    public void play() {
        System.out.println("YOU HAVE DISCONNECTED");
    }
}
