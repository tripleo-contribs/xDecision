package com.xrosstools.xdecision.idea.editor.menus;

import com.intellij.openapi.project.Project;
import com.xrosstools.idea.gef.actions.CommandAction;
import com.xrosstools.xdecision.idea.editor.commands.expression.ChangeConditionOperatorCommand;
import com.xrosstools.xdecision.idea.editor.model.ConditionOperator;
import com.xrosstools.xdecision.idea.editor.model.DecisionTreeDiagram;
import com.xrosstools.xdecision.idea.editor.model.DecisionTreeNodeConnection;
import com.xrosstools.xdecision.idea.editor.parts.DecisionTreeNodeConnectionPart;

import javax.swing.*;
import java.beans.PropertyChangeListener;

import static com.xrosstools.idea.gef.ContextMenuProvider.*;


public class ConnectionContextMenuProvider {
    private Project project;
    private DecisionTreeDiagram diagram;

    public ConnectionContextMenuProvider(Project project, DecisionTreeDiagram diagram, PropertyChangeListener listener) {
        this.project = project;
        this.diagram = diagram;
    }

    public void buildContextMenu(JPopupMenu menu, DecisionTreeNodeConnectionPart connPart){
        DecisionTreeNodeConnection conn = (DecisionTreeNodeConnection)connPart.getModel();
        for(ConditionOperator oprValue: ConditionOperator.values())
            menu.add(createItem(new CommandAction(oprValue.getText(), conn.getOperator() == oprValue, new ChangeConditionOperatorCommand(conn, oprValue))));
    }
}