import javax.swing.*;
import java.awt.*;

class MyPanel extends JPanel{
     Daten daten=null;
     java.util.List<Zeile> ar;

     private double xgmax=0;
     private double xgmin=0;
     private double ygmax=0;
     private double ygmin=0;
     
     private double xwmax=0;
     private double xwmin=0;
     private double ywmax=0;
     private double ywmin=0;
     
     private double xgt=0;
     private double ygt=0;

   public MyPanel(){
      daten=new Daten();
      ar=daten.einlesen();
      skalieren();
   }

   void skalieren(){
      xgmin=0.0;
      ygmin=0.0;
      xgmax=800;
      ygmax=400;
      
      xwmin=0.0;
      xwmax=600;
      
      ywmin=120;
      ywmax=180;
      
      xgt=20.0;
      ygt=600.0;
   }

   int xg_berechnung(int xw){
     return (int)((xw-xwmin)*((xgmax-xgmin)/(xwmax-xwmin))+xgt);
   }

   
   int yg_berechnung(int yw){
     return (int)(ygt-(yw-ywmin)*((ygmax-ygmin)/(ywmax-ywmin)));
   }

   void candleStick(Graphics g, int x, int o, int c, int h, int l){
     /*Testlinie als Rahmen
        g.drawLine(xg_berechnung(0),yg_berechnung(0),xg_berechnung(100),yg_berechnung(0));
        //Testlinie 2
        g.drawLine(xg_berechnung(100),yg_berechnung(0),xg_berechnung(100),yg_berechnung(100));
         //Testlinie 3
        g.drawLine(xg_berechnung(100),yg_berechnung(100),xg_berechnung(0),yg_berechnung(100));
        //Testlinie 4
        g.drawLine(xg_berechnung(0),yg_berechnung(100),xg_berechnung( 0),yg_berechnung(0));
        //g.drawLine(0,0,100,100);
        //Rechteck ohne Skalierunng zum Test
        //g.drawRect(10,10,6,20);
     */
     
     if(o>c){
        g.drawLine(xg_berechnung(x)+3,yg_berechnung(h),xg_berechnung(x)+3,yg_berechnung(o));
        
        int hoehe= yg_berechnung(c)-yg_berechnung(o);
        //System.out.println("H�he: " +hoehe);
        g.fillRect(xg_berechnung(x),yg_berechnung(o),8,hoehe);
        
         System.out.println("x "+xg_berechnung(x));
         System.out.println("open "+xg_berechnung(o));
         System.out.println("close " +xg_berechnung(c));
        
        g.drawLine(xg_berechnung(x)+3,yg_berechnung(l),xg_berechnung(x)+3,yg_berechnung(c));
     }
     else{
        //c>o
        g.drawLine(xg_berechnung(x)+3,yg_berechnung(h),xg_berechnung(x)+3,yg_berechnung(c));

        int hoehe= yg_berechnung(o)-yg_berechnung(c);
        g.drawRect(xg_berechnung(x),yg_berechnung(c),8,hoehe);
        
        g.drawLine(xg_berechnung(x)+3,yg_berechnung(l),xg_berechnung(x)+3,yg_berechnung(o));
     }
   }

    @Override
    public void paintComponent(Graphics g){
      super.paintComponent(g);
     //bei Swing paintComponent ()
    //Zeilen in Liste einlesen

      //java.util.List<Zeile> ar=daten.einlesen();
      
      Zeile zeile = new Zeile();
      // Test: //  zeile = (Zeile) ar.get(0);
     //skalieren();

     int xalt=0;
     int yalt=0;
     
     int xzaehler=4;
     
     
     for (int i=ar.size()-1;i>0;i--){
      //for (int i=0;i<ar.size();i++){


        //Fehler, erkennt globales ar nicht, null Pointer exception
        //funket jetzt
        zeile=(Zeile) ar.get(i);
        
        candleStick(g,xzaehler,(int)zeile.getE(),(int)zeile.getC(),(int)zeile.getH(),(int)zeile.getT());
        
        //Kurslinie zeichnen
        g.drawLine(xg_berechnung(xalt),yg_berechnung(yalt+10), xg_berechnung(xzaehler),yg_berechnung((int)zeile.getE()+10));
        
        xalt=xzaehler;
        yalt=(int)zeile.getE();
        
        System.out.println(zeile.getE()+"/"+zeile.getC()+"/"+zeile.getH()+"/"+ zeile.getT());
        
        xzaehler+=10;
     }

    // y-Achse mit Beschriftung
        int minimumtickBeschriftung=130;
        int y0=130;
        for(int i=0;i<8;i++){    //Linien
          g.drawLine(xg_berechnung(30),yg_berechnung(y0),xg_berechnung(1600),yg_berechnung(y0));
          g.drawString(Integer.toString(minimumtickBeschriftung),xg_berechnung(10),yg_berechnung(y0));
          y0+=10;
          minimumtickBeschriftung+=10;

        }
     
     
      //x-Achse mit Beschriftung
      
       xzaehler=0;
       for (int i=ar.size()-1;i>0;i--){
           zeile=(Zeile) ar.get(i);
           if (xzaehler % 100==0){
              g.drawLine(xg_berechnung(xzaehler),yg_berechnung(130),xg_berechnung(xzaehler),yg_berechnung(280));
              g.drawString(zeile.getDatum().replaceAll("\"",""),xg_berechnung(xzaehler),yg_berechnung(125));
           }
          xzaehler+=10;
       }
       
       
      
      
      
      
      

     
   }

}


