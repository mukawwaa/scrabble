package com.gamecity.scrabble;

import com.gamecity.scrabble.model.api.Board;
import com.gamecity.scrabble.model.api.User;

public interface Resources
{
    String get = "/get/{id}";
    String list = "/list";
    String save = "/";
    String remove = "/remove/{id}";

    public enum Services
    {
        user("/users", User.class), /**/
        board("/boards", Board.class), /**/
        content("/content/board/{boardId}"), /**/
        chat("/chat/board/{boardId}"), /**/
        game("/game");

        public String resource;
        public Class clazz;

        private Services(String resource)
        {
            this(resource, null);
        }

        private Services(String resource, Class clazz)
        {
            this.resource = resource;
            this.clazz = clazz;
        }
    }

    interface UserResource
    {
        String loadByUsername = Services.user.resource + "/{username}";
    }

    interface BoardResource
    {
        String create = Services.board.resource + "/create";
        String join = Services.board.resource + "/{boardId}/user/{userId}/join";
        String leave = Services.board.resource + "/{boardId}/user/{userId}/leave";
        String activeBoards = Services.board.resource + "/active";
        String myBoards = Services.board.resource + "/user/{userId}/active";
    }

    interface GameResource
    {
        String play = Services.game.resource + "/play";
    }

    interface ContentResource
    {
        String rack = Services.content.resource + "/rack/user/{userId}";
        String players = Services.content.resource + "/players/orderNo/{orderNo}";
        String content = Services.content.resource + "/orderNo/{orderNo}";
    }

    interface ChatResource
    {
        String send = Services.chat.resource + "/user/{userId}/send";
        String messages = Services.chat.resource + "/messages/{orderNo}";
    }
}
