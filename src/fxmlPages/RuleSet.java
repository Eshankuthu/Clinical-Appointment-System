package fxmlPages;

import java.awt.Component;
import Ruleset.RuleException;


public interface RuleSet {
	public void applyRules(Component ob) throws RuleException;
}
