public interface Unifiable extends PCExpression{
	public SubstitutionSet unify(Unifiable expr, SubstitutionSet s);
	
}
