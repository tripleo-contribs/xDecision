package com.xrosstools.xdecision.editor.model;

public class DataTypeSet extends DataTypeCollection {
    //TODO shall we support toArray[]? If you need it, just contact me
    public DataTypeSet(DecisionTreeDiagram diagram) {
        super(diagram, DataTypeEnum.SET);
    }
}
