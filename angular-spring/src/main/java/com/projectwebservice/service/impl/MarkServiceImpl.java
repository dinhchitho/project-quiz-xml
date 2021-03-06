package com.projectwebservice.service.impl;

import com.projectwebservice.markxml.ListUser;
import com.projectwebservice.markxml.MarkQuiz;
import com.projectwebservice.markxml.QuizMark;
import com.projectwebservice.markxml.UserMark;
import com.projectwebservice.model.User;
import com.projectwebservice.model.exam.Mark;
import com.projectwebservice.model.exam.Quiz;
import com.projectwebservice.repo.MarkRepository;
import com.projectwebservice.repo.QuestionRepository;
import com.projectwebservice.repo.QuizRepository;
import com.projectwebservice.repo.UserRepository;
import com.projectwebservice.respone.MarkResponse;
import com.projectwebservice.respone.QuizRespone;
import com.projectwebservice.respone.UserResponse;
import com.projectwebservice.service.MarkService;
import com.projectwebservice.service.QuestionService;
import com.projectwebservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarkServiceImpl implements MarkService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MarkRepository markRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mark addMark(Mark mark) {
        return markRepository.save(mark);
    }

    @Override
    public ListUser getAllMark() {
        List<User> userList = userRepository.findAll();
        ListUser listUserXmlRoot = new ListUser();
        List<UserMark> userMarksXml = new ArrayList<>();;
        List<QuizMark> quizMarksXml = null;
        for (User user: userList
             ) {
            List<Mark> isMark = markRepository.findMarkByUserId(user.getId());
            if (isMark != null && isMark.size() > 0) {
                UserMark userMarkXml = new UserMark();
                userMarkXml.setUsername(user.getUsername());
                userMarkXml.setPhone(user.getPhone());
                userMarkXml.setEmail(user.getEmail());
                List<Quiz> quizListGetDb = quizRepository.findAllQuizByUser(user.getId());
                quizMarksXml = new ArrayList<>();
                for (Quiz quiz: quizListGetDb
                ) {
                    List<Mark> mark = markRepository.findMarkByQuizId(quiz.getQId(), user.getId());
                    if (mark != null) {
                        for (Mark markGetDB: mark
                             ) {
                            QuizMark quizMarkXml = new QuizMark();
                            quizMarkXml.setTitle(quiz.getTitle());
                            quizMarkXml.setDescription(quiz.getDescription());
                            MarkQuiz markQuiz = new MarkQuiz();
                            markQuiz.setMarksGot(markGetDB.getMarksGot());
                            markQuiz.setCorrectAnswers(markGetDB.getCorrectAnswers());
                            markQuiz.setAttempted(markGetDB.getAttempted());
                            quizMarkXml.setMarkQuiz(markQuiz);
                            quizMarksXml.add(quizMarkXml);
                        }
                    }
                }
                userMarkXml.setQuizMarkList(quizMarksXml);
                userMarksXml.add(userMarkXml);
                listUserXmlRoot.setUserMarkList(userMarksXml);
            }
        }
        return listUserXmlRoot;
    }

    @Override
    public ListUser marshalling(ListUser listUser) {
        try {
            //Duy???t ??i???u ki???n = pubDate
            List<UserMark> userMarkList = new ArrayList<>();
            for (UserMark e : listUser.getUserMarkList()) {
                userMarkList.add(e);
            }

            //Define path l??u
            JAXBContext context = JAXBContext.newInstance(ListUser.class);
            Marshaller marshaller = context.createMarshaller();

            //Config import
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            //L??u th??nh xml
            listUser.setUserMarkList(userMarkList);
            marshaller.marshal(listUser, new FileOutputStream("File/mark-export.xml"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listUser;
    }

    @Override
    public List<UserResponse> getAllMarkUser() {
        List<User> userListGetDB = userRepository.findAll();
        List<UserResponse> listUserXmlRoot = new ArrayList<>();
        List<MarkResponse> markResponseList = new ArrayList<>();;
        List<QuizRespone> quizResponeList = null;
        for (User user: userListGetDB
             ) {
            List<Mark> isMark = markRepository.findMarkByUserId(user.getId());
            if (isMark != null && isMark.size() > 0) {
                UserResponse userResponse = new UserResponse();
                userResponse.setUsername(user.getUsername());
                userResponse.setEmail(user.getEmail());
                userResponse.setPhone(user.getPhone());
                List<Quiz> quizListGetDb = quizRepository.findAllQuizByUser(user.getId());
                quizResponeList = new ArrayList<>();
                for (Quiz quiz: quizListGetDb
                     ) {
                    List<Mark> getListMarkDB = markRepository.findMarkByQuizId(quiz.getQId(), user.getId());
                    if (getListMarkDB != null && getListMarkDB.size() > 0) {
                        for (Mark mark: getListMarkDB
                             ) {
                            QuizRespone quizRespone = new QuizRespone();
                            quizRespone.setTitle(quiz.getTitle());
                            quizRespone.setDescription(quiz.getDescription());
                            MarkResponse markResponse = new MarkResponse();
                            markResponse.setMarksGot(mark.getMarksGot());
                            markResponse.setCorrectAnswers(mark.getCorrectAnswers());
                            markResponse.setAttempted(mark.getAttempted());
                            quizRespone.setMarkResponse(markResponse);
                            quizResponeList.add(quizRespone);
                        }
                    }
                }
                userResponse.setQuizRespones(quizResponeList);
                listUserXmlRoot.add(userResponse);
            }
        }
        return listUserXmlRoot;
    }
}
