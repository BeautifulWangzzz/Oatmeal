package com.plum.oatmeal.application.util;

import com.plum.oatmeal.core.support.OatmealControl;

import java.io.File;
import java.util.Map;
import java.util.Set;

public class OatmealControlBuilder {

    private Map<String, Class> stringClassMap;
    private static OatmealControlBuilder instance;
    private OatmealControlBuilder() {
        XMLParser parser = new XMLParser();
        parser.addXML(new File(getClass().getResource("/com/plum/oatmeal/core/BaseStructures.xml").getFile()));
        parser.addXML(new File(getClass().getResource("/com/plum/oatmeal/function/Function.xml").getFile()));

        parser.parse();

        stringClassMap = parser.getStructureMap();
    }

    public static OatmealControlBuilder getInstance() {
        if (instance == null) {
            instance = new OatmealControlBuilder();
        }
        return instance;
    }

    public OatmealControl build(String name) {
        return build(stringClassMap.get(name));
    }

    public OatmealControl build(Class clazz) {
        OatmealControl control = null;
        try {
            control = ((OatmealControl) clazz.getConstructor().newInstance());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return control;
    }

    public Set<String> getControlStringSet() {
        return stringClassMap.keySet();
    }
}
