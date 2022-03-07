/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treeviewdemo;

import java.awt.event.MouseEvent;
import java.util.List;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;

/**
 *
 * @author 91959
 */
public class TreeViewDemo extends Application  {
    
    @Override
    public void start(Stage primaryStage) {
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
          primaryStage.setTitle("Tree View Sample");        
        
        TreeItem<String> rootItem = new TreeItem<> ("AGNISYS PROJECT");
        TreeItem<String> rootItem1 = new TreeItem<> ("PROJECT1");
        TreeItem<String> rootItem2 = new TreeItem<> ("PROJECT2");
        TreeItem<String> rootItem3 = new TreeItem<> ("PROJECT3");
        
        rootItem.getChildren().addAll(rootItem1,rootItem2,rootItem3);
        rootItem.setExpanded(true);
        for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<> ("file" + i);            
            rootItem1.getChildren().add(item);
        }
        
        for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<> ("file" + i);            
            rootItem2.getChildren().add(item);
        }for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<> ("file" + i);            
            rootItem3.getChildren().add(item);
        }
      
        ObservableList<TreeItem<String>> child=rootItem.getChildren();
        for(TreeItem<String> c: child){
            c.setExpanded(true);
        }
        TreeView<String> tree = new TreeView<> (rootItem);        
        TreeItem<String> item=tree.getSelectionModel().getSelectedItem();
        
        StackPane root = new StackPane();
        root.getChildren().add(tree);
        
        primaryStage.setScene(new Scene(root, 900, 750));
        primaryStage.show();
        
//        ObservableList<TreeItem<String>> children = 
//        tree.getSelectionModel().getSelectedItem().getChildren();
//
//        for (TreeItem<String> child : children) {
//            System.out.println(child.getValue());
//        }
//        
 
    
    }
//    public static void myfunc(TreeItem t){
//        if(t==null){
//            return;
//        }
//    }
//    public boolean isDirectory(){
//        return false;
//    }
    public static void main(String[]args){
        launch(args);
       
    }
    
}
