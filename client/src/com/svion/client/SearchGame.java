package com.svion.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;
import com.codebutler.android_websockets.WebSocketClient;
import omsu.svion.MessagesHandlersResolver;
import omsu.svion.game.handler.MessageFromServerHandler;
import omsu.svion.game.handler.impl.ChooseThemeAndCostSearchingHandler;
import omsu.svion.game.handler.impl.GameUpdateStateMessageSearchingHandler;
import omsu.svion.game.handler.impl.GameUpdateStateMessageWaitingHandler;
import omsu.svion.messages.*;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.CookieStore;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * Created by victor on 14.03.14.
 */
public class SearchGame extends Activity {
    public static SearchGame main;
    ProgressBar myProgressBar;
    int myProgress = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        main = this;
        MessagesHandlersResolver.getHandlers().clear();
        MessagesHandlersResolver.getHandlers().put(GameStateUpdateMessage.class, new MessageFromServerHandler[]{new GameUpdateStateMessageSearchingHandler(this)});
        MessagesHandlersResolver.getHandlers().put(ChooseThemeAndCostRequestMessage.class, new MessageFromServerHandler[]{new ChooseThemeAndCostSearchingHandler(this)});
        setContentView(R.layout.search_game);
        Websockethandler.openWebosket(getResources().getString(R.string.websocket_url));
     }
    @Override
    public void onBackPressed() {
        Websockethandler.closeWebsocket();
        if (SearchGame.main != null) {
            SearchGame.main.finish();
        }
        if (WaitingForEnoughPlayersActivity.main != null) {
            WaitingForEnoughPlayersActivity.main.finish();
        }
        if (GameActivity.main != null) {
            GameActivity.main.finish();
        }
        if (QuestionActivity.main != null) {
            QuestionActivity.main.finish();
        }
    }

}