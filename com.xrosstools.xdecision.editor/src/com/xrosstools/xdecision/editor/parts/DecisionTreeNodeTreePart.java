package com.xrosstools.xdecision.editor.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.swt.graphics.Image;

import com.xrosstools.xdecision.editor.Activator;
import com.xrosstools.xdecision.editor.model.DecisionTreeNode;
import com.xrosstools.xdecision.editor.model.DecisionTreeNodeConnection;
import com.xrosstools.xdecision.editor.model.expression.ExpressionDefinition;

public class DecisionTreeNodeTreePart extends AbstractTreeEditPart implements PropertyChangeListener {
    public DecisionTreeNodeTreePart(Object model) {
        super(model);
    }
    
    protected List<DecisionTreeNode> getModelChildren() {
    	DecisionTreeNode node = (DecisionTreeNode)getModel();
    	List<DecisionTreeNode> chidren = new ArrayList<DecisionTreeNode>();
    	for(DecisionTreeNodeConnection path : node.getOutputs())
    		chidren.add(path.getChild());
    	return chidren;
    }

    public void activate() {
        super.activate();
        ((DecisionTreeNode) getModel()).getListeners().addPropertyChangeListener(this);
    }
    
    public void deactivate() {
        super.deactivate();
        ((ExpressionDefinition) getModel()).getListeners().removePropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        refresh();
    }

    protected String getText() {
    	DecisionTreeNode node = (DecisionTreeNode)getModel();
    	return node.getOutlineText();
    }
    
    protected Image getImage() {
    	return Activator.getDefault().getImage(com.xrosstools.xdecision.editor.Activator.NODE);
    }
}
