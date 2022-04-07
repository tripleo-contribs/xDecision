package com.xrosstools.xdecision.editor.model.expression;

import com.xrosstools.xdecision.editor.model.MethodDefinition;

public class MethodExpression extends VariableExpression {
    private ParameterListExpression parameters;
    
    public MethodExpression(String name, ParameterListExpression parameters){
        super(name);
        setParameters(parameters);
    }
    
    public MethodExpression(MethodDefinition definition){
        super(definition);
        setParameters(new ParameterListExpression(definition.getParameters()));
    }

    public MethodExpression(ParameterListExpression parameters){
        this("", parameters);
    }
    
    public void setParameters(ParameterListExpression parameters) {
        this.parameters = replaceExpression(this.parameters, parameters);
    }

    public ParameterListExpression getParameters() {
        return parameters;
    }

    @Override
    public String getBaseString() {
        return getName() + "(" + parameters.toString() + ")";
    }
}
