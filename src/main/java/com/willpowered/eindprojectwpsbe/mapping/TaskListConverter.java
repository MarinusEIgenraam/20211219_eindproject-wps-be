package com.willpowered.eindprojectwpsbe.mapping;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import org.modelmapper.AbstractConverter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UsersListConverter class map the property data from the list of users into the list of user names.
 *
 * @author Sasa Milenkovic
 */
public class TaskListConverter extends AbstractConverter<List<Task>, List<String>> {

    @Override
    protected List<String> convert(List<Task> tasks) {

        return tasks
                .stream()
                .map(Task::getTaskName)
                .collect(Collectors.toList());
    }
}