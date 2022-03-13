package treeviewdemo;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ReadOnlyIntegerProperty;
import static javafx.beans.property.ReadOnlyIntegerProperty.readOnlyIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller implements  Initializable {
    private Task currentTask=new Task();
    private final ObservableList<Task> tasks=FXCollections.observableArrayList();
    @FXML
    private TableView<Task> tasksTable;
    @FXML
    private TableColumn<Task,String> priorityColumn;
    @FXML
    private TableColumn<Task,String> descriptionColumn;
    @FXML
    private TableColumn<Task,String> progressColumn;
    @FXML
    private ComboBox<String> priorities;

    @FXML
    private TextField taskDescription;

    @FXML
    private Button add;

    @FXML
    private Spinner<Integer> progressSpinner;

    @FXML
    private CheckBox completionStatus;

    @FXML
    private Button cancel;
    
    @FXML
    private ProgressBar progressBar;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        priorities.getItems().addAll("High","Medium","Low");
        progressSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0));
        progressSpinner.valueProperty().addListener(new ChangeListener<Integer>(){
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue,Integer newValue) {
            if(newValue.intValue()==100){
                completionStatus.setSelected(true);
            }
            else{
                completionStatus.setSelected(false);
            }
//            System.out.println(currentTask.getPriority());
//            System.out.println(currentTask.getDescription());
//            System.out.println(currentTask.getProgress());
//            currentTask.setDescription(newValue.toString());
                
            }
            
        });
        ReadOnlyIntegerProperty intProgress = readOnlyIntegerProperty(progressSpinner.valueProperty());
        progressBar.progressProperty().bind(intProgress.divide(100.00));
        priorities.valueProperty().bindBidirectional(currentTask.priorityProperty());
        taskDescription.textProperty().bindBidirectional(currentTask.descriptionProperty());
        progressSpinner.getValueFactory().valueProperty().bindBidirectional(currentTask.progressProperty());
        tasksTable.setItems(tasks);
        priorityColumn.setCellValueFactory(x->x.getValue().priorityProperty());
        descriptionColumn.setCellValueFactory(x -> x.getValue().descriptionProperty());
        progressColumn.setCellValueFactory(x -> Bindings.concat(x.getValue().progressProperty(),"%"));
//    
//        tasks.add(new Task(1,"High","TreeView",30));
//        tasks.add(new Task(2,"Medium","TreeView",20));
//        tasks.add(new Task(3,"High","TreeView",10));
        //String Binding on Button
        StringBinding addButtonTextBinding=new StringBinding(){
            {
            super.bind(currentTask.idProperty());
            }
            
            @Override
            protected String computeValue() {
              if(currentTask.getId()==null){
                  return "Add";
              }
              else{
                  return "Update";
              }
            }
            
        };
        add.textProperty().bind(addButtonTextBinding);
        add.disableProperty().bind(Bindings.greaterThan(2,currentTask.descriptionProperty().length()));
        //Adding Listener to taskTable
        tasksTable.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Task> observable,Task oldValue,Task newValue)->{
                    setCurrentTask(newValue);
                });
        //Add & Update Button Event
//        add.addEventFilter(MouseEvent.MOUSE_PRESSED,(e)->{
//                System.out.println("Filter: Mouse Pressend");
//        });
//        add.addEventHandler(MouseEvent.MOUSE_PRESSED,new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent event) {
//                System.out.println("Handler: "+event.getEventType().getName());
//            }
//            
//        });
//        add.setOnAction(e->{
//            System.out.println("Action Event");
//        }); 
         
        
    
    
    }
    int lastId=0;
    private final HashMap<Integer,Task> tasksMap=new HashMap<>();
    //Add Button event
    public HashMap<Integer,Task> getTasksMap(){
        return tasksMap;
    }
    @FXML
    void addButtonClicked(ActionEvent event) {
        if(currentTask.getId()==null){
            Task t=new Task(++lastId,currentTask.getPriority(),currentTask.getDescription(),currentTask.getProgress());
            tasks.add(t);
            tasksMap.put(lastId, t);
        }else{
            Task t=tasksMap.get(currentTask.getId());
            t.setDescription(currentTask.getDescription());
            t.setPriority(currentTask.getPriority());
            t.setProgress(currentTask.getProgress());
        }
        setCurrentTask(null);
    }
    //Cancel Button event
    @FXML
    void cancelButtonClicked(ActionEvent event) {
        Alert alert=new Alert(INFORMATION);
        alert.setHeaderText("This goes into header section");
        alert.setTitle("Title Bar");
        alert.setContentText("This is a detailed information");
        alert.showAndWait();
        setCurrentTask(null);
        tasksTable.getSelectionModel().clearSelection();
            
    
    }
    private void setCurrentTask(Task selectedTask) {
        if(selectedTask!=null){
            currentTask.setId(selectedTask.getId());
            currentTask.setPriority(selectedTask.getPriority());
            currentTask.setDescription(selectedTask.getDescription());
            currentTask.setProgress(selectedTask.getProgress());
            
        }else{
            currentTask.setId(null);
            currentTask.setPriority("");
            currentTask.setDescription("");
            currentTask.setProgress(0);
            
        }
    }
    
    void setTasksMap(HashMap<Integer,Task> initialTasksMap){
        tasksMap.clear();
        tasks.clear();
        tasksMap.putAll(initialTasksMap);
        tasks.addAll(initialTasksMap.values());
        lastId=tasksMap.keySet().stream().max(Integer::compare).get();
        
    }
}
