public class MoveableObject extends GameObject
{
   double xdir,ydir,speed;
   double Cxdir=0;
        double Cydir=0;
        boolean caught=false;
    int thing;
    public MoveableObject(int xx, int yy,int aa,int bb,int xdir, int ydir)
    {
       super(xx,yy,aa,bb);
       this.xdir=xdir;
       this.ydir=ydir;
       speed=0;
    }
    
    public MoveableObject(int xx, int yy,int aa,int bb)
    {
       super(xx,yy,aa,bb);
       xdir=0;
       ydir=0;
       speed=0;
    }
    
    
    

   public void move()
   {
       x = x + xdir;
       y = y + ydir;
       
    }
    
   
    
    public void setLocation(int xx, int yy)
    {
        x = xx;
        y = yy;
    }
    
    public void setspeed(int xxdir, int yydir)
    {
        xdir = xxdir;
        ydir = yydir;
     }
    
    public void increaseSpeed(double sped)
    {
      speed = speed + sped;    
    }
    
    public void setSpeed(double sped)
    {
        speed = sped;
    }
  
   public int getXdir()
     {
      return (int)xdir;  
     }
   
   public int getYdir()
     {
      return (int)ydir; 
     }
     
     public void moveX(int moved)
     {
         x = x + moved;
     }
     
     public void moveY(int moved)
     {
         y = y + moved;
     }
     
     public void setXdir(double xxdir)
     {
      xdir = xxdir;
        
     }
   
   public void setYdir(double yydir)
     {
      ydir = yydir;
     }
     
     
     
     public void hit(String rt)
    {     
        boolean switchgate=false;
        if(xdir>6||ydir>6||xdir<6||ydir<-61)
        {
            
        }
            if(rt=="HitX"&& switchgate==false)
            {
                xdir=xdir*-1.15;
                ydir=ydir*1.01;
            }
            if(rt=="HitY")
            {
                xdir=xdir*1.15;
                ydir=ydir*-1.01;
            }
            // System.out.println(xdir+","+ydir);
    }
    
    public void hitBarrier(String rt)
    {     
        boolean switchgate=false;
        if(xdir>6||ydir>6||xdir<6||ydir<-61)
        {
            
        }
            if(rt=="HitX"&& switchgate==false)
            {
                xdir=xdir*-1;
                ydir=ydir*1;
            }
            if(rt=="HitY")
            {
                xdir=xdir*1;
                ydir=ydir*-1;
            }
            // System.out.println(xdir+","+ydir);
    }
    
    public void Artifcalhit(String rt)
    {
           if(rt.equals("HitX"))
            {
                xdir=Cxdir*-1.15;
                ydir=Cydir*1.01;
            }
            if(rt.equals("HitY"))
            {
                xdir=Cxdir*1.15;
                ydir=Cydir*-1.01;
            }
            caught=false;
            
    }
    
    
    public void hitPlayer(String rt,boolean Catch,Player Player)
    {
        
        // if(!Catch&&!caught)
        // {
            // if(rt=="HitX")
            // {
                // xdir=xdir*-1.15;
                // ydir=ydir*1.01;
            // }
            // if(rt=="HitY")
            // {
                // xdir=xdir*1.15;
                // ydir=ydir*-1.01;
            // }
        // }
        
        // if(caught)
        // {
            // xdir=oxdir;
            // ydir=oydir;
        // }
        // if(Catch)
        // {
            // oxdir=xdir;
            // oydir=xdir;
            // xdir=0;
            // ydir=0;
            // caught=true;
        // }
        if(!caught)
        {
            Cxdir=xdir;
            Cydir=ydir;
        }
        if(!Catch)
        {
            if(rt.equals("HitX"))
            {
                xdir=Cxdir*-1.15;
                ydir=Cydir*1.01;
            }
            if(rt.equals("HitY"))
            {
                xdir=Cxdir*1.15;
                ydir=Cydir*-1.01; 
            
            
            }
           
            caught=false;
                 
        }
        if(Catch && !caught)
        {
            
            xdir=0;
            ydir=0;
            x=Player.getX()+Player.getSizeA();
            caught=true;
        }
        if(Catch&&caught)
        {
            y=y+Player.getYdir();
        }
    }
    
    public boolean getCaught()
    {
        return caught;
    }
    
    public void hitY()
    {        
            xdir=xdir*1.15;
            ydir=ydir*-1.15;
    }
     
     
}