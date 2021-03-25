package com.xrosstools.xdecision.editor;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;

import com.xrosstools.xdecision.editor.actions.CommandAction;
import com.xrosstools.xdecision.editor.actions.DecisionTreeCreateDecisionAction;
import com.xrosstools.xdecision.editor.actions.DecisionTreeCreateFactorAction;
import com.xrosstools.xdecision.editor.commands.ChangeDecisionCommand;
import com.xrosstools.xdecision.editor.commands.ChangeFactorFieldCommand;
import com.xrosstools.xdecision.editor.commands.ChangeNodeFactorCommand;
import com.xrosstools.xdecision.editor.model.DataType;
import com.xrosstools.xdecision.editor.model.DecisionTreeDiagram;
import com.xrosstools.xdecision.editor.model.DecisionTreeFactor;
import com.xrosstools.xdecision.editor.model.DecisionTreeNode;
import com.xrosstools.xdecision.editor.model.FieldDefinition;
import com.xrosstools.xdecision.editor.parts.DecisionTreeNodePart;

public class NodeContextMenuProvider {
    private DecisionTreeDiagramEditor editor;
    
    public NodeContextMenuProvider(DecisionTreeDiagramEditor editor) {
        this.editor = editor;
    }
    
    public void buildContextMenu(IMenuManager menu, DecisionTreeNodePart nodePart) {
        DecisionTreeNode node = (DecisionTreeNode)nodePart.getModel();
        DecisionTreeDiagram diagram = (DecisionTreeDiagram)editor.getRootEditPart().getContents().getModel();
        
        int i = 0;
        for(DecisionTreeFactor factor: diagram.getFactors()) {
//            if(factor.getType() != FactorType.USER_DEFINED)
                menu.add(new CommandAction(editor, factor.getFactorName(), node.getFactorId() == i, new ChangeNodeFactorCommand(node, i++)));
//            else
//                menu.add(selectFactorFieldMeun(diagram, node, factor));
        }

        menu.add(new Separator());
        menu.add(new DecisionTreeCreateFactorAction(editor, node));
        menu.add(new Separator());
        
        
        menu.add(new Separator());
        i = 0;
        for(String decision: diagram.getDecisions()) {
            menu.add(new CommandAction(editor, decision, node.getDecisionId() == i, new ChangeDecisionCommand(node, i++)));
        }
        
        menu.add(new Separator());
        menu.add(new DecisionTreeCreateDecisionAction(editor, node));
    }
    
    private MenuManager selectFactorFieldMeun(DecisionTreeDiagram diagram, DecisionTreeNode node, DecisionTreeFactor factor) {
        MenuManager subMenu = new MenuManager(factor.getFactorName());
        // select self
        int index = diagram.indexOf(factor);
        subMenu.add(new CommandAction(editor, factor.getFactorName(), node.getFactorId() == index, new ChangeNodeFactorCommand(node, index)));
        subMenu.add(new Separator());
        
        // select field
        DataType type = diagram.findDataType(factor.getTypeName());
        for(FieldDefinition field: type.getFields()) {
            subMenu.add(new CommandAction(editor, field.getName(), field.getName().equals(node.getFactorField()), new ChangeFactorFieldCommand(node, field.getName())));
        }
        return subMenu;
    }
}