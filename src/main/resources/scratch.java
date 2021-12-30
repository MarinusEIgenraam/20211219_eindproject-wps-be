import org.springframework.web.bind.annotation.GetMapping;

@GetMapping()
public List<ProjectDto> getProjects() {
        var dtos = new ArrayList<ProjectDto>();
        List<Project> projects;

        projects = projectService.getProjects();
        for (Project project : projects) {
        dtos.add(ProjectDto.fromProject(project));
        }
        return dtos;
        }

public List<Project> getProjects() {
        return projectRepository.findAll();
        }