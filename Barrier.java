import java.util.ArrayList;
/**
 * Write a description of class Barrier here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Barrier extends GameObject
{
    // instance variables - replace the example below with your 
    int middle,range,bottom,index;
    
    int positionX;
    int ogb;
    boolean neat;
    /**
     * Constructor for objects of class Barrier
     */
    public Barrier(int xx,int yy,int aa,int bb)
    {
        super(xx,yy,aa,bb);
        middle=0;
        range=0;
        bottom=0;
    }

    public Barrier(Ball Ball, ArrayList<Barrier>Barriers)
    {
        super(00,00,15,60);
        middle=377;
        range=190;
        bottom=440;
        ChangeXY(Ball,Barriers);
    }
    
   
    
    public void ChangeXY(Ball Ball, ArrayList<Barrier>Barriers)
    {
        
        
        int positionY;
        boolean NewLocation=false;
        // if(Ball.getScoreLeft()>(Ball.getScoreRight()*2)+Ball.getScoreRight()&&!Lever)
        // {
            // middle=middle+100;
            // range=range-100;
            // Lever=true;
        // }
        while(!NewLocation)
        {
            // if(Ball.getScoreLeft()>Ball.getScoreRight())
            // {
                // positionX=middle-(int)(Math.random()*range+10);
            // }
            // if(Ball.getScoreLeft()<Ball.getScoreRight())
            // {
                // positionX=(middle+(int)(Math.random()*range+10));
            // }
            
            // System.out.println(Barriers.size());
            if(Ball.getScoreLeft()<Ball.getScoreRight())
            {
                positionX=(middle+(int)(Math.random()*range+10));
            }
            if(Ball.getScoreLeft()==Ball.getScoreRight())
            {
                positionX=(middle);
            }
            if(Ball.getScoreLeft()>Ball.getScoreRight())
            {
                positionX=middle-(int)(Math.random()*range+10);
            }
            positionY=(60+(int)(Math.random()*(bottom-60)+1));
         if(Barriers.size()>1)
         {
             boolean okay=true;
            for (index=0;index<Barriers.size();index++)
          {
              // if(!neat)
              // System.out.println("for loop stuck");
              // neat=true;
                // if((this !=Barriers.get(index)) && !(
                            // positionX > Barriers.get(index).getX() - (a * 5)
                         // && positionX < Barriers.get(index).getX() + (Barriers.get(index).getSizeA()  * 5) 
                         // && positionY > Barriers.get(index).getY() - (b * 5)
                         // && positionY < Barriers.get(index).getY() + (Barriers.get(index).getSizeB() * 5)))
                if((this !=Barriers.get(index))) 
                if((
                            positionX > Barriers.get(index).getX() - (a * 2)
                         && positionX < Barriers.get(index).getX() + (Barriers.get(index).getSizeA()  * 2) 
                         && positionY > Barriers.get(index).getY() - (b * 2)
                         && positionY < Barriers.get(index).getY() + (Barriers.get(index).getSizeB() * 2))
                         // && !(positionX > Ball.getX() - (a*2)
                          // && positionX < Ball.getX() + (Ball.getSizeA()*2)
                          // && positionY > Ball.getY() - (b*2)
                          // && positionY < Ball.getY() + (Ball.getSizeB()*2))
                          )
                            {
                                
                                    
                                okay=false;    
                                
                              } 
            }
            if(okay)
            {
                NewLocation=true;
                setX(positionX);
                setY(positionY);
            }
        }
        else
        {
            setX(positionX);
            setY(positionY);
            NewLocation=true;
        } 
        
     }
     
     
    }
    
}

   
