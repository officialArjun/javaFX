/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treeviewdemo;

import java.awt.event.MouseEvent;
import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import sun.plugin2.ipc.windows.WindowsEvent;

/**
 *
 * @author 91959
 */
public class TreeViewDemo extends Application  {
    Controller controller;
    private void onClose(WindowEvent event) {
    
        FileOutputStream out = null;
        try{
            out=new FileOutputStream("myTasks.xml");
            XMLEncoder encoder=new XMLEncoder(out);
            encoder.writeObject(controller.getTasksMap());
            encoder.close();
        }catch(Exception e){
            if(out!=null)
                try{
                    out.close();
                }catch(IOException ex){
                    ex.printStackTrace();
                }
                
            
        }
    
    
    
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader= FXMLLoader.load(getClass().getResource("ui.fxml"));
        GridPane grid=loader.load();
        controller=loader.getController();
        Scene scene=new Scene(grid,600,400);
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(false);
        primaryStage.setOnCloseRequest(this::onClose);
        primaryStage.show();
    
    }
   
    public static void main(String[]args){
        launch(args);
       
    }

    
    
}
