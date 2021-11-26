package Laba5.TicTacToe.Prog.GUI;

public interface INetwork {
    void IConnection(Network network);
    void IDisconnection(Network network);
    void IResponse(Network network, int x, int sign, int winnerID);
    void IException(Network network, Exception e);
}
