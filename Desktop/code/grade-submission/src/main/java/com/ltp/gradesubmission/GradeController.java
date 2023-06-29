package com.ltp.gradesubmission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GradeController {

    List<Grade>studentGrades = new ArrayList<>();

    @RequestMapping("/")
    public String getForm(Model Model, @RequestParam(required = false) String name){
        // Model.addAttribute(errormsg, "This is the error msg");
        // Grade grade = new Grade("sumit", "cs", "100");
        // Model.addAttribute("grades", studentGrades);
        // Model.addAttribute("grade", new Grade("sumit","cs","22"));
        // return "grades";

        Grade grade;
        if(getGradeIndex(name)==-1000){
            grade = new Grade();
        }else{
            grade = studentGrades.get(getGradeIndex(name));
        }
        Model.addAttribute("grade", grade);
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submitGrade(Grade grade){
            // System.out.println(grade);
            if(getGradeIndex(grade.getName())==-1000)   {
                studentGrades.add(grade);
            }else{
                studentGrades.set(getGradeIndex(grade.getName()), grade);
            }
            
            return "redirect:/grades";
    }

    @RequestMapping("/grades")
    public String getGrades(Model model){
        model.addAttribute("grades", studentGrades);
        return "grades";
    }

    public Integer getGradeIndex(String name){
        for (int i = 0; i < studentGrades.size(); i++) {
            if(studentGrades.get(i).getName().equals(name)) return i;
        }
        return -1000;
    }
}
