package com.iqmsoft.ui;

import com.iqmsoft.model.Todo;
import com.iqmsoft.service.TodoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;



@Route("todo")
public class TodoUI  extends VerticalLayout {


    @Autowired
    TodoService todoService;



    @PostConstruct
    public void initialize(){
        setLayout();
    }

    public void setLayout(){

       TextField textField=new TextField("Task");
       Button addBtn=new Button("Add");

        Grid<Todo> todoGrid=new Grid<>();

        todoGrid.addColumn(Todo::getTask).setHeader("Task");

       addBtn.addClickListener(buttonClickEvent -> {

           Todo todo=new Todo();
           todo.setTask(textField.getValue());
           todo.setCompleted(0);

           if(todoService.save(todo)!=null){
               Notification.show("Task Added");
               todoGrid.setItems(todoService.findAll());
           }
           else{
               Notification.show("Error Adding Task");
           }

       });

        todoGrid.setItems(todoService.findAll());

        add(textField);
        add(addBtn);
        add(todoGrid);


    }


}
