public Task saveTask(Task task) {

        if (dto.parentProjectId != null && dto.parentTaskId != null) {
        task.setParentTask(taskRepository.findById(dto.parentTaskId).get());
        task.setParentProject(projectRepository.findById(dto.parentProjectId).get());
        } else if (dto.parentProjectId == null && dto.parentTaskId != null) {
        task.setParentTask(taskRepository.findById(dto.parentTaskId).get());
        } else if (dto.parentProjectId != null && dto.parentTaskId == null) {
        task.setParentProject(projectRepository.findById(dto.parentProjectId).get());
        } else {
        throw new RecordNotFoundException("No parent found");
        }
        User currentUser = userAuthenticateService.getCurrentUser();

        if (dto.taskOwnerName != null) {
        task.setTaskOwner(userRepository.findByUsername(dto.taskOwnerName).get());
        } else if (dto.taskOwnerName == null && currentUser == null) {
        throw new UserNotFoundException("No user");
        } else {
        task.setTaskOwner(currentUser);
        }

        task.setTaskId(dto.taskId);
        task.setTaskName(dto.taskName);
        task.setIsRunning(true);
        task.setDescription((dto.description));
        task.setStartTime(dto.startTime);
        task.setEndTime(dto.endTime);

        return taskRepository.save(task);
        }