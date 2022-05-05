package com.xrosstools.xdecision.idea.editor.model.definition;

import com.xrosstools.xdecision.idea.editor.model.DecisionTreeDiagram;

public class FieldDefinition extends NamedType {
    public FieldDefinition(DecisionTreeDiagram diagram, String name) {
        this(diagram, name, DEFAULT_TYPE);
    }

    public FieldDefinition(DecisionTreeDiagram diagram, String name, DataType type) {
        super(diagram, name, NamedElementTypeEnum.FIELD, type);
    }    
}
