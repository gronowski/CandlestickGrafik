import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Daten {
  //double data[]=new double[33000];
  
  List<Zeile> ar=new ArrayList<Zeile>();
  
  //public  void einlesen(double data[]){
  public  List<Zeile> einlesen(){
    String zeile="";
    String[] tokens;
    
    Date d=new Date();
    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    df.setTimeZone(TimeZone.getDefault());
    String DatumsString=df.format(d);
      
    String dateiName="crop_"+DatumsString+".csv";
    //zum Test:
    dateiName="ExcelDownload.csv";
      
    try {
      BufferedReader br=new BufferedReader(new FileReader(new File(dateiName)));
      int zahler=0;
      while (zeile!=null){
        zeile=br.readLine();
        if (zeile!=null && zahler>10){  //erste 8 Zeilen �berlesen wegen Struktur
          System.out.println("Zeile: "+zeile);

          tokens=zeile.split(";");

          //for (String s:tokens) {
            // System.out.println(s);
            //}

            //Datenreihenfolge close, volumen, open, high, low
           ar.add(new Zeile(tokens[0],Double.parseDouble(tokens[1]),Double.parseDouble(tokens[2]),Double.parseDouble(tokens[3]),Double.parseDouble(tokens[4]),Double.parseDouble(tokens[5])));
           //System.out.println("Token: "+tokens[1]);
        }
         zahler++;
      }//ende while
       br.close();
    } catch (IOException e){
      e.printStackTrace();
    }
    return ar;
  }
  
}