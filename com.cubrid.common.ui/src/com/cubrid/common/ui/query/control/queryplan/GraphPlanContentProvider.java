package com.cubrid.common.ui.query.control.queryplan;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.IGraphContentProvider;

import com.cubrid.common.core.queryplan.model.PlanNode;

public class GraphPlanContentProvider<K, V> implements IGraphContentProvider {
	public Object[] getElements(Object inputElement) {
		if (!(inputElement instanceof PlanNode)) {
			return null;
		}

		List<GraphPlanNodePair> list = new ArrayList<GraphPlanNodePair>();
		makeList(list, null, (PlanNode)inputElement);
		return list.toArray();
	}

	private void makeList(List<GraphPlanNodePair> list, PlanNode parentNode, PlanNode planNode) {
		if (planNode == null) {
			return;
		}

		if (parentNode != null) {
			GraphPlanNodePair pair = new GraphPlanNodePair();
			list.add(pair);

			// if you need to change directions, you should change following both.
			pair.setSource(parentNode);
			pair.setDest(planNode);
		}

		List<PlanNode> children = planNode.getChildren();
		if (children == null) {
			return;
		}

		for (PlanNode child : children) {
			makeList(list, planNode, child);
		}
	}

	public Object getSource(Object rel) {
		if (!(rel instanceof GraphPlanNodePair)) {
			return null;
		}
		GraphPlanNodePair kv = (GraphPlanNodePair) rel;
		return kv.getSource();
	}

	public Object getDestination(Object rel) {
		if (!(rel instanceof GraphPlanNodePair)) {
			return null;
		}
		GraphPlanNodePair kv = (GraphPlanNodePair) rel;
		return kv.getDest();
	}

	public Object[] getConnectedTo(Object entity) {
		return null;
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}
