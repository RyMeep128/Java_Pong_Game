/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player extends MoveableObject 
{
    // instance variables - replace the example below with your own
    String direction;
    double yspeed;
    boolean easy,mixed;
    /**
     * Constructor for objects of class Player
     */
    public Player(int xx, int yy,int aa,int bb,int xdir, int ydir)
    {
       super(xx,yy,aa,bb);
    }
    
    public void ChangeDirection(String dd)
    {
           direction=dd;
           if(direction.equals("up"))    
            {
              xdir =  0;
              ydir = -1*speed;
             }
           if(direction.equals("down"))    
            {
              xdir =  0;
              ydir = 1*speed;
             }
           if(direction.equals("left"))    
            {
              xdir = -1*speed;
              ydir =   0;
             }
           if(direction.equals("stop"))    
            {
              xdir = 0;
              ydir =  0;
             }
             
             
             
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
            if(obj.getScoreLeft()>obj.getScoreRight())
            {               
             dif="hard";   
            }
            if(obj.getScoreLeft()<obj.getScoreRight())
            {               
             dif="easy";   
            }
            if(obj.getScoreLeft()*2<obj.getScoreRight())
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
        x = x + xdir;
        y = y + ydir;
    }
    
    public void moveY(int ydir)
    {       
       y = y + ydir;
    }
    // public void movedownY()
    // {
        // y = y - ydir;
    // }
    // public void edge(String S2)
    // {
        // if(S2=="YB")
        // direction="up";
        // if(S2=="YT")
        // direction="down";
        // System.out.println(direction);

   
        // }
        
        public void scroll(int notch)
        {
            notch=notch*20;
            y=y+notch;
        }
}
