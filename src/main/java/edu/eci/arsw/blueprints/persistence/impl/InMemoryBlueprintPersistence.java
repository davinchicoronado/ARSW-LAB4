/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author hcadavid
 */
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp=new Blueprint("_authorname_", "_bpname_ ",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {    
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBlueprintsForAutor(String author) throws BlueprintNotFoundException{
            Set<Blueprint> bSprint = new HashSet<>();
            
            Blueprint bprintprov;
            for(Map.Entry<Tuple<String,String>,Blueprint>  entry :  blueprints.entrySet()){
                bprintprov=entry.getValue();
                if(bprintprov.getAuthor()==author){
                    bSprint.add(bprintprov);
                }
            }
            if (bSprint.isEmpty()){
                throw new BlueprintNotFoundException("The author does not exist: "+author);
            }
            
            return bSprint;
    }
    
    @Override
    public Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException{
        
        Set<Blueprint> bSprint = new HashSet<>();
        for(Map.Entry<Tuple<String,String>,Blueprint>  entry :  blueprints.entrySet()){
            bSprint.add(entry.getValue());
        }
        if (bSprint.isEmpty()){
            throw new BlueprintNotFoundException("There are not any blueprint");
        }
        
        return bSprint;
    
    }
    
}
