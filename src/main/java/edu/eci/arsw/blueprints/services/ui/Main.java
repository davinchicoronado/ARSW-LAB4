/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services.ui;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author David Coronado
 */
public class Main {
    public static void main(String a[]) {     
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bpService = ac.getBean(BlueprintsServices.class);
        
        Point[] pts=new Point[]{new Point(10, 10),new Point(10, 10), new Point(10,10)};
        Blueprint bp=new Blueprint("john", "thepaint2",pts);
        
        Point[] pts2=new Point[]{new Point(214, 214),new Point(215, 123),new Point(214, 214), new Point(22,25)};
        Blueprint bp2=new Blueprint("Carlos", "thepaintECI",pts2);
        
        Blueprint bp3=new Blueprint("David","Plano casita");
        Blueprint bp4=new Blueprint("David","Plano Centro Comercial Portal 80");
        
        
        // Agregar planos
        try {
            bpService.addNewBlueprint(bp);
            bpService.addNewBlueprint(bp2);
            bpService.addNewBlueprint(bp3);
            bpService.addNewBlueprint(bp4);
        } catch (BlueprintPersistenceException ex) {
            System.out.println("Error:" + ex.getMessage());
       
        }
        
        // Consultar todos los planos
        Set<Blueprint> bPrint = new HashSet<>();
        
        try {
            bPrint = bpService.getAllBlueprints();
            System.out.println(bPrint.toString());
        } catch (BlueprintNotFoundException ex) {
            System.out.println("Error:" + ex.getMessage());
        }

        // Consultar todos los planos de un autor
        try {
            bPrint=bpService.getBlueprintsByAuthor("David");
            System.out.println(bPrint.toString());
        } catch (BlueprintNotFoundException ex) {
            System.out.println("Error:" + ex.getMessage());
        }

        
        // Consultar plano dado el nombre y el autor
        try {
            System.out.println("Consultar plano: "+bpService.getBlueprint("john", "thepaint2").toString());
        } catch (BlueprintNotFoundException ex) {
            System.out.println("Error:" + ex.getMessage());
        }

        
        
        
    }    
}
