package com.atik.programmingquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionCollection extends AppCompatActivity {
    RadioGroup radioGroup;
    TextView lblQuestion;
    RadioButton optionA;
    RadioButton optionB;
    RadioButton optionC;
    RadioButton optionD;
    Button confirm;
    String rightAnswer;
    String Answer;
    public static List<QuestionModule> question_list;
    int score;
    public static String SUBJECT_NAME = "";
    public static ArrayList <ArrayList<QuestionModule>> questionBank = new ArrayList<>();
    public static ArrayList <HashMap<String, String>> subjectList = new ArrayList<>();
    LinearLayout rootLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        rootLay = findViewById(R.id.rootLay);
        confirm = findViewById(R.id.confirm);
        lblQuestion = findViewById(R.id.lblPergunta);
        optionA = findViewById(R.id.opcaoA);
        optionB = findViewById(R.id.opcaoB);
        optionC = findViewById(R.id.opcaoC);
        optionD = findViewById(R.id.opcaoD);
        score = 0;
        radioGroup = findViewById(R.id.radioGroup);
        loadQuestion();

    }




    @Override
    protected void onRestart(){
        super.onRestart();
        loadQuestion();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(rootLay!=null) rootLay.startAnimation(AnimationUtils.loadAnimation(QuestionCollection.this, R.anim.middle_to_top));
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    private void loadQuestion(){
        //Toast.makeText(getApplicationContext(), "Total Questions: "+question_list.size(), Toast.LENGTH_SHORT).show();
        if(question_list.size() > 0) {
            QuestionModule q = question_list.remove(0);
            lblQuestion.setText(q.getQuestion());
            List<String> answers = q.getAnswers();

            optionA.setText(answers.get(0));
            optionB.setText(answers.get(1));
            optionC.setText(answers.get(2));
            optionD.setText(answers.get(3));
            rightAnswer = q.getRightAnswer();
        } else {
            Intent intent = new Intent(this, ScoreActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    public void loadAnswer(View view) {
        int op = radioGroup.getCheckedRadioButtonId();

        switch (op){
            case R.id.opcaoA:
                Answer="A";
                break;

            case R.id.opcaoB:
                Answer="B";
                break;

            case R.id.opcaoC:
                Answer="C";
                break;

            case R.id.opcaoD:
                Answer="D";
                break;

            default:
                return;

        }

        radioGroup.clearCheck();

        this.startActivity(isRightOrWrong(Answer));

    }

    private Intent isRightOrWrong(String Answer){
        Intent screen;
        if(Answer.equals(rightAnswer)) {
            this.score += 1;
            screen = new Intent(this, RightActivity.class);

        }else {
            screen = new Intent(this, WrongActivity.class);
        }

        return screen;
    }



    //===============================================================================================







    //====================================================================
    //====================================================================
    public static  ArrayList <QuestionModule> questions;
    public static void createQuestionBank(){
        QuestionCollection.subjectList = new ArrayList<>();
        QuestionCollection.questionBank = new ArrayList<>();



        //------------- Subject 1
        questions = new ArrayList(){
            {
                add(new QuestionModule("Who is the father of C language?", "C", "Steve Jobs", "James Gosling", "Dennis Ritchie", "Rasmus Lerdorf"));
                add(new QuestionModule("Which of the following is not a valid C variable name?", "D", "int number;", "float rate;","int variable_count;", "int $main;"));
                add(new QuestionModule("All keywords in C are in _______", "A", "LowerCase letters", "UpperCase letters","CamelCase letters", "None of the mentioned"));
                add(new QuestionModule("Which is valid C expression?", "B", "int my_num = 100,000;", "int my_num = 100000;","int my num = 1000;", "int $my_num = 10000;"));
                add(new QuestionModule("Which of the following cannot be a variable name in C?", "C", "The basic data type of C", "Qualifier", "Short is the qualifier and int is the basic data type" , " All of the mentioned"));
                add(new QuestionModule("Which of the following declaration is not supported by C language?", "A", "String str;", "char *str;", "float str = 3e2;", "Both “String str;” and “float str = 3e2;”"));
                add(new QuestionModule("Which keyword is used to prevent any changes in the variable within a C program?", "C", "immutable", "mutable", "const", "volatile"));
                add(new QuestionModule("Which of the following typecasting is accepted by C language?", "C", "Widening conversions", "Narrowing conversions", "Widening & Narrowing conversions", "None of the mentioned"));
                add(new QuestionModule("What is an example of iteration in C?", "D", "for", "while", "do-while", "all of the mentioned"));
                add(new QuestionModule("Functions can return enumeration constants in C?", "A", "true", "false", "depends on the compiler", "depends on the standard"));
            }
        };
        QuestionModule.createQuestionsForSubject("C Programming", R.drawable.cpro, questions);



        //------------- Subject 2
        questions = new ArrayList(){
            {
                add(new QuestionModule("Who invented C++?", "D", "Dennis Ritchie", "Ken Thompson", "Brian Kernighan", "Bjarne Stroustrup"));
                add(new QuestionModule("What is C++?", "C", "C++ is an object oriented programming language", "C++ is a procedural programming language","C++ supports both procedural and object oriented programming language", "C++ is a functional programming language"));
                add(new QuestionModule("Which of the following is the correct syntax of including a user defined header files in C++?", "B", "#include [userdefined]", "#include “userdefined”","#include <userdefined.h>", "#include <userdefined>"));
                add(new QuestionModule("Which of the following is used for comments in C++?", "D", "/* comment */", " // comment */","// comment", "both // comment or /* comment */"));
                add(new QuestionModule("Which of the following extension is used for user-defined header file in c++?", "C", "hg", "cpp", "h" , "hf"));
                add(new QuestionModule("Which of the following is a correct identifier in C++?", "A", "VAR_1234", "$var_name", "7VARNAME", "7var_name"));
                add(new QuestionModule("Which of the following is not a type of Constructor in C++?", "D", "Default constructor", "Parameterized constructor", "Copy constructor", "Friend constructor"));
                add(new QuestionModule("Which of the following approach is used by C++?", "C", "Left-right", "Right-left", "Bottom-up", "Top-down"));
                add(new QuestionModule(" Which of the following type is provided by C++ but not C?", "D", "double", "float", "int", "bool"));
                add(new QuestionModule("By default, all the files in C++ are opened in _________ mode.", "C", "Binary", "VTC", "Text", "ISCII"));
            }
        };
        QuestionModule.createQuestionsForSubject("C++", R.drawable.cplus, questions);







        //------------- Subject 3
        questions = new ArrayList(){
            {
                add(new QuestionModule("Who developed Python Programming Language?", "C", "Wick van Rossum", "Rasmus Lerdorf", "Guido van Rossum", "Niene Stom"));
                add(new QuestionModule("Which type of Programming does Python support?", "D", "object-oriented programming", "structured programming","functional programming", "all of the mentioned"));
                add(new QuestionModule("Is Python case sensitive when dealing with identifiers?", "B", "no", "yes","machine dependent", "none of the mentioned"));
                add(new QuestionModule("Which of the following is the correct extension of the Python file?", "C", ".python", ".pl",".py", " .p"));
                add(new QuestionModule("Is Python code compiled or interpreted?", "A", " Python code is both compiled and interpreted", "Python code is neither compiled nor interpreted", "Python code is only compiled" , "Python code is only interpreted"));
                add(new QuestionModule("All keywords in Python are in _________", "D", "Capitalized", "lower case", "UPPER CASE", "None of the mentioned"));
                add(new QuestionModule("Which of the following is used to define a block of code in Python language?", "A", "Indentation", "Key", "Brackets", "All of the mentioned"));
                add(new QuestionModule("Which keyword is used for function in Python language?", "B", "Function", "def", "Fun", "Define"));
                add(new QuestionModule("Which of the following character is used to give single-line comments in Python?", "B", "//", "#", "!", "/*"));
                add(new QuestionModule("Python supports the creation of anonymous functions at runtime, using a construct called __________", "C", "pi", "anonymous", "lambda", "none of the mentioned"));
            }
        };
        QuestionModule.createQuestionsForSubject("Python", R.drawable.py2, questions);



        //------------- Subject 4
        questions = new ArrayList(){
            {
                add(new QuestionModule("Who invented Java Programming?", "B", "Guido van Rossum", "James Gosling", "Dennis Ritchie", "Bjarne Stroustrup"));
                add(new QuestionModule("Which statement is true about Java?", "D", "Java is a sequence-dependent programming language", "Java is a code dependent programming language","Java is a platform-dependent programming language", "Java is a platform-independent programming language"));
                add(new QuestionModule("Which component is used to compile, debug and execute the java programs?", "C", "JRE", "JIT","JDK", "JVM"));
                add(new QuestionModule("Which one of the following is not a Java feature?", "B", "Object-oriented", "Use of pointers","Portable", "Dynamic and Extensible"));
                add(new QuestionModule("Which of these cannot be used for a variable name in Java?", "C", "identifier & keyword", "identifier", "keyword" , "none of the mentioned"));
                add(new QuestionModule("What is the extension of java code files?", "D", ".js", ".txt", ".class", ".java"));
                add(new QuestionModule("Which environment variable is used to set the java path?", "D", " MAVEN_Path", "JavaPATH", "JAVA", "JAVA_HOME"));
                add(new QuestionModule("Which of the following is not an OOPS concept in Java?", "C", "Polymorphism", "Inheritance", "Compilation", "Encapsulation"));
                add(new QuestionModule(" Which of the following is a type of polymorphism in Java Programming?", "B", "Multiple polymorphism", "Compile time polymorphism", "Multilevel polymorphism", "Execution time polymorphism"));
                add(new QuestionModule("What is Truncation in Java?", "B", "Floating-point value assigned to a Floating type", "Floating-point value assigned to an integer type", "Integer value assigned to floating type", "Integer value assigned to floating type"));
            }
        };
        QuestionModule.createQuestionsForSubject("Java", R.drawable.javap, questions);






        //------------- Subject 5
        questions = new ArrayList(){
            {
                add(new QuestionModule("Which of the following keyword is used for including the namespaces in the program in C#?", "B", "imports", " using", "exports", "None of the above."));
                add(new QuestionModule("Which of the following converts a type to a double type in C#?", "B", "ToDecimal", "ToDouble","ToInt16", "ToInt32"));
                add(new QuestionModule("Which of the following operator returns the address of an variable in C#?", "C", " sizeof", "typeof"," &", "*"));
                add(new QuestionModule("Which of the following access specifier in C# allows a class to expose its member variables and member functions to other functions and objects?", "A", "Public", "Private","Protected", "Internal"));
                add(new QuestionModule("Which of the following is true about C# structures vs C# classes?", "D", "Classes are reference types and structs are value types.", "Structures do not support inheritance.", "Structures cannot have default constructor" , "All of the above."));
                add(new QuestionModule("Which of the following preprocessor directive allows generating an error from a specific location in your code in C#?", "C", "The System.ApplicationException class supports exceptions generated by application programs.", "Exceptions defined by the programmers should derive from this class.", "Both of the above.", "None of the above"));

            }
        };
        QuestionModule.createQuestionsForSubject("C#", R.drawable.cshrp, questions);



        //------------- Subject 6
        questions = new ArrayList(){
            {
                add(new QuestionModule("Which of the following is a derived type in Go?", "D", "Pointer types", " Array types", "Structure types", " All of the above."));
                add(new QuestionModule("Which of the following function returns the capacity of slice as how many elements it can be accomodate?", "C", "size()", "len()","cap()", "None of the above."));
                add(new QuestionModule("What command is used to run a Go program? ", "A", "go run ", "go start"," go exec", "go boot "));
                add(new QuestionModule("How can you format the Go source code in an idiomatic way? ", "A", " Using the gofmt tool ", "Using the golint tool","Using the gocode tool", "Using the goformat tool "));

            }
        };
        QuestionModule.createQuestionsForSubject("Go", R.drawable.gop, questions);













    }




//====================================================================
// ====================================================================
//====================================================================
// ====================================================================

}
