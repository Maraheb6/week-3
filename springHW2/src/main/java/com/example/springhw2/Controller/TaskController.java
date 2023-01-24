package com.example.springhw2.Controller;

import com.example.springhw2.pojo.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

ArrayList<Task>tasks=new ArrayList<>();

@GetMapping("/get")
    public ArrayList displayTask(){
    return tasks;
}
 @PostMapping("/add")
    public String addTask(@RequestBody Task task){
    tasks.add(task);
    return "Task Added";
}
@PutMapping("/update/{index}")
    public String updateTask(@PathVariable int index,@RequestBody Task task){
    tasks.set(index,task);
    return "Task updated";
}

@DeleteMapping("/delete/{index}")
    public String deleteTask(@PathVariable int index){
    tasks.remove(index);
    return "Task Deleted";
}
@PutMapping("/change/{index}")
    public String changeTask(@PathVariable int index) {
    Task task1 = tasks.get(index);
    if (task1.isStatus() == false)
        task1.setStatus(true);
    else
        task1.setStatus(false);
    return "Task status changed";
}
@GetMapping("/search/{title}")
    public Task searchTask(@PathVariable String title){
    for(Task t:tasks) {
        boolean equals = t.getTitle().equals(title);
        if (equals)
            return t;
    }

        return null;
}


}
