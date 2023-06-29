package com.ltp.gradesubmission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GradeController {

    List<Grade>studentGrades = new ArrayList<>();

    @RequestMapping("/")
    public String index(Model Model){
        // Model.addAttribute(errormsg, "This is the error msg");
        // Grade grade = new Grade("sumit", "cs", "100");
        // Model.addAttribute("grades", studentGrades);
        Model.addAttribute("grade", new Grade("sumit","cs","22"));
        // return "grades";
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submitGrade(Grade grade){
            // System.out.println(grade);
            studentGrades.add(grade);
            return "redirect:/grades";
    }

    @RequestMapping("/grades")
    public String getGrades(Model model){
        model.addAttribute("grades", studentGrades);
        return "grades";
    }
}
