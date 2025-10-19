package com.example.kavana_resume_system.Controller;

import com.example.kavana_resume_system.Entity.Skill;
import com.example.kavana_resume_system.Entity.User;
import com.example.kavana_resume_system.Repository.SkillRepository;
import com.example.kavana_resume_system.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        if (skill.getUser() != null && skill.getUser().getId() != null) {
            User user = userRepository.findById(skill.getUser().getId()).orElse(null);
            skill.setUser(user);
        }
        return skillRepository.save(skill);
    }


    @GetMapping("/{id}")
    public Skill getSkillById(@PathVariable Long id) {
        return skillRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Skill updateSkill(@PathVariable Long id, @RequestBody Skill skillDetails) {
        Skill skill = skillRepository.findById(id).orElse(null);
        if (skill != null) {
            skill.setName(skillDetails.getName());
            skill.setLevel(skillDetails.getLevel());
            skill.setCategory(skillDetails.getCategory());
            skill.setDescription(skillDetails.getDescription());
            skill.setUser(skillDetails.getUser());

            return skillRepository.save(skill);
        }
        return null;
    }


    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable Long id) {
        skillRepository.deleteById(id);
    }
}
