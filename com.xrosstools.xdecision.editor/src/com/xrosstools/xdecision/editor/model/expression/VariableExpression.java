package com.xrosstools.xdecision.editor.model.expression;

import com.xrosstools.xdecision.editor.model.NamedElement;

public class VariableExpression extends ExtensibleExpression {
    private String name;
    private NamedElement referenceElement;

    public VariableExpression(String name) {
        this.name = name;
    }
    
    public VariableExpression(NamedElement referenceElement) {
        setReferenceElement(referenceElement);
    }

    public String getName() {
        return referenceElement == null ? name : referenceElement.getName();
    }

    public void setName(String name) {
        this.name = name;
        propertyChanged();
    }
    
    public boolean isValid() {
        return referenceElement != null;
    }
    
    public NamedElement getReferenceElement() {
        return referenceElement;
    }

    public void setReferenceElement(NamedElement newReferenceElement) {
        this.referenceElement = replaceElement(this.referenceElement, newReferenceElement);
    }

    @Override
    public String getBaseString() {
        return getName();
    }
}
