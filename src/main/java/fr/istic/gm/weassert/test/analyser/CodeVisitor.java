package fr.istic.gm.weassert.test.analyser;

import fr.istic.gm.weassert.test.model.VariableDefinition;

import java.util.Map;

public interface CodeVisitor {

    Map<VariableDefinition, Object> getVariableValues();

    void initVariableValues();

    void visit(String localVariable);
}
