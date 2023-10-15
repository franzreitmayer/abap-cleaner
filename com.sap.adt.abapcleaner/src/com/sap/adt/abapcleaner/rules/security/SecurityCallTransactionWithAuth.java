package com.sap.adt.abapcleaner.rules.security;

import java.time.LocalDate;

import com.sap.adt.abapcleaner.parser.*;
import com.sap.adt.abapcleaner.programbase.*;
import com.sap.adt.abapcleaner.rulebase.*;
import com.sap.adt.abapcleaner.rulehelpers.*;
import com.sap.adt.abapcleaner.rules.alignment.AlignLogicalExpressionsRule;

public class SecurityCallTransactionWithAuth extends Rule {
	private final static RuleReference[] references = new RuleReference[] { 
        new RuleReference(RuleSource.ABAP_CLEANER) };

	@Override
	public RuleID getID() { return RuleID.SECURITY_CALL_TA_WITH_AUTHORIZATION; }

	@Override
	public RuleGroupID getGroupID() { return RuleGroupID.SECURITY; }

	@Override
	public String getDisplayName() { return "Authority check at CALL TRANSACTION"; }

	@Override
	public String getDescription() { return "Use CALL TRANSACTION only with authority check"; }

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
      return LINE_SEP + "CALL TRANSACTION 'XD03' USING lt_params." + LINE_SEP
                ;
   }

	public SecurityCallTransactionWithAuth(Profile profile) {
		super(profile);
		initializeConfiguration();
	}

	@Override
	protected void executeOn(Code code, int releaseRestriction) throws UnexpectedSyntaxAfterChanges {
        Command command = code.firstCommand;
/*         while(command != null) {
			Token firstToken = command.getFirstToken();
			if (firstToken.getText().toUpperCase().equals("CALL") ){
				Token nextToken = firstToken.getNext();
				if (nextToken.getText().toUpperCase().equals("TRANSACTION")) {
					Token transactionToken = nextToken.getNext();
					Token possibleUsing = transactionToken.getNext();
					if (possibleUsing.getText().toUpperCase().equals("USING")) {
						possibleUsing.setText("WITH AUTHORITY-CHECK USING", isActive);
					}
				}
			}
			command = command.getNext();
		}
 */	}
}
