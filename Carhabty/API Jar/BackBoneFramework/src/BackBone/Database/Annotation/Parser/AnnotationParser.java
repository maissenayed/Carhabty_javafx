/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackBone.Database.Annotation.Parser;

import BackBone.Database.Annotation.Interfaces.Entity;
import java.lang.annotation.Annotation;

/**
 *
 * @author Azgard
 */
public class AnnotationParser<T> {
    
    
    public String getTableName(T entity){
     
        Class c=entity.getClass();
        Annotation an=c.getAnnotation(Entity.class);
        Entity s=(Entity)an;
        
    return s.tableName();
            }
}
