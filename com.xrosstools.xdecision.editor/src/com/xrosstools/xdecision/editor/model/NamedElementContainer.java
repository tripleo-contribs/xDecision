package com.xrosstools.xdecision.editor.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

public class NamedElementContainer<T extends NamedElement> extends NamedElement {
    private List<T> elements = new ArrayList<T>();
    private NamedElementTypeEnum elementType;

    public NamedElementContainer() {
        super(NamedElementTypeEnum.CONTAINER);
    }

    public NamedElementContainer(String name, NamedElementTypeEnum elementType) {
        super(name, NamedElementTypeEnum.CONTAINER);
        this.elementType = elementType;
    }

    public int size() {
        return elements.size();
    }
    
    public T get(int index) {
        return elements.get(index);
    }
    
    public void set(int index, T value) {
        elements.set(index, value);
    }

    public NamedElementTypeEnum getElementType() {
        return elementType;
    }

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
        firePropertyChange(PROP_ELEMENTS, null, elements);
    }
    
    public String[] getElementNames() {
        String[] names = new String[elements.size()];
        for (int i = 0; i < names.length; i++)
            names[i] = get(i).getName();
        return names;
    }
    
    public void add(T element) {
        elements.add(element);
        firePropertyChange(PROP_ELEMENTS, null, elements);
    }

    public void addAll(List<T> elements) {
        this.elements.addAll(elements);
        firePropertyChange(PROP_ELEMENTS, null, elements);
    }

    public void remove(T element) {
        elements.remove(element);
        firePropertyChange(PROP_ELEMENTS, null, elements);
    }
    
    public boolean containsName(String name) {
        return findByName(name) != null;
    }
    
    public NamedElement findByName(String name) {
        for(NamedElement type: elements) {
            if(name.equals(type.getName()))
                return type;
        }
        
        return null;
    }
    
    @Override
    public IPropertyDescriptor[] getPropertyDescriptors() {
        //Don't allow user change name
        return NONE;
    }
}