package fr.istic.gm.weassert.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocalVariableParsed implements MethodSignature {

    private String name;
    private String desc;
    private List<String> localVariables;
}
