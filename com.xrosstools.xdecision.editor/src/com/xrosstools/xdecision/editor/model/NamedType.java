package com.xrosstools.xdecision.editor.model;

import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

public class NamedType extends NamedElement implements PropertyConstants {
    private DataType type;
    private String propertyType;
    
    public NamedType(NamedElementTypeEnum type) {
        super(type);
        propertyType = String.format(PROP_TYPE_TPL, type.getTypeName());
    }
    
    public NamedType(String name, NamedElementTypeEnum elementType, DataType type) {
        super(name, elementType);
        this.type = type;
    }
    
    @Override
    public IPropertyDescriptor[] getPropertyDescriptors() {
        return combine(new IPropertyDescriptor[] {
                getNameDescriptor(),
                //TODO need to plus user defined types
                new ComboBoxPropertyDescriptor(propertyType, propertyType, DataTypeEnum.getAllNames()),},
                type.getPropertyDescriptors());
    }
    
    public Object getPropertyValue(Object propName) {
        if (propertyType.equals(propName))
            return type.getType().ordinal();
        
        if(type.isConcernedProperty(propName))
            return type.getPropertyValue(propName);
        
        return super.getPropertyValue(propName);
    }

    public void setPropertyValue(Object propName, Object value){
        if (propertyType.equals(propName)) {
            setType(DataTypeEnum.values()[(Integer)value].createDataType());
            return;
        }
        
        if(type.isConcernedProperty(propName)) {
            type.setPropertyValue(propName, value);
            return;
        }

        super.setPropertyValue(propName, value);
    }

    @Override
    public String toString() {
        return (getName() != null ? getName() : "-") +  ": " + (type != null ? type.toString() : "-");
    }
    
    public DataType getType() {
        return type;
    }

    public String getTypeName() {
        return type.getName();
    }

    public void setType(DataType type) {
        this.type = type;
        firePropertyChange(PROP_DATA_TYPE, null, type);
    }
}
