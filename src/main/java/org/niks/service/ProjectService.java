package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.niks.ProjectSort;
import org.niks.entity.Project;
import org.niks.entity.Status;
import org.niks.entity.User;
import org.niks.repository.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public final class ProjectService implements IProjectService {

    private final IUserService userService;
    private final IProjectRepository projectRepository;

    @Autowired
    public ProjectService(IUserService userService, IProjectRepository projectRepository) {
        this.userService = userService;
        this.projectRepository = projectRepository;
    }

    final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public void create(@NotNull final String projectName, String projectDescription, String startDate,
                       String finishDate) {
        final User currentUser = userService.getCurrentUser();

        try {
            Project project = new Project(
                    currentUser.getUserID(),
                    randomNumber(),
                    projectName,
                    projectDescription,
                    dateFormat.parse(startDate),
                    dateFormat.parse(finishDate),
                    Status.PLANNED,
                    new Date());
            projectRepository.save(project);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    public List<Project> list() {
        return projectList();
    }

    @NotNull
    public List<Project> list(@NotNull final String order) {
        final List<Project> projectList = projectList();
        projectList.sort(ProjectSort.valueOf(order).getProjectComparator());
        return projectList;
    }

    @NotNull
    public List<Project> list(@NotNull final ProjectSort order) {
        final List<Project> projectList = projectList();
        projectList.sort(order.getProjectComparator());
        return projectList;
    }

    public void remove(@NotNull final String projectToRemove) {
        projectRepository.remove(projectToRemove);
    }

    public void clear() {
        projectRepository.removeAll();
    }

    @NotNull
    public List<Project> projectSearch(@NotNull final String source) {
        final List<Project> projectList = projectList();
        return projectList
                .stream()
                .filter(project -> project.getProjectName().toLowerCase().contains(source.toLowerCase()) ||
                        project.getProjectDescription().toLowerCase().contains(source.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Project findExactMatch(@NotNull final String name) {
        Project project = null;
        if (projectRepository.findOne(name).isPresent()) {
            project = projectRepository.findOne(name).get();
        }
        return project;
    }

    public void update(@NotNull final Project project) {
        projectRepository.update(project);
    }

    @NotNull
    public List<Project> projectList() {
        return projectRepository.findAll();
    }

    public void serialize() throws IOException {
        projectRepository.serialize();
    }

    public long randomNumber() {
        final SecureRandom random = new SecureRandom();
        return Math.abs(random.nextInt(Integer.MAX_VALUE));
    }
}
