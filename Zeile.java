public class Zeile {
  private String datum;
  private double c; //close
  private double v; //volume
  private double e; //er�ffnung
  private double h; //hoch
  private double t;  //tief
  
  public Zeile(String datum, double c, double v, double e, double h, double t){
    this.datum=datum;
    this.c=c;
    this.v=v;
    this.e=e;
    this.h=h;
    this.t=t;
  }
  
  public Zeile(){
    }

  
  public String getDatum(){
    return datum;
  }
  

  public double getC(){
    return c;
  }
  
  public double getH(){
    return h;
  }
  
  
  public double getE(){
    return e;
  }
  
  public double getT(){
    return t;
  }

 }

