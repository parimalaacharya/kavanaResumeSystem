package com.example.kavana_resume_system.Service;

import com.example.kavana_resume_system.Entity.Project;
import java.util.List;

public interface ProjectService1 {
    Project saveProject(Project project);
    List<Project> getAllProjects();
}
