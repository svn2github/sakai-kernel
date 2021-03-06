package org.sakaiproject.conditions.impl;

import java.util.List;

import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;
import org.sakaiproject.conditions.api.EvaluationAction;
import org.sakaiproject.conditions.api.Rule;
import org.sakaiproject.conditions.api.Condition;
import org.sakaiproject.event.api.Event;
import org.sakaiproject.event.api.Notification;
import org.sakaiproject.event.api.NotificationAction;
import org.w3c.dom.Element;

public class BaseRule implements Rule, NotificationAction {
	
	private String resourceId;
	private List<Condition> predicates;
	private Conjunction conj;
	private EvaluationAction command;
	
	public BaseRule(String resourceId, List<Condition> predicates, EvaluationAction command, Conjunction conj) {
		this.resourceId = resourceId;
		this.predicates = predicates;
		this.command = command;
		this.conj = conj;
	}

	public boolean evaluate(Object arg0) {
		Predicate judgement = null;
		if (predicates.size() == 1) {
			judgement = predicates.get(0);
		} else {
			if (conj == Conjunction.AND) {
				judgement = PredicateUtils.allPredicate(predicates);
			}
			else if (conj == Conjunction.OR) {
				judgement = PredicateUtils.anyPredicate(predicates);
			}
		}
		
		return judgement.evaluate(arg0);
	}

	public NotificationAction getClone() {
		// TODO Auto-generated method stub
		return null;
	}

	public void notify(Notification notification, Event event) {
			try {
				command.execute(event, this.evaluate(event));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void set(Element el) {
		// TODO Auto-generated method stub

	}

	public void set(NotificationAction other) {
		// TODO Auto-generated method stub

	}

	public void toXml(Element el) {
		el.setAttribute("resourceId", this.resourceId);
		
		if(this.conj == Rule.Conjunction.OR) {
			el.setAttribute("conjunction", "OR");
		} else if(this.conj == Rule.Conjunction.AND) {
			el.setAttribute("conjunction", "AND");
		}
		
		Element predicates = el.getOwnerDocument().createElement("predicates");
		el.appendChild(predicates);
		for (Condition c : this.predicates) {
			Element predicateElement = el.getOwnerDocument().createElement("predicate");
			predicateElement.setAttribute("class", c.getClass().getName());
			predicateElement.setAttribute("receiver", c.getReceiver());
			predicateElement.setAttribute("method", c.getMethod());
			predicateElement.setAttribute("operator", c.getOperator());
			Object argument = c.getArgument();
			if (argument == null) {
				argument = "";
			}
			predicateElement.setAttribute("argument", argument.toString());
			predicates.appendChild(predicateElement);
		}

	}

}
