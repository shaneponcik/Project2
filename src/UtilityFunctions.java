import java.util.HashMap;
import java.util.HashSet;

public class UtilityFunctions {

	public static void propogateNewVariablesInUnifiable(Unifiable u, HashMap<Unifiable,Unifiable> swapMap)
	{
		if(u instanceof Variable)
		{
			if(! swapMap.containsKey(u))
			{
				swapMap.put(u, new Variable((Variable) u));
			}
		}
		else if(u instanceof SimpleSentence)
		{
			for(int i = 0; i < ((SimpleSentence) u).terms.length; ++i)
			{
				propogateNewVariablesInUnifiable(((SimpleSentence) u).terms[i], swapMap);
			}
		}
		
	}

}
