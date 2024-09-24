
/**
 * Write a description of class Gravity here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Gravity extends MoveableObject
{
    boolean gravity, hit;
    
    public Gravity(int xx, int yy,int aa,int bb,int xdir,int ydir, boolean Gravity)
    {   
        super(xx,yy,aa,bb,xdir,ydir);
        gravity = Gravity;
        hit=false;
    }
    
     public Gravity(int xx, int yy,int aa,int bb,boolean Gravity)
    {   
        super(xx,yy,aa,bb);
        gravity = Gravity;
        hit=false;
    }
    
    public void setGravity(boolean Gravity)
    {
        gravity = Gravity;
    }
    
    public boolean getGravity()
    {
        return gravity;
    }
    
    public void setHit(boolean Hit)
    {
        hit=Hit;
    }
    
    public boolean getHit()
    {
        return hit;   
    }
    public void walls(String S1)
    {    
       
        if(S1=="Y,0")
        ydir=2;
        
        if(S1=="Y,600")
        ydir=-2;
    }
}
