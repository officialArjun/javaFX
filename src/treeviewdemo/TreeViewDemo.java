/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treeviewdemo;

import java.awt.event.MouseEvent;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/**
 *
 * @author 91959
 */
public class TreeViewDemo extends Application  {
    Controller controller;
   
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ui.fxml"));
         
        GridPane grid = loader.load();
        controller=loader.getController();
        Scene scene=new Scene(grid,600,400);
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(false);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(this::onClose);
        controller.setTasksMap(readTasksFile());
        primaryStage.show();
    
    }
    private HashMap<Integer,Task> readTasksFile(){
            FileInputStream in=null;
            HashMap<Integer,Task> tasksMap=new HashMap<>();
            try{
                in=new FileInputStream("tasks.xml");
                XMLDecoder decoder=new XMLDecoder(in);
                tasksMap=(HashMap<Integer, Task>) decoder.readObject();
                decoder.close();
            }catch(Exception e){
                if(in!=null)
                    try{
                        in.close();
                    }catch(IOException ex){
                        ex.printStackTrace();
                    }
                e.printStackTrace();
                
            }finally{
                return tasksMap;
            }
    }
     private void onClose(WindowEvent event) {
    
        FileOutputStream out = null;
        try{
            out=new FileOutputStream("tasks.xml");
            XMLEncoder encoder = new XMLEncoder(out);
            encoder.writeObject(controller.getTasksMap());
            encoder.close();
        }catch(FileNotFoundException e){
            if(out != null){
                try{
                    out.close();
                }catch(IOException ex){
                    ex.printStackTrace();
                }
        }
            
        }
    }
   
    public static void main(String[]args){
        launch(args);
       
    }

    
    
}
