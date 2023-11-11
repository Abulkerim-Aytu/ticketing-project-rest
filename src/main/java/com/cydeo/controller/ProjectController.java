package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getProjects(){
        return ResponseEntity.ok(new ResponseWrapper("success",projectService.listAllProjects(), HttpStatus.OK));
    };

    @GetMapping("/{code}")
    public ResponseEntity<ResponseWrapper> getProjectByCode(@PathVariable("code") String code ){
        return ResponseEntity.ok(new ResponseWrapper("success",projectService.getByProjectCode(code), HttpStatus.OK));
    };

    @PostMapping
    public ResponseEntity<ResponseWrapper> createProject(@RequestBody ProjectDTO project){
        projectService.save(project);
        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new ResponseWrapper("success", HttpStatus.CREATED));
    };

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateProjects(@RequestBody ProjectDTO project){
        projectService.update(project);
        return ResponseEntity.ok(new ResponseWrapper("success", HttpStatus.OK));
    };

    @DeleteMapping("/{code}")
    public ResponseEntity<ResponseWrapper> deleteProjects(@PathVariable("code") String code){
        projectService.delete(code);
        return ResponseEntity.noContent().build();
    };

    @GetMapping("/manager/project-status")
    public ResponseEntity<ResponseWrapper> getProjectByManager(){
        List<ProjectDTO> projectDTOList = projectService.listAllProjects();
        return ResponseEntity.ok(new ResponseWrapper("success",projectDTOList, HttpStatus.OK));
    };

    @PutMapping("/manager/complete/{projectCode}")
    public ResponseEntity<ResponseWrapper> managerCompleteProjects(@PathVariable("projectCode")String projectCode){
        projectService.complete(projectCode);
        return ResponseEntity.ok(new ResponseWrapper("success", HttpStatus.OK));
    };
}
