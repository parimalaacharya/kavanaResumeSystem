package com.example.kavana_resume_system.Controller;

import com.example.kavana_resume_system.Entity.Course;
import com.example.kavana_resume_system.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseRepository coursesRepository;

    // Get all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return coursesRepository.findAll();
    }

    // Create a new course
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return coursesRepository.save(course);
    }

    // Get course by id
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return coursesRepository.findById(id).orElse(null);
    }

    // Update course
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        Course course = coursesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + id));

        course.setName(courseDetails.getName());
        course.setDetails(courseDetails.getDetails());
        course.setInstitute(courseDetails.getInstitute());
        course.setDurationMonths(courseDetails.getDurationMonths());
        course.setCertificateLink(courseDetails.getCertificateLink());

        return coursesRepository.save(course);
    }

    // Delete course
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        coursesRepository.deleteById(id);
    }
}
