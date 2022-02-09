package com.xrosstools.xdecision.editor.parts;

import java.util.ArrayList;
import java.util.List;

import com.xrosstools.xdecision.editor.model.MethodDefinition;

public class MethodDefinitionTreePart extends NamedElementTreePart {
    private MethodDefinition method;
    public MethodDefinitionTreePart(Object model) {
        super(model);
        method = (MethodDefinition)model;
     }

    @Override
    protected List getModelChildren() {
        List a = new ArrayList();
        a.add(method.getParameters());
        return a;
    }
}