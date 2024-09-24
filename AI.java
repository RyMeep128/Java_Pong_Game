
/**
 * Write a description of class AI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AI extends MoveableObject
{
    // instance variables - replace the example below with your own
    
    boolean easy,mixed;
    /**
     * Constructor for objects of class AI
     */
    public AI(int xx, int yy,int aa,int bb,int xdir, int ydir)
    {
       super(xx,yy,aa,bb);
    }
    public void alg(Ball obj,String dif)
    {
        int random,var;
        mixed=false;
        var=10;
        if(dif.equals(null))
            dif="mixed";
        if(dif.equals(""))
            dif="mixed";
        if(dif.equals("mixed"))
        {
            mixed=true;
            if(obj.getScoreLeft()<obj.getScoreRight())
            {               
             dif="hard";   
            }
            if(obj.getScoreLeft()>obj.getScoreRight())
            {               
             dif="easy";   
            }
            if(obj.getScoreLeft()>obj.getScoreRight()*2)
            {               
             dif="miss";   
            }
            if(obj.getScoreLeft()==obj.getScoreRight())
            {               
             dif="normal";   
            }
            
        }
        
        if(dif.equals("easy"))
        {
            var=4;
        }
        if(dif.equals("normal"))
        {
            var=5;
        }
        if(dif.equals("hard"))
        {
            var=6;
        }
        if(dif.equals("unbeatable"))
        {
            var=10;
        }
        if(dif.equals("miss"))
        {
            var=0;
        }
        if(dif.equals("bot"))
        {
            var=1000000;
        }
        
        random = (int)(Math.random()  * var + 1);
      
        if(random==1)
        {
            if(y-(b/2)<obj.getY())
                ydir=-2;
            if(y+(b/2)>obj.getY())
                ydir=2;
        }
        else 
        {
          if(y-(b/2)<obj.getY())
                ydir=2;
          if(y+(b/2)>obj.getY())
                ydir=-2; 
        }
        if(mixed)
        dif="mixed";
    }
    
    public void walls(String S1)
    {    
       
        if(S1=="Y,0")
        ydir=2;
        
        if(S1=="Y,600")
        ydir=-2;
    }
    
}
