

/**
 * Write a description of class Ball here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ball extends MoveableObject
{
    int left,right,ogX,ogY,ogXdir,ogYdir,randomdirection,direction,randomdirection2,direction2;
    int pausecounter;
    double Cxdir,Cydir;
    String WhoScored;
    boolean caught;
    boolean color;
    int updown;
    public Ball(int xx, int yy,int aa,int bb,int xdir, int ydir)
    {
       super(xx,yy,aa,bb,xdir,ydir);
       ogX=(int)x;
       ogY=(int)y;
       ogXdir=(int)xdir;
       ogYdir=(int)ydir;
       caught=false;
       right=0;
       left=0;
       color=false;
    }
    
    public void Gravity(int updown)
    { 
        this.updown=updown;
        if(updown==1)
        ydir=ydir+0.01;
        if(updown==2)
        ydir=ydir-0.01;
    }
    // public void hit(Boolean Catch,Player Player)
    // {
        // if(!caught)
        // {
            // Cxdir=xdir;
            // Cydir=ydir;
        // }
        // if(!Catch)
        // {
            // xdir=Cxdir*-1.15;
            // // if(y==Player.getY()-60)
            // // ydir=Cydir*-1.15;
            // // else
            // ydir=Cydir*1.15;
            // caught=false;
            
        // }
        // if(Catch && !caught)
        // {
            
            // xdir=0;
            // ydir=0;
            // caught=true;
            // System.out.println(caught);
        // }
        // if(Catch&&caught)
        // {
            // y=y+Player.getYdir();
        // }
    // }
    
    
    
    public void hit()
    {        
            xdir=xdir*-1.15;
            ydir=ydir*1.15;
            
    }
    
    public boolean getColor()
    {
        return color;
    }
    
    public void setColor(boolean stuff)
    {
        color=stuff;
    }
    public boolean getWithin(Player Player)
    {
        int nxdir=(int)xdir;
        if(xdir<0)
        nxdir=nxdir*-1;
        // System.out.println(x+"<"+(Player.getX()+(150*nxdir)));
        if(x<Player.getX()+(150*nxdir))
        return true;
        return false;
    }
   
    
    public void Score(String WWhoScored)
    {
        WhoScored=WWhoScored;
        if(WhoScored=="left")
        left=left+1;
        if(WhoScored=="right")
        right=right+1;
    }
    
    
    
    public String getWhoScored()
    {
     return WhoScored;   
    }
    
    public int Reset()
    {
        pausecounter=0;
        if(WhoScored=="left" || WhoScored=="right")
        {
            randomdirection = (int)(Math.random()  * 2 + 1);
            randomdirection2 = (int)(Math.random()  * 2 + 1);
            if(randomdirection==1)
            direction=1;
            else
            direction=-1;
            if(randomdirection==1)
            direction2=-1;
            else
            direction2=1;
            x=ogX;
            y=ogY;
            xdir=direction;
            ydir=direction2;
            WhoScored="";
            pausecounter=250;
            
        }
        return pausecounter;
        
    }
    public int getScoreLeft()
    {
        return left;
    }
    public int getScoreRight()
    {
        return right;
    }
    
    public boolean equalscore()
    {
        if(left==right)
        return true;
        else return false;
    }
    
    public void walls(GameBorder gb)
    {    
        ydir=ydir*-1.01;
        if(y>gb.getY()+gb.getSizeB()-10)
        {
            y=gb.getY()+gb.getSizeB()-10;
        }
        if(y<gb.getY())
        {
            y=gb.getY();
        }
        // if(S1=="X,0")
        // xdir=1;
        // if(S1=="Y,0")
        // {
            // y=0;
            // ydir=ydir*-1.15;
            
            // System.out.println(ydir);
        // }
        // if(S1=="X,600")
        // xdir=-1;
        // if(S1=="Y,600")
        // ydir=ydir*-1.15;
    }
    }
