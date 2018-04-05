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
import com.gamecity.scrabble.api.model.BoardContent;
import com.gamecity.scrabble.model.NotificationKey;

@Controller
@RequestMapping("/content")
public class ContentController extends BaseController implements MessageListener
{
    private static final Logger logger = LoggerFactory.getLogger(ContentController.class);
    private final Map<DeferredResult, NotificationKey> contents = new ConcurrentHashMap<DeferredResult, NotificationKey>();

    @RequestMapping(value = "/board/{boardId}/orderNo/{orderNo}", method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult getBoardContent(@PathVariable Long boardId, @PathVariable Integer orderNo)
    {
        final DeferredResult deferredResult = new DeferredResult(Constants.ASYNCHRONOUS_REQUEST_DURATION, Collections.emptyList());
        contents.put(deferredResult, new NotificationKey(boardId, orderNo));

        deferredResult.onCompletion(new Runnable()
        {
            @Override
            public void run()
            {
                contents.remove(deferredResult);
            }
        });

        BoardContent content = getByCriteria(Resources.ContentResource.content, BoardContent.class, boardId, orderNo);
        if (content != null)
        {
            deferredResult.setResult(content);
        }

        return deferredResult;
    }

    @Override
    public void onMessage(Message message, byte[] pattern)
    {
        try
        {
            for (Entry<DeferredResult, NotificationKey> entry : contents.entrySet())
            {
                BoardContent content = getNotificationByCriteria(Resources.ContentResource.content, BoardContent.class, entry.getValue().getBoardId(),
                        entry.getValue().getOrderNo());
                if (content != null)
                {
                    entry.getKey().setResult(content);
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Exception : {} {}", e.getMessage(), e);
        }
    }
}
