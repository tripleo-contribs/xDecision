package com.xrosstools.xdecision.idea.editor.treeparts.definition;

import com.xrosstools.xdecision.idea.editor.model.definition.NamedElement;
import com.xrosstools.xdecision.idea.editor.model.definition.NamedElementContainer;

import java.util.List;

public class NamedElementContainerTreePart extends NamedElementTreePart {
    private NamedElementContainer container;
    public NamedElementContainerTreePart(Object model) {
        super(model);
        container = (NamedElementContainer)model;
     }

    @Override
    protected List<NamedElement> getModelChildren() {
        return ((NamedElementContainer)getModel()).getElements();
    }
}