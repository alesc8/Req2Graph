package it.unige.req2graph;
/**
 * Un singolo arco con etichetta del grafo 
 * @author Alessandro Scotto
 *
 */

import org.jgrapht.graph.DefaultEdge;

public class LabelEdge<V> extends DefaultEdge {
    private ObjRequirement v1;
    private ObjRequirement v2;
    private String label;
    private static final long serialVersionUID = 1L;

    public LabelEdge(ObjRequirement v1, ObjRequirement v2, String label) {
        this.v1 = v1;
        this.v2 = v2;
        this.label = label;
    }

    public LabelEdge(String label) {
        this.label = label;
    }
    public ObjRequirement getV1() {
        return v1;
    }

    public ObjRequirement getV2() {
        return v2;
    }
    
    @Override
    public String toString() {
        return label;
    }	
}
