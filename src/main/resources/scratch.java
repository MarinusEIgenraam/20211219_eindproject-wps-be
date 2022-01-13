public Task saveTask(TaskInputDto dto){
        Task task=saveTaskData(dto);

        if(dto.parentProjectId==null&&dto.parentTaskId!=null){
        task.setParentTask(taskRepository.findById(dto.parentTaskId).get());
        }else if(dto.parentProjectId!=null&&dto.parentTaskId==null){
        task.setParentProject(projectRepository.findById(dto.parentProjectId).get());
        }else{
        throw new RecordNotFoundException("No parent found");
        }
        User currentUser=userAuthenticateService.getCurrentUser();

        if(dto.taskOwnerName!=null){
        task.setTaskOwner(userRepository.findByUsername(dto.taskOwnerName).get());
        }else if(dto.taskOwnerName==null&&currentUser==null){
        throw new UserNotFoundException("No user");
        }else{
        task.setTaskOwner(currentUser);
        }

        Task parentTask=taskRepository.save(task);


        if(dto.taskTaskList!=null){
        List<Task> newTaskList=new ArrayList<>();

        for(TaskInputDto taskTaskInputDto:dto.taskTaskList){
        taskTaskInputDto.parentTaskId=parentTask.getTaskId();
        newTaskList.add(saveTask(taskTaskInputDto));
        }

        parentTask.setTaskTaskList(newTaskList);
        }

        return parentTask;
        }