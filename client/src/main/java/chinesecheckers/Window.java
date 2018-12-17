package chinesecheckers;

import javax.swing.*;
import java.util.ArrayList;

abstract class Window extends JPanel{
    protected JFrame frame;
    protected ArrayList<GameInfo> gameList;

    abstract void display();
}
