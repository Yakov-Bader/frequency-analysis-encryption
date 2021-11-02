import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;

public class MainPanel extends JPanel {
    JLabel con,conText;
    JButton change,convert;
    JTextField from,to;
    JTextArea input, textArea;
    LetterArray letter=new LetterArray();
    public MainPanel() {

        String in="input text here";
        input=new JTextArea(in,20,30);
        input.setLineWrap(true);
        input.setWrapStyleWord(true);
        input.setEditable(true);
        JScrollPane inScroll = new JScrollPane(input);
        inScroll.setBounds(10, 11, 455, 249);
        add(inScroll);

        convert=new JButton("convert");
        ButtonListener listener = new ButtonListener();
        convert.addActionListener (listener);
        add(convert);

        textArea=new JTextArea(letter.toText,30,40);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBounds(10, 11, 455, 249);

        add(scroll);
        setPreferredSize (new Dimension(2800, 2800));

        setFocusable(true);

        con=new JLabel("enter here the letter you want to change ");
        conText=new JLabel("enter the letter you want to change to ");
        from= new JTextField(1);
        to= new JTextField(1);
        add(con);
        add(from);
        add(conText);
        add(to);
        change = new JButton("change");
        change.addActionListener(listener);
        add(change);
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed (ActionEvent event) {
            letter.toText="";
            textArea.setText(letter.toText);
            if(event.getActionCommand().equals("change")){
                for (int i=0;i<letter.result.length;i++){
                    if(letter.result[i]==from.getText().charAt(0)){
                        letter.result[i]=to.getText().charAt(0);
                    }else if(letter.result[i]==to.getText().charAt(0)){
                        letter.result[i]=from.getText().charAt(0);
                    }
                }
            }else if(input.getText().equals("input text here")){
                try {
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
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                int i=0;
                while (i<input.getText().length()){
                    int let= input.getText().charAt(i);
                    if (let>=65&&let<=90) {
                        let+=32;
                        letter.sorted[let-97]++;
                        letter.letterArray[let-97]++;
                    } else if(let>=97&&let<=122){
                        letter.letterArray[let-97]++;
                        letter.sorted[let-97]++;
                    }else{
                        i++;
                        continue;
                    }
                    i++;
                }
            }

            if(!event.getActionCommand().equals("change")){
                Arrays.sort(letter.sorted);
                for(int i=0;i<letter.sorted.length;i++){
                    findLetter(i, letter);
                }
            }

            for(int j=0;j<letter.result.length;j++){
                System.out.println("sorted at "+j+" "+(char)letter.result[j]);
            }
            try {
                fillFile(letter,input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            textArea.setText(letter.toText);
        }
    }
    public static void fillFile(LetterArray letter,JTextArea input) throws IOException {
        if(input.getText().equals("input text here")){
            File file=new File("text.txt");
            FileWriter myWriter = new FileWriter("filename.txt");
            FileReader fr =new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            int c;
            while(( c = br.read()) != -1) {
                for(int i=0;i<letter.result.length;i++){
                    if (c>=97&&c<=122) {
                        if(c==letter.result[i]){
                            letter.toText += letter.frequency[i];
                            myWriter.write(letter.frequency[i]);
                        }
                    }else if(c>=65&&c<=90) {
                        c += 32;
                        if (c==letter.result[i]) {
                            letter.toText+=letter.frequency[i];
                            myWriter.write(letter.frequency[i]);
                        }
                    }
                }
            }
            myWriter.close();
        }else{
            int j=0;
            while (j<input.getText().length()){
                int let= input.getText().charAt(j);
                for(int i=0;i<letter.result.length;i++){
                    if (let>=97&&let<=122) {
                        if(letter.result[i]==let){
                            letter.toText+=letter.frequency[i];
                        }
                    }else if(let>=65&&let<=90) {
                        let += 32;
                        if (letter.result[i] == let) {
                            letter.toText+=letter.frequency[i];
                        }
                    }
                }
                j++;
            }
        }
    }
    public static void findLetter(int i,LetterArray letter){
        for(int j=0;j<letter.letterArray.length;j++){
            if(letter.sorted[i]==letter.letterArray[j]){
                letter.result[i]=j+97;
            }
        }
    }
}
