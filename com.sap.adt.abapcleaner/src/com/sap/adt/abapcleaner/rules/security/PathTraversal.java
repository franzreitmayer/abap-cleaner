package com.sap.adt.abapcleaner.rules.security;

import java.time.LocalDate;

import com.sap.adt.abapcleaner.parser.*;
import com.sap.adt.abapcleaner.programbase.*;
import com.sap.adt.abapcleaner.rulebase.*;
import com.sap.adt.abapcleaner.rulehelpers.*;
import com.sap.adt.abapcleaner.rules.alignment.AlignLogicalExpressionsRule;

public class PathTraversal extends Rule {
	private final static RuleReference[] references = new RuleReference[] { 
        new RuleReference(RuleSource.ABAP_CLEANER) };

	@Override
	public RuleID getID() { return RuleID.SECURITY_PATH_TRAVERSAL; }

	@Override
	public RuleGroupID getGroupID() { return RuleGroupID.SECURITY; }

	@Override
	public String getDisplayName() { return "Secure usage of File operation"; }

	@Override
	public String getDescription() { return "Make sure only valid and checked Pathnames are used"; }

	@Override
	public LocalDate getDateCreated() { return LocalDate.of(2023, 10, 14); }

	@Override
	public RuleReference[] getReferences() { return references; }

	@Override
	public RuleID[] getDependentRules() { return null; }

	@Override
	public boolean isEssential() { return true; }

	@Override
   public String getExample() {
      return LINE_SEP + "OPEN DATASET l_file." + LINE_SEP + 
                LINE_SEP + "DELETE DATASET l_file." +
                LINE_SEP + "CLOSE DATASET l_file." 
                
                ;
   }

	public PathTraversal(Profile profile) {
		super(profile);
		initializeConfiguration();
	}

	@Override
	protected void executeOn(Code code, int releaseRestriction) throws UnexpectedSyntaxAfterChanges {
        Command command = code.firstCommand;
        while(command != null) {
            System.out.println(command);
            command = command.getNext();
        }
	}
}
