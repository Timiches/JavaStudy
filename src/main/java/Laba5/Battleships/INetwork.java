package Laba5.Battleships;

public interface INetwork {
    void IConnection(Network network);
    void IDisconnection(Network network);
    void IResponse(Network network, int x, int y, int mark, int playerID, int winnerID);
    void IException(Network network, Exception e);
}
