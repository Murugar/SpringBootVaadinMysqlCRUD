package com.iqmsoft.service;

import com.iqmsoft.model.Todo;
import com.iqmsoft.repository.TodoRepository;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TodoService {

    // This is the concept of Dependency Injection. we use @Autowired to instantiate an instance of TodoRepository and make it available
    @Autowired
    TodoRepository todoRepository;


    public Todo save(Todo todo){

            try{
                return todoRepository.save(todo);
            }

            catch (Exception e){
                e.printStackTrace();
                return null;
            }
    }


    public List<Todo> findAll(){
        return todoRepository.findAll();
    }

}
