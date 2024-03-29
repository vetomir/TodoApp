package pl.gregorymartin.udemykursspring.model;

import pl.gregorymartin.udemykursspring.model.event.TaskEvent;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task extends BaseTask{
    private LocalDateTime deadline;

    @ManyToOne
    @JoinColumn(name = "task_group_id")
    private TaskGroup group;


    public Task() {
    }

    public Task(String description, LocalDateTime deadline) {
        this(description, deadline, null);
    }

    public Task(String description, LocalDateTime deadline, TaskGroup group) {
        this.description = description;
        this.deadline = deadline;
        if (group != null) {
            this.group = group;
        }
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(final LocalDateTime deadline) {
        this.deadline = deadline;
    }

    TaskGroup getGroup() {
        return group;
    }

    public void setGroup(final TaskGroup group) {
        this.group = group;
    }

    public TaskEvent toggleDone() {
        this.setDone(!this.isDone());
        return TaskEvent.changed(this);
    }

    public void updateFrom(Task source) {
        description = source.description;
        done = source.done;
        deadline = source.deadline;
        group = source.group;
    }
}

