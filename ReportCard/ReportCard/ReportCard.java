package com.ahmedmakramallah.reportcard;

/**
 * Created by ahmed on 7/24/2017.
 */

public class ReportCard {
    private String myName;
    private String myAcademicYear;
    private char mySection;
    private int myComputerScience;
    private int myLogicDesign;
    private int mySoftwareEngineering;
    private int myAssemblylanguage;
    private int myDegree;

    public ReportCard(String name, String academicYear, char section, int computerScience, int logicDesign, int softwareEngineering, int assemblylanguage){
        myName = name;
        myAcademicYear = academicYear;
        mySection = section;
        myComputerScience = computerScience;
        myLogicDesign = logicDesign;
        mySoftwareEngineering = softwareEngineering;
        myAssemblylanguage = assemblylanguage;
    }

    // get The name of the student
    public String getName(){
        return myName;
    }
    // set the name of the student
    public void setName(String name){
        myName = name;
    }
    // get the academic year of the student
    public String getAcademicYear(){
        return myAcademicYear;
    }
    // set the academic year of the student
    public void setAcademicYear(String academicYear){
        myAcademicYear = academicYear;
    }
    // get the section of the student
    public char getSection(){
        return mySection;
    }
    // set the section of the student
    public void setSection(char section){
        mySection = section;
    }
    // get computer science marks
    public int getComputerScienceMarks(){
        return myComputerScience ;
    }
    // set the computer science marks
    public void setComputerScienceMarks(int computerScienceMarks){
        myComputerScience = computerScienceMarks;
    }
    // get logic design marks
    public int getLogicDesignMarks(){
        return myLogicDesign;
    }
    // set logic design marks;
    public void setLogicDesignMarks(int logicDesignMarks){
        myLogicDesign = logicDesignMarks;
    }
    // get software engineering marks
    public int getSoftwareEngineeringMarks(){
        return mySoftwareEngineering;
    }
    // set software engineering marks
    public void setSoftwareEngineeringMarks(int softwareEngineeringMarks){
        mySoftwareEngineering = softwareEngineeringMarks;
    }
    // get  assembly language marks
    public int getAssemblylanguageMarks(){
        return myAssemblylanguage;
    }
    // set assembly language marks
    public void setAssemblylanguageMarks(int assemblylanguageMarks){
        myAssemblylanguage = assemblylanguageMarks;
    }
    // get total marks
    public int getTotalMarks(){
        myDegree = myComputerScience + myLogicDesign+ mySoftwareEngineering + myAssemblylanguage;
        return myDegree;
    }
    // get the the percentage of mydegree
    public double getPercentage(){
        return ((myDegree/4) * 100);
    }

    @Override
    public String toString() {
        return "myResult" + "\n"
                + "Logic Design = " + myLogicDesign + "\n"
                + "Computer Science = " + myComputerScience + "\n"
                + "Software Engineering = " + mySoftwareEngineering + "\n"
                + "Assembly Language = " + myAssemblylanguage;

    }
}
