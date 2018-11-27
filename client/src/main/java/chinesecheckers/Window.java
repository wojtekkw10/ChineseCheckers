package chinesecheckers;

import javax.swing.*;
import java.util.ArrayList;

abstract class Window extends JPanel{
    protected JFrame frame;
    protected ArrayList<Id> idList;

    abstract void display();

    public void setIdList(ArrayList<Id> idList) {
        this.idList = idList;
    }
}
