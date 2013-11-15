import javax.swing.*;
import java.awt.*;

//import java.awt.*;
class SwingTestGrafik {
MyPanel mp;
  SwingTestGrafik(){
    JFrame jfrm=new JFrame("Swing getestet");
    jfrm.setSize(800,600);
    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel jl=new JLabel("Swing test");
    jfrm.add(jl);
    
    mp=new MyPanel();
    mp.setPreferredSize(new Dimension(2500,600));
    
    jfrm.add(new JScrollPane(mp));
    
    jfrm.setVisible(true);
  }
  
  public static void main(String[] args){
    SwingUtilities.invokeLater(new Runnable(){
      public void run(){
        new SwingTestGrafik();
      }
    });
  }
}