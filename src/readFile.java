import java.io.*;
import java.util.Arrays;

public class readFile {
    public static void main(String[] args) throws IOException {
        LetterArray letter=new LetterArray();
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
    }
    public static void findLetter(int i,LetterArray letter){
        for(int j=0;j<letter.letterArray.length;j++){
            if(letter.sorted[i]==letter.letterArray[j]){
                letter.result[j]=(char)(j+97);
            }
        }
    }
    public static void fillFile(LetterArray letter) throws IOException {
        File file=new File("text.txt");
        FileWriter myWriter = new FileWriter("filename.txt");
        FileReader fr =new FileReader(file);
        BufferedReader br=new BufferedReader(fr);
        int c=0;
        while(( c = br.read()) != -1) {
            System.out.println("the cap "+(char)c);
            for(int i=0;i<letter.result.length;i++){
                if (c>=65&&c<=90) {
                    c+=32;
                    if(letter.result[i]==c){
                        myWriter.write((char)(i+97));
                    }
                }else if(c>=97&&c<=122){
                    if(letter.result[i]==c){
                        myWriter.write((char)(i+97));
                    }
                }else{
                    continue;
                }
            }

        }
        myWriter.close();
    }

}
