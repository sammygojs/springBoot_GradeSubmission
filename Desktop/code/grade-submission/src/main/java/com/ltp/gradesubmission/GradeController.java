package com.ltp.gradesubmission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GradeController {

    List<Grade>studentGrades = new ArrayList<>();

    @RequestMapping("/")
    public String getForm(Model Model, @RequestParam(required = false) String id){
        // Model.addAttribute(errormsg, "This is the error msg");
        // Grade grade = new Grade("sumit", "cs", "100");
        // Model.addAttribute("grades", studentGrades);
        // Model.addAttribute("grade", new Grade("sumit","cs","22"));
        // return "grades";

        Grade grade;
        if(getGradeIndex(id)==Constants.NOT_FOUND){
            grade = new Grade();
        }else{
            grade = studentGrades.get(getGradeIndex(id));
        }
        Model.addAttribute("grade", grade);
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submitGrade(@Valid Grade grade, BindingResult result){

            // System.out.println(result.hasErrors()); //boolean val
            if(result.hasErrors()){
                System.out.println("has errors!");
            }
            if(result.hasErrors()){
                // return "redirect:/";
                return "form";
            }
            if(getGradeIndex(grade.getId())==-1000)   {
                System.out.println("Grade ID not found: " +grade.getId());
                studentGrades.add(grade);
            }else{
                studentGrades.set(getGradeIndex(grade.getId()), grade);
            }
            
            return "redirect:/grades";
    }

    @RequestMapping("/grades")
    public String getGrades(Model model){
        model.addAttribute("grades", studentGrades);
        return "grades";
    }

    public Integer getGradeIndex(String id){
        for (int i = 0; i < studentGrades.size(); i++) {
            System.out.println("current ID: "+studentGrades.get(i).getId());
            if(studentGrades.get(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }
}
