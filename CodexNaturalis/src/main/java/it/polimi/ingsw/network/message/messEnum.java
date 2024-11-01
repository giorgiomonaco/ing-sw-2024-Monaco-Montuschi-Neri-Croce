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
    DRAW_CARD_REQUEST,
    DRAW_CARD_RESPONSE,
    COMMON,
    NEW_PLAYER_JOIN,
    GAME_STARTING,
    GAME_ABORTED,
    GAME_STOPPED,
    FIRST_TURN,
    SELECTION_TOKEN,
    SELECT_OBJECTIVE,
    SELECTION_OBJECTIVE,
    PLAY_CARD_REQUEST,
    SELECTION_CARD,
    SHOW_WINNER, WAIT_TURN,
    CHAT_MSG,
    CHAT_RESPONSE,
    SELECTION_FIRST_CARD,
    SELECT_FIRST_SIDE
}
