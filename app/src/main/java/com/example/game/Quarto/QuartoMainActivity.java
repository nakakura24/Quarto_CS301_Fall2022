package com.example.game.Quarto;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.GameFramework.GameMainActivity;
import com.example.game.GameFramework.LocalGame;
import com.example.game.GameFramework.gameConfiguration.GameConfig;
import com.example.game.GameFramework.infoMessage.GameState;
import com.example.game.R;

public class QuartoMainActivity extends GameMainActivity {

    @Override
    public GameConfig createDefaultConfig() {
        return null;
    }

    /**
     * createLocalGame
     *
     * Creates a new game that runs on the server tablet,
     * @param gameState
     *              The desired gameState to start at or null for new game
     *
     * @return a new, game-specific instance of a sub-class of the LocalGame class.
     */
    @Override
    public LocalGame createLocalGame(GameState gameState) {
        if(gameState == null)
            return new QuartoLocalGame();
        return new QuartoLocalGame((QuartoState) gameState);
    }


}