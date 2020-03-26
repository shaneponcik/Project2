import java.util.ArrayList;
import java.util.Collections;

public class ClauseContainer {
	int clauseCounter;
	ArrayList<Clause> clauses;
	
	public ClauseContainer()
	{
		clauseCounter = 1;
		clauses = new ArrayList<Clause>();
	}
	
	public Clause getClause(int clauseNumber)
	{
		return clauses.get(clauseNumber - 1);
	}
	
	public void add(Clause c)
	{
		c.clauseNumber = clauseCounter;
		clauseCounter++;
		
		clauses.add(c);
	}
	
	public void printClauses()
	{
		for(Clause c: clauses)
		{
			System.out.println(c);
		}
	}
	
	public Clause get(int clauseNumber)
	{
		return clauses.get(clauseNumber - 1);
	}
	
	public ClauseContainer getReducedContainer()
	{
		//Only works if the problem is first proved
		ClauseContainer nContainer = new ClauseContainer();
		Clause solvedClause = this.get(this.clauses.size());
		nContainer.clauses.add(solvedClause); // Add the "False" clause
		for(int i = 1; i <= nContainer.clauses.size(); ++i)
		{
			int parent1 = nContainer.get(i).parent1;
			int parent2 = nContainer.get(i).parent2;
			
			//If the clause is not a root clause, add its parents to the list of relevant clauses
			if(parent1 != 0 && !nContainer.ifContainsClauseNumber(parent1))
			{
				nContainer.clauses.add(this.get(parent1));
			}
			
			if(parent2 != 0 && !nContainer.ifContainsClauseNumber(parent2))
			{
				nContainer.clauses.add(this.get(parent2));
			}
		}
		
		Collections.sort(nContainer.clauses);
		
		return nContainer;
	}
	
	public boolean ifContainsClauseNumber(int clauseNumber)
	{	
		for(Clause c: this.clauses)
		{
			if(c.clauseNumber == clauseNumber)
				return true;
		}
		return false;
	}

}
