public Project saveProject(@NotNull ProjectInputDto projectInputDto) {
        projectRepo.save(projectInputDto.toProject());
        var categoryById = categoryRepository.findById(projectInputDto.categoryId);
        Object category = optionalCategory.orElseThrow(() -> new RuntimeException("cat not found"));
        Project project = createProject(projectInputDto, category);
        List<User> optionalCollaborators = createCollaborators(projectInputDto);
        project.setCollaborators(optionalCollaborators);
        Project newProject = projectRepository.save(project);
        List<Task> newTaskList = getTasks(projectInputDto, newProject);
        newProject.setProjectTaskList(newTaskList);
        return newProject;
        }

private List<User> createCollaborators(ProjectInputDto projectInputDto) {
        List<User> optionalCollaborators = new ArrayList<>();
        if (projectInputDto.collaborators != null) {
        for (String username : projectInputDto.collaborators) {
        var optionalUser = userRepository.findById(username);
        optionalCollaborators.add(optionalUser.get());
        }
        }
        return optionalCollaborators;
        }

private List<Task> getTasks(ProjectInputDto projectInputDto, Project newProject) {
        List<Task> newTaskList = new ArrayList<>();
        for (TaskInputDto dto : projectInputDto.projectTaskList) {
        if (dto.taskOwnerName == null) {
        dto.taskOwnerName = userAuthenticateService.getCurrentUser().getUsername();
        }
        dto.parentProjectId = newProject.getProjectId();
        newTaskList.add(taskService.saveTask(dto));
        }
        return newTaskList;
        }

private Project createProject(ProjectInputDto projectInputDto, Category category) {
        Project project = new Project();
        project.setPubliclyVisible(projectInputDto.publiclyVisible);
        project.setProjectOwner(userAuthenticateService.getCurrentUser());
        project.setUrl(projectInputDto.url);
        project.setProjectName(projectInputDto.projectName);
        project.setDescription(projectInputDto.description);
        project.setCategory(category);
        project.setEndTime(projectInputDto.endTime);
        if (projectInputDto.startTime == null) {
        project.setStartTime(LocalDate.now());
        } else {
        project.setStartTime(projectInputDto.startTime);
        project.setEditedTime(LocalDate.now());
        }
        return project;
        }