import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;

public class MainPanel extends JPanel {
    JTextArea textArea;
    JTextArea input;
    LetterArray letter=new LetterArray();
    public MainPanel() throws IOException {


        File file=new File("text.txt");
        FileReader fr =new FileReader(file);
        BufferedReader br=new BufferedReader(fr);
        int c;
        while(( c = br.read()) != -1) {
            if (c>=65&&c<=90) {
                c+=32;
                letter.sorted[c-97]++;
                letter.letterArray[c-97]++;
            }else if(c>=97&&c<=122){
                letter.letterArray[c-97]++;
                letter.sorted[c-97]++;
            }else{
                continue;
            }
            letter.sum++;
        }
        Arrays.sort(letter.sorted);

        for (int i=0;i<letter.letterArray.length;i++){
            letter.sorted[i]/= letter.sum;
            letter.letterArray[i]/= letter.sum;
        }

        for(int i=0;i<letter.sorted.length;i++){
            findLetter(i, letter);
        }
        fillFile(letter);
//        String in=" dghghmhgm  ";
//        input=new JTextArea(in,20,30);
//        textArea.setLineWrap(true);
//        textArea.setWrapStyleWord(true);
//        textArea.setEditable(true);
//        JScrollPane inScroll = new JScrollPane(input);
//        inScroll.setBounds(10, 11, 455, 249);

        textArea=new JTextArea(letter.toText,30,20);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBounds(10, 11, 455, 249);

        add(scroll);
        setPreferredSize (new Dimension(2800, 2800));
        setFocusable(true);

    }
    public static void fillFile(LetterArray letter) throws IOException {
        File file=new File("text.txt");
        FileWriter myWriter = new FileWriter("filename.txt");
        FileReader fr =new FileReader(file);
        BufferedReader br=new BufferedReader(fr);
        int c;
        while(( c = br.read()) != -1) {
            System.out.println("the cap "+(char)c);
            for(int i=0;i<letter.result.length;i++){
                if (c>=97&&c<=122) {
                    if(letter.result[i]==c){
                        letter.toText+=(char)(i+97);
                        myWriter.write((char)(i+97));
                    }
                }else if(c>=65&&c<=90) {
                    c += 32;
                    if (letter.result[i] == c) {
                        letter.toText+=(char)(i+97);
                        myWriter.write((char) (i + 97));
                    }
                }
            }
        }
        myWriter.close();
    }
    public static void findLetter(int i,LetterArray letter){
        for(int j=0;j<letter.letterArray.length;j++){
            if(letter.sorted[i]==letter.letterArray[j]){
                letter.result[j]=(char)(j+97);
            }
        }
    }
}
