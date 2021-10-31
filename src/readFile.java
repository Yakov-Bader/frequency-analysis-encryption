import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Arrays;

public class readFile {

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame ("encryption");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MainPanel());
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(2100,2100));
        frame.pack();

    }

}
