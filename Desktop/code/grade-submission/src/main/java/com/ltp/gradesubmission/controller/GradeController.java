package com.ltp.gradesubmission.controller;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ltp.gradesubmission.Constants;
import com.ltp.gradesubmission.Grade;
import com.ltp.gradesubmission.repository.GradeRepository;

@Controller
public class GradeController {

    GradeRepository gradeRepository = new GradeRepository();

    @GetMapping("/")
    public String getForm(Model Model, @RequestParam(required = false) String id){
        // Model.addAttribute(errormsg, "This is the error msg");
        // Grade grade = new Grade("sumit", "cs", "100");
        // Model.addAttribute("grades", studentGrades);
        // Model.addAttribute("grade", new Grade("sumit","cs","22"));
        // return "grades";

        // Grade grade;
        int index = getGradeIndex(id);
        Model.addAttribute("grade", index==Constants.NOT_FOUND ? new Grade() : gradeRepository.getGrade(index) );
        return "form";
        // if(index==Constants.NOT_FOUND){
        //     grade = new Grade();
        // }else{
        //     grade = studentGrades.get(getGradeIndex(id));
        // }
        // Model.addAttribute("grade", grade);
        
    }

    @PostMapping("/handleSubmit")
    public String submitGrade(@Valid Grade grade, BindingResult result){

            // System.out.println(result.hasErrors()); //boolean val
            // if(result.hasErrors()){
            //     System.out.println("has errors!");
            // }
            if(result.hasErrors()){
                // return "redirect:/";
                return "form";
            }
            int index = getGradeIndex(grade.getId());
            if(index == Constants.NOT_FOUND)   {
                // System.out.println("Grade ID not found: " +grade.getId());
                // studentGrades.add(grade);
                gradeRepository.addGrade(grade);
            }else{
                // studentGrades.set(getGradeIndex(grade.getId()), grade);
                gradeRepository.updateGrade(index, grade);
            }
            
            return "redirect:/grades";
    }

    @GetMapping("/grades")
    public String getGrades(Model model){
        model.addAttribute("grades", gradeRepository.getGrades());
        return "grades";
    }

    public Integer getGradeIndex(String id){
        for (int i = 0; i < gradeRepository.getGrades().size(); i++) {
            System.out.println("current ID: "+gradeRepository.getGrades().get(i).getId());
            if(gradeRepository.getGrades().get(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }
}
