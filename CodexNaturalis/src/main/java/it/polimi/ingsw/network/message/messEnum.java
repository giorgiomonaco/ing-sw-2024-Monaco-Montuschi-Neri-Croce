package it.polimi.ingsw.network.message;

public enum messEnum {
    CONNECTION_ACTIVE,
    LOGIN_REQUEST,
    LOGIN_RESPONSE,
    SELECT_NUM_PLAYERS,
    SELECTION_NUM_PLAYERS,
    WAITING_FOR_LOBBY,
    LOBBY,
    PING,
    REJECTED,
    ALREADY_STARTED,
    SHOW_CARD,
    SHOW_HAND,
    DRAW_CARD_REQUEST,
    DRAW_CARD_RESPONSE,
    SHOW_PLAYER_RESOURCES,
    SHOW_PLAYER_BOARD,
    COMMON,
    NEW_PLAYER_JOIN,
    GAME_STARTING,
    FIRST_TURN,
    FIRST_TURN_RESPONSE,
    PLAY_CARD,
    SHOW_FIRST_CARD,
    SELECTION_TOKEN,
    YOUR_TURN,
    END_GAME
}
