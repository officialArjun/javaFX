/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treeviewdemo;

import javafx.beans.property.*;

/**
 *
 * @author 91959
 */
public class Task {
    StringProperty priority =new SimpleStringProperty();
    StringProperty description =new SimpleStringProperty();
    ObjectProperty<Integer> progress=new SimpleObjectProperty<>(0);
    ObjectProperty<Integer> id=new SimpleObjectProperty<>(null);
    public Task(){
        
    }
    Task(Integer id,String priority,String  description,Integer progress){
        this.id.set(id);
        this.priority.set(priority);
        this.description.set(description);
        this.progress.set(progress);
    }
    public void setId(Integer value){
        this.id.set(value);
    }
    public Integer getId(){
        return this.id.get();
    }
    public ObjectProperty<Integer> idProperty(){
        return this.id;
    }
    
    
    public String getPriority(){
        return priority.get() ;
    }
    public void setPriority(String priority){
        this.priority.set(priority);
    }
    public StringProperty priorityProperty(){
        return priority ;
    }
    
    public String getDescription(){
        return description.get() ;
    }
    
    public void setDescription(String description){
        this.description.set(description);
    }
    public StringProperty descriptionProperty(){
        return description ;
    }
    public Integer getProgress(){
        return progress.get();
    }
    
    public void setProgress(Integer progress){
        this.progress.set(progress);
    }
    
    public ObjectProperty<Integer> progressProperty(){
        return progress;
    }

    
    
    
    






}
