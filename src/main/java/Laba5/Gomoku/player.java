package Laba5.Gomoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class player extends JFrame implements ActionListener, IConnection {
    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new player();
            }
        });
    }

    private final JTextArea wLog = new JTextArea();
    private final JTextField wInput= new JTextField();

    private Connection connection;

    private player(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        int wWidth = 1280;
        int wHeight = 720;
        setBounds(dimension.width/2 - wWidth/2, dimension.height/2 - wHeight/2, wWidth, wHeight);
        setAlwaysOnTop(true);
        wLog.setEditable(false);
        wLog.setLineWrap(true);

        add(wLog, BorderLayout.NORTH);
        add(wInput, BorderLayout.SOUTH);
        wInput.addActionListener(this);

        setVisible(true);
        try {
            connection = new Connection(this, "localhost", 8030);
        } catch (IOException e) {
            printMsg("Connection exception" + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = wInput.getText();
        if(msg.equals("")) return;
        wInput.setText(null);
        connection.SendMsg(msg);
    }

    @Override
    public void UserConnection(Connection connection) {
        printMsg("Connection ready...");
    }

    @Override
    public void UserDisconnection(Connection connection) {
        printMsg("Connection close");
    }

    @Override
    public void UserResponse(Connection connection, String request) {
        printMsg(request);
    }

    @Override
    public void UserException(Connection connection, Exception e) {
        printMsg("Connection exception" + e);
    }

    private synchronized void printMsg(String msg){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                wLog.append(msg + "\n");
                wLog.setCaretPosition(wLog.getDocument().getLength());
            }
        });
    }
}