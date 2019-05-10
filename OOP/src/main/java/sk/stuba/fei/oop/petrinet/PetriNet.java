package sk.stuba.fei.oop.petrinet;

import sk.stuba.fei.oop.exceptions.ArcWeightLowerThanOneException;
import sk.stuba.fei.oop.exceptions.LowTokensSumException;
import sk.stuba.fei.oop.exceptions.TransitionNotExecutableException;
import java.util.HashMap;
import java.util.Map;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class PetriNet {

    private HashMap<Long, Transition> transitionHashMap = new HashMap<>();
    private HashMap<Long, Place> placeHashMap = new HashMap<>();
    private HashMap<Long, Arc> arcHashMap = new HashMap<>();

    public void fire(Long id) {
        for (Map.Entry transition : transitionHashMap.entrySet()) {
            if (id == transition.getKey()) {
                Transition t = (Transition) transition.getValue();
                for (Arc a : t.getArcs()) {
                    test(a);
                    execute(t, a);
                }
            }
        }
    }

    private void execute(Transition t, Arc a) {
        if (t.isExecutable()) {
            a.execute();
        }
    }

    private void test(Arc a) {
        try {
            a.test();
        } catch (ArcWeightLowerThanOneException | TransitionNotExecutableException | LowTokensSumException e) {
            System.out.println(e);
        }
    }

    public void addTransition(Long id, Transition transition) {
        transitionHashMap.put(id, transition);
    }
    public void addPlace(Long id, Place place) {
        placeHashMap.put(id, place);
    }
    public void addArc(Long id, Arc arc) {
        arcHashMap.put(id, arc);
    }

    public Transition getTransitionById(long id) {
        Transition t = null;
        for (Map.Entry transition : transitionHashMap.entrySet()) {
            if ((Long) id == transition.getKey()) {
                t = (Transition) transition.getValue();
            }
        }
        return t;
    }

    public Place getPlaceById(long id) {
        Place p = null;
        for (Map.Entry place : placeHashMap.entrySet()) {
            if ((Long) id == place.getKey()) {
                p = (Place) place.getValue();
            }
        }
        return p;
    }

    public Arc getArcById(long id) {
        Arc a = null;
        for (Map.Entry arc : arcHashMap.entrySet()) {
            if ((Long) id == arc.getKey()) {
                a = (Arc) arc.getValue();
            }
        }
        return a;
    }

    public Node getNode(long id) {
        Place p;
        Transition t;

        for (Map.Entry place : placeHashMap.entrySet()) {
            if ((Long) id == place.getKey()) {
                p = (Place) place.getValue();
                return p;
            }
        }

        for (Map.Entry transition : transitionHashMap.entrySet()) {
            if ((Long) id == transition.getKey()) {
                t = (Transition) transition.getValue();
                return t;
            }
        }

        return null;
    }

    public HashMap<Long, Arc> getArcHashMap() {
        return arcHashMap;
    }
}
