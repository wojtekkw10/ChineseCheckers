package chinesecheckers;

import javax.swing.*;

abstract class Window extends JPanel{
    protected JFrame frame;
    abstract void display();
}
