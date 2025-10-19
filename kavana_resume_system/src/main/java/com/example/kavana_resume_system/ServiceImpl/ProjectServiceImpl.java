package com.example.kavana_resume_system.ServiceImpl;

import com.example.kavana_resume_system.Entity.Project;
import com.example.kavana_resume_system.Repository.ProjectRepository;
import com.example.kavana_resume_system.Service.ProjectService1;
import com.example.kavana_resume_system.Service.ProjectService1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService1 {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}
