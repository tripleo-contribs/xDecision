package com.xrosstools.xdecision.editor.model.expression;

import com.xrosstools.xdecision.editor.model.MethodDefinition;

public class MethodExpression extends VariableExpression {
    private ParameterListExpression parameters;
    private String id;
    
    public MethodExpression(String name, ParameterListExpression parameters){
        super(name);
        setParameters(parameters);
    }
    
    public MethodExpression(MethodDefinition definition){
        this(definition.getName(), new ParameterListExpression());
        id = definition.getIdentifier();
    }

    public MethodExpression(ParameterListExpression parameters){
        this("", parameters);
    }
    
    public void setParameters(ParameterListExpression parameters) {
        this.parameters = parameters;
    }

    public ParameterListExpression getParameters() {
        return parameters;
    }
    
    @Override
    public String getIdentifier() {
        //Fix this
        //return id;
        return getName() + "(" + parameters.toString() + ")";
    }

    @Override
    public String getMainExpDisplayText() {
        return getName() + "(" + parameters.toString() + ")";
    }
}