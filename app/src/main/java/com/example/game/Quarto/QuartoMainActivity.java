package com.example.game.Quarto;

import android.content.pm.ActivityInfo;

import java.util.ArrayList;
import com.example.game.GameFramework.GameMainActivity;
import com.example.game.GameFramework.LocalGame;
import com.example.game.GameFramework.gameConfiguration.GameConfig;
import com.example.game.GameFramework.gameConfiguration.GamePlayerType;
import com.example.game.GameFramework.infoMessage.GameState;
import com.example.game.GameFramework.players.GamePlayer;
import com.example.game.Quarto.infoMessage.QuartoState;
import com.example.game.Quarto.players.QuartoComputerPlayer;
import com.example.game.Quarto.players.QuartoHumanPlayer;
import com.example.game.Quarto.players.QuartoSmartComputer;
import com.example.game.R;


public class QuartoMainActivity extends GameMainActivity {
    // create a port number for quarto to use when playing local network games
    private static final int PORT_NUMBER = 5555;

    @Override
    public GameConfig createDefaultConfig() {
        // define player types for quarto
        ArrayList<GamePlayerType> playerTypes = new ArrayList<>();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // two player types allowed (human and computer)
        // add human player type to the list
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new QuartoHumanPlayer(name, R.layout.quarto_main);
            }
        });

        // add computer player type to the list
        playerTypes.add(new GamePlayerType("Computer Player (Easy)") {
            public GamePlayer createPlayer(String name) {
                return new QuartoComputerPlayer(name);
            }
        });

        playerTypes.add(new GamePlayerType("Computer Player (Hard)") {
            public GamePlayer createPlayer(String name) {
                return new QuartoSmartComputer(name);
            }
        });

        // create GameConfig object for the quarto game
        GameConfig config = new GameConfig(playerTypes, 2, 2, "Quarto", PORT_NUMBER);

        // add a human player to the config and set it as player 1
        config.addPlayer("Human",0);

        // add a computer player to the config and set it as player 2
        config.addPlayer("Computer",1);

        //add a smart computer player to the config and set it as player 2
        config.addPlayer("Smart Computer", 1);

        config.setRemoteData("Remote Human Player", "", 0);
        return config;
    }

    @Override
    public LocalGame createLocalGame(GameState gs) {
        if (gs == null) {
            return new QuartoLocalGame();
        }
        return new QuartoLocalGame((QuartoState) gs);
    }
}