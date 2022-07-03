package org.mowitnow.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class InputParameters {
    private Pelouse pelouse;
    private List<Tondeuse> tondeuseList;
    private Map<Integer, List<Action>> actionsByTondeuseIdMap;
}
