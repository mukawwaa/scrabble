package com.gamecity.scrabble.controller;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.gamecity.scrabble.Constants;
import com.gamecity.scrabble.Resources;
import com.gamecity.scrabble.api.model.BoardPlayer;
import com.gamecity.scrabble.model.NotificationKey;

@Controller
@RequestMapping("/players")
public class PlayerController extends BaseController implements MessageListener
{
    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);
    private final Map<DeferredResult, NotificationKey> players = new ConcurrentHashMap<DeferredResult, NotificationKey>();

    @RequestMapping(value = "/board/{boardId}/orderNo/{orderNo}", method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult getPlayers(@PathVariable Long boardId, @PathVariable Integer orderNo)
    {
        final DeferredResult deferredResult = new DeferredResult(Constants.ASYNCHRONOUS_REQUEST_DURATION, Collections.emptyList());
        players.put(deferredResult, new NotificationKey(boardId, orderNo));

        deferredResult.onCompletion(new Runnable() {
            @Override
            public void run()
            {
                players.remove(deferredResult);
            }
        });

        BoardPlayer player = getByCriteria(Resources.ContentResource.players, BoardPlayer.class, boardId, orderNo);
        if (player != null)
        {
            deferredResult.setResult(player);
        }

        return deferredResult;
    }

    @Override
    public void onMessage(Message message, byte[] pattern)
    {
        try
        {
            for (Entry<DeferredResult, NotificationKey> entry : players.entrySet())
            {
                BoardPlayer player =
                    getNotificationByCriteria(
                        Resources.ContentResource.players, BoardPlayer.class, entry.getValue().getBoardId(), entry.getValue().getOrderNo());
                if (player != null)
                {
                    entry.getKey().setResult(player);
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Exception : {} {}", e.getMessage(), e);
        }
    }
}
