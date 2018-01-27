package com.gamecity.scrabble.view;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

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
import com.gamecity.scrabble.model.NotificationKey;
import com.gamecity.scrabble.model.api.ChatMessage;

@Controller
@RequestMapping("/chat")
public class ChatController extends BaseController implements MessageListener
{
    private final Map<DeferredResult<List>, NotificationKey> chatMessages = new ConcurrentHashMap<DeferredResult<List>, NotificationKey>();

    @RequestMapping(value = "/board/{boardId}/orderNo/{orderNo}", method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult getMessages(@PathVariable Long boardId, @PathVariable Integer orderNo)
    {
        final DeferredResult deferredResult = new DeferredResult(Constants.ASYNCHRONOUS_REQUEST_DURATION, Collections.emptyList());
        chatMessages.put(deferredResult, new NotificationKey(boardId, orderNo));

        deferredResult.onCompletion(new Runnable()
        {
            @Override
            public void run()
            {
                chatMessages.remove(deferredResult);
            }
        });

        List newMessages = listByCriteria(Resources.ChatResource.messages, ChatMessage.class, boardId, orderNo);
        if (newMessages != null && newMessages.size() > 0)
        {
            deferredResult.setResult(newMessages);
        }

        return deferredResult;
    }

    @Override
    public void onMessage(Message message, byte[] pattern)
    {
        for (Entry<DeferredResult<List>, NotificationKey> entry : chatMessages.entrySet())
        {
            List newMessages = listNotificationsByCriteria(Resources.ChatResource.messages, ChatMessage.class, entry.getValue().getBoardId(),
                    entry.getValue().getOrderNo());
            if (newMessages != null)
            {
                entry.getKey().setResult(newMessages);
            }
        }
    }
}
