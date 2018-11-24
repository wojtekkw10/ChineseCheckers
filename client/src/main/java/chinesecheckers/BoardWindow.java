package chinesecheckers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardWindow extends Window{
    private ActionListener actionListener;
    private Board board;

    BoardWindow(ActionListener actionListener)
    {
        this.actionListener = actionListener;
    }

    //TODO: zaimplentowac wyglad okna + jego wyswietlanie (trzeba wyczyscic okno i namalowac od nowa)

    BoardWindow(Board board)
    {
        this.board = board;
    }

    void setBoard(Board board) {
        this.board = board;
    }

    void display(JFrame mainFrame)
    {

    }


}
