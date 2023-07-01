package com.ltp.gradesubmission.service;

import java.util.List;

import org.springframework.ui.Model;

import com.ltp.gradesubmission.Constants;
import com.ltp.gradesubmission.Grade;
import com.ltp.gradesubmission.repository.GradeRepository;

public class GradeService {

    GradeRepository gradeRepository = new GradeRepository();

     public Grade getGrade(int index){
        return gradeRepository.getGrade(index);
    }

    public void addGrade(Grade grade){
        gradeRepository.addGrade(grade);
    }

    public void updateGrade(int index, Grade grade){
        gradeRepository.updateGrade(index, grade);
    }

    public List<Grade>getGrades(){
        return gradeRepository.getGrades();
    }

    public Integer getGradeIndex(String id){
        for (int i = 0; i < getGrades().size(); i++) {
            System.out.println("current ID: "+getGrades().get(i).getId());
            if(getGrades().get(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }

    public Grade getGradeById(String id){
         int index = getGradeIndex(id);
         return index==Constants.NOT_FOUND ? new Grade() : getGrade(index);
    }

    public void submitGrade(Grade grade){
        int index = getGradeIndex(grade.getId());
            if(index == Constants.NOT_FOUND)   {
                addGrade(grade);
            }else{
                updateGrade(index, grade);
            }
    }
}
