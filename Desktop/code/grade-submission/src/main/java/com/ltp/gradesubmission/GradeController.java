package com.ltp.gradesubmission;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GradeController {

    List<Grade>studentGrades = Arrays.asList(
        new Grade("sumit", "cs", "100"),
        new Grade("sumit", "cs", "100"),
        new Grade("sumit", "cs", "100")
    );

    @RequestMapping("/")
    public String index(Model Model){
        // Model.addAttribute(errormsg, "This is the error msg");
        // Grade grade = new Grade("sumit", "cs", "100");
        // Model.addAttribute("grades", studentGrades);
        Model.addAttribute("grade", new Grade("sumit", "cs", "100"));
        // return "grades";
        return "form";
    }
}
