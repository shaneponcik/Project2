
public class RoadRunnerDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClauseContainer cc = RoadRunnerDriver.getRoadRunnerClauses();
		cc.printClauses();
		
		boolean isSolvable = TwoPointer.TwoPointer(cc, 7);
		
		System.out.println(isSolvable);
		//clauseContainer.printClauses();
		
		if(isSolvable)
		{
			cc.getReducedContainer().printClauses();
		}
	}
	
	public static ClauseContainer getRoadRunnerClauses()
	{
//		'( (1   ( (rr (a)) )   	( (coyote y) )   )
//		   (2	( (chase z (a)) ) ( (coyote z) )   )	
//		   (3   ( (smart x) )   ( (rr x) (beep x) )   )
//		   (4   nil ( (coyote w) (rr u) (catch w u) (smart u) )    )
//		   (5   ( (frustrated s) (catch s t) ) ( (coyote s) (rr t) 
//				(chase s t) )    )
//		   (6   ( (beep r) )  ( (rr r) )   )
//		   (7   ( ( coyote (b)) ) nil   ) ;; goal clause begins here
//		   (8   nil ( (frustrated (b)) )   )     ) )
		
		ClauseContainer cc = new ClauseContainer();
		
		//Variables
		Variable y;
		Variable z;
		Variable x;
		Variable w;
		Variable u;
		Variable s;
		Variable t;
		Variable r;
		
		//Constants
		Constant a = new Constant("a");
		Constant b = new Constant("b");
		
		//Functions
		Constant rr = new Constant("rr");
		Constant coyote = new Constant("coyote");
		Constant chase = new Constant("chase");
		Constant smart = new Constant("smart");
		Constant beep = new Constant("beep");
		Constant catchf = new Constant("catch");
		Constant frustrated = new Constant("frustrated");
		
//		'( (1   ( (rr (a)) )   	( (coyote y) )   )
		y = new Variable("y");
		Clause c1 = new Clause();
		c1.positiveLiterals.add(new SimpleSentence(rr, a));
		c1.negativeLiterals.add(new SimpleSentence(coyote, y));
		cc.add(c1);
		
		
//		   (2	( (chase z (a)) ) ( (coyote z) )   )	
		z = new Variable("z");
		Clause c2= new Clause();
		c2.positiveLiterals.add(new SimpleSentence(chase, z, a));
		
		c2.negativeLiterals.add(new SimpleSentence(coyote, z));
		cc.add(c2);
		
//		   (3   ( (smart x) )   ( (rr x) (beep x) )   )
		x = new Variable("x");
		Clause c3= new Clause();
		c3.positiveLiterals.add(new SimpleSentence(smart, x));
		
		c3.negativeLiterals.add(new SimpleSentence(rr, x));
		c3.negativeLiterals.add(new SimpleSentence(beep, x));
		cc.add(c3);
		
//		   (4   nil ( (coyote w) (rr u) (catch w u) (smart u) )    )
		w = new Variable("w");
		u = new Variable("u");
		Clause c4= new Clause();
		
		c4.negativeLiterals.add(new SimpleSentence(coyote, w));
		c4.negativeLiterals.add(new SimpleSentence(rr, u));
		c4.negativeLiterals.add(new SimpleSentence(catchf, w, u));
		c4.negativeLiterals.add(new SimpleSentence(smart, u));
		cc.add(c4);
		
//		   (5   ( (frustrated s) (catch s t) ) ( (coyote s) (rr t) (chase s t) )    )
		s = new Variable("s");
		t = new Variable("t");
		Clause c5= new Clause();
		c5.positiveLiterals.add(new SimpleSentence(frustrated, s));
		c5.positiveLiterals.add(new SimpleSentence(catchf, s, t));
		
		c5.negativeLiterals.add(new SimpleSentence(coyote, s));
		c5.negativeLiterals.add(new SimpleSentence(rr, t));
		c5.negativeLiterals.add(new SimpleSentence(chase, s, t));
		cc.add(c5);
		
//		   (6   ( (beep r) )  ( (rr r) )   )
		r = new Variable("r");
		Clause c6= new Clause();
		c6.positiveLiterals.add(new SimpleSentence(beep, r));
		
		c6.negativeLiterals.add(new SimpleSentence(rr, r));
		cc.add(c6);
		
//		   (7   ( ( coyote (b)) ) nil   ) ;; goal clause begins here
		Clause c7 = new Clause();
		c7.positiveLiterals.add(new SimpleSentence(coyote, b));
		cc.add(c7);
		
//		   (8   nil ( (frustrated (b)) )   )     ) )
		Clause c8 = new Clause();
		c8.negativeLiterals.add(new SimpleSentence(frustrated, b));
		cc.add(c8);
		
		return cc;
	}

}
