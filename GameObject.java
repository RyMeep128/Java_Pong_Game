public class GameObject {
	double x, y;
	double a, b;
	String rt = "Nothing Returned";

	public GameObject(int xx, int yy, int aa, int bb) {
		x = xx;
		y = yy;
		a = aa;
		b = bb;
	}

	public String hitdetection(GameObject obj) {

		if (obj instanceof MoveableObject && !(this instanceof Gravity))
			rt = hitdetectionball((MoveableObject) obj);
		else if (x > obj.getX() - a && x < obj.getX() + obj.getSizeA() && y > obj.getY() - b
				&& y < obj.getY() + obj.getSizeB()) {
			rt = "Hit";
			return rt;
		} else {
			rt = "Miss";
			return rt;
		}
		return rt;
	}

	public String hitdetectionball(MoveableObject mobj, boolean Catch) {
		double space = -1;

		for (space = -1; space < 5; space = space + 0.05) {
			// System.out.println(""+space);
			// System.out.println("Sides"+((int)(y-space))+">"+((int)(mobj.getY()-b))+"&&"+((int)(y+
			// space))+"<"+((int)mobj.getY()+mobj.getSizeB())
			// +","+"&&("+((int)(x-space))+"=="+((int)(mobj.getX() -
			// a))+"||"+((int)(x+space))+"=="+((int)(mobj.getX()+mobj.getSizeA()))+")");
			if ((y) > ((mobj.getYDouble() - b)) && (y) < ((mobj.getYDouble() + mobj.getSizeBDouble()))
					&& (x - space == ((mobj.getXDouble() - a))
							|| x + space == (((mobj.getXDouble() + mobj.getSizeADouble())))

					))

			{
				if (mobj.getXdir() < 0) {
					mobj.moveX((mobj.getSizeA() / 2));
				}
				if (mobj.getXdir() > 0) {
					mobj.moveX((-mobj.getSizeA() / 2));
				}

				rt = "HitX";

				if (this instanceof Player) {
					mobj.hitPlayer(rt, Catch, (Player) this);
				} else
					mobj.hit(rt);

				return rt;
			} else
				rt = "No Return";
			if ((x) > (mobj.getXDouble() - a) && (x) < (mobj.getXDouble() + mobj.getSizeADouble())
					&& ((y - space) == (mobj.getYDouble() - b)
							|| (y + space) == (mobj.getYDouble() + mobj.getSizeBDouble())))

			{
				if (mobj.getYdir() < 0) {
					mobj.moveY(mobj.getSizeB() / 4);
				}
				if (mobj.getYdir() > 0) {
					mobj.moveY(-mobj.getSizeB() / 4);
				}
				// System.out.println("Topbottom");
				if (this instanceof Player) {
					mobj.hitPlayer(rt, Catch, (Player) this);
				} else
					mobj.hit(rt);
				rt = "HitY";
				mobj.hit(rt);
				return rt;
			} else
				rt = "No Return";
			// if(mobj.getX()<60)
			// {
			// System.out.print(", b:"+b+", y:"+y+", a:"+a+", x:"+x+",
			// objA:"+mobj.getSizeADouble()+", objy:"+mobj.getYDouble()+",
			// objb:"+mobj.getSizeBDouble()+", objx:"+mobj.getXDouble());
			// System.out.println(
			// ", "+ ( (y) )
			// + ">"
			// + ( ((mobj.getYDouble() - b) ) )
			// + " && "
			// + ( (y) )
			// +"<"
			// + (mobj.getYDouble() + mobj.getSizeBDouble())
			// + " && "
			// + ( (x - space) )
			// + "=="
			// + ( ((mobj.getXDouble() - a) ))
			// + " || "
			// + ( (x + space) )
			// + "=="
			// + (mobj.getXDouble() + mobj.getSizeADouble()) ) ;}
		}
		return rt;
	}

	// if(obj instanceOf Ball)

	// public String hitdetectionSides(GameObject obj)
	// {
	// int space=-1,index=0;
	// String rt="Nothing Returned";
	// boolean thing;

	// for(index=0;index<20;index++)
	// {System.out.println("Sides"+((int)(y-space))+">"+((int)(obj.getY()-b))+"&&"+((int)(y+
	// space))+"<"+((int)(obj.getY()+obj.getSizeB()))
	// +","+"&&("+((int)(x-space))+"=="+((int)(obj.getX() -
	// a))+"||"+((int)(x+space))+"=="+((int)(obj.getX()+obj.getSizeA()))+")");
	// space++ ;
	// }
	// space=-1;

	// for(index=0;index<20;index++)
	// {

	// if( (int)(y-space) > (int)(obj.getY() - b)
	// && (int)(y+space) < (int)(obj.getY() + obj.getSizeB())
	// &&( (int)(x - space) == (int)(obj.getX() - a)
	// || (int)(x + space) == (int)(obj.getX() + obj.getSizeA())))

	// {
	// space++;
	// rt="HitX";
	// return rt;
	// }
	// else
	// rt="No Return";

	// }
	// return rt;
	// }

	private String hitdetectionball(MoveableObject mobj) {
		double space = -1;

		for (space = -1; space < 5; space = space + 0.05) {
			// System.out.println(""+space);
			// System.out.println("Sides"+((int)(y-space))+">"+((int)(mobj.getY()-b))+"&&"+((int)(y+
			// space))+"<"+((int)mobj.getY()+mobj.getSizeB())
			// +","+"&&("+((int)(x-space))+"=="+((int)(mobj.getX() -
			// a))+"||"+((int)(x+space))+"=="+((int)(mobj.getX()+mobj.getSizeA()))+")");
			if ((y) > ((mobj.getYDouble() - b)) && (y) < ((mobj.getYDouble() + mobj.getSizeBDouble()))
					&& (x - space == ((mobj.getXDouble() - a))
							|| x + space == (((mobj.getXDouble() + mobj.getSizeADouble())))

					))

			{
				if (mobj.getXdir() < 0) {
					mobj.moveX((mobj.getSizeA() / 2));
				}
				if (mobj.getXdir() > 0) {
					mobj.moveX((-mobj.getSizeA() / 2));
				}

				rt = "HitX";

				if (this instanceof Barrier)
					mobj.hitBarrier(rt);
				else
					mobj.hit(rt);

				return rt;
			} else
				rt = "No Return";
			if ((x) > (mobj.getXDouble() - a) && (x) < (mobj.getXDouble() + mobj.getSizeADouble())
					&& ((y - space) == (mobj.getYDouble() - b)
							|| (y + space) == (mobj.getYDouble() + mobj.getSizeBDouble())))

			{
				if (mobj.getYdir() < 0) {
					mobj.moveY(mobj.getSizeB() / 4);
				}
				if (mobj.getYdir() > 0) {
					mobj.moveY(-mobj.getSizeB() / 4);
				}
				// System.out.println("Topbottom");
				if (this instanceof Barrier)
					mobj.hitBarrier(rt);
				else
					mobj.hit(rt);
				rt = "HitY";
				mobj.hit(rt);
				return rt;
			} else
				rt = "No Return";
			// if(mobj.getX()<60)
			// {
			// System.out.print(", b:"+b+", y:"+y+", a:"+a+", x:"+x+",
			// objA:"+mobj.getSizeADouble()+", objy:"+mobj.getYDouble()+",
			// objb:"+mobj.getSizeBDouble()+", objx:"+mobj.getXDouble());
			// System.out.println(
			// ", "+ ( (y) )
			// + ">"
			// + ( ((mobj.getYDouble() - b) ) )
			// + " && "
			// + ( (y) )
			// +"<"
			// + (mobj.getYDouble() + mobj.getSizeBDouble())
			// + " && "
			// + ( (x - space) )
			// + "=="
			// + ( ((mobj.getXDouble() - a) ))
			// + " || "
			// + ( (x + space) )
			// + "=="
			// + (mobj.getXDouble() + mobj.getSizeADouble()) ) ;}
		}
		return rt;
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public double getXDouble() {
		double nx;
		nx = (int) x;
		return nx;
	}

	public double getYDouble() {
		double ny;
		ny = (int) y;
		return ny;
	}

	public void addX(double add) {
		x = x + add;
	}

	public void addY(double add) {
		y = y + add;
	}

	public void addSizeA(double add) {
		a = a + add;
	}

	public void addB(double add) {
		b = b + add;
	}

	public int getSizeA() {
		return (int) a;
	}

	public int getSizeB() {
		return (int) b;
	}

	public double getSizeADouble() {
		return a;
	}

	public double getSizeBDouble() {
		return b;
	}

	public void setX(double xx) {
		x = xx;
	}

	public void setY(double yy) {
		y = yy;
	}

}