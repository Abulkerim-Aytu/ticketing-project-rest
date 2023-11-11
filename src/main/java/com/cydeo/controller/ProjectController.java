package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> getProjects(){
        return ResponseEntity.ok(new ResponseWrapper("Projects are successfully retrieved",projectService.listAllProjects(), HttpStatus.OK));
    };

    @GetMapping("/{code}")
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> getProjectByCode(@PathVariable("code") String code ){
        return ResponseEntity.ok(new ResponseWrapper("Projects is successfully retrieved",projectService.getByProjectCode(code), HttpStatus.OK));
    };

    @PostMapping
    @RolesAllowed({"Manager","Admin"})
    public ResponseEntity<ResponseWrapper> createProject(@RequestBody ProjectDTO project){
        projectService.save(project);
        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new ResponseWrapper("Projects is successfully created", HttpStatus.CREATED));
    };

    @PutMapping
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> updateProjects(@RequestBody ProjectDTO project){
        projectService.update(project);
        return ResponseEntity.ok(new ResponseWrapper("Projects is successfully update", HttpStatus.OK));
    };

    @DeleteMapping("/{code}")
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> deleteProjects(@PathVariable("code") String code){
        projectService.delete(code);
        return ResponseEntity.noContent().build();
    };

    @GetMapping("/manager/project-status")
    public ResponseEntity<ResponseWrapper> getProjectByManager(){
        List<ProjectDTO> projectDTOList = projectService.listAllProjects();
        return ResponseEntity.ok(new ResponseWrapper("Projects is successfully retrieved",projectDTOList, HttpStatus.OK));
    };

    @PutMapping("/manager/complete/{projectCode}")
    public ResponseEntity<ResponseWrapper> managerCompleteProjects(@PathVariable("projectCode")String projectCode){
        projectService.complete(projectCode);
        return ResponseEntity.ok(new ResponseWrapper("Projects is successfully completed", HttpStatus.OK));
    };
}
