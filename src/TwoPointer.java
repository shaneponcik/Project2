import java.util.ArrayList;

public class TwoPointer {
	public static boolean TwoPointer(ClauseContainer cc, int conclusionClauseNumber)
	{
		for(int a = conclusionClauseNumber; a <= cc.clauses.size(); ++a)
		{
			for(int b = 1; b < a; ++b)
			{
				Clause c1 = cc.get(a);
				Clause c2 = cc.get(b);
				
				ArrayList<Clause> resolvents = ResolutionFunctions.getResolvents(c1, c2);
				for(Clause c: resolvents)
				{
					cc.add(c);
					if(c.isFalse())
					{
						return true; // aka proved
					}
				}
			}
		}
		
		return false;
	}
}
