package model;


import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Quizzes")
public class QuizListWrapper {
    private List<Quiz> quizzes;

    @XmlElement(name = "Quiz")
    public List<Quiz> getQuizzes(){
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes){
        this.quizzes = quizzes;
    }
}