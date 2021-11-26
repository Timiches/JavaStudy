package Laba5.Gomoku;

public interface IConnection {
    void UserConnection(Connection connection);
    void UserDisconnection(Connection connection);
    void UserResponse(Connection connection, String request);
    void UserException(Connection connection, Exception e);
}
