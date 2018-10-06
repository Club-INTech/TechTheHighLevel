package junit.unit;

import data.table.graph.Graph;
import org.junit.After;
import org.junit.Before;

public class Test_Graph {

    private Graph graphe;

    @Before
    public void before(){
        this.graphe=new Graph();
    }

    @After
    public void after(){
        this.graphe=null;
    }





}
