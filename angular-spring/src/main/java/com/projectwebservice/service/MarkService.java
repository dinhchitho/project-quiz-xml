package com.projectwebservice.service;

import com.projectwebservice.markxml.ListUser;
import com.projectwebservice.model.User;
import com.projectwebservice.model.exam.Mark;
import com.projectwebservice.respone.UserResponse;

import java.util.List;

public interface MarkService {
    Mark addMark(Mark mark);
    ListUser getAllMark();
    ListUser marshalling(ListUser listUser);
    List<UserResponse> getAllMarkUser();
}
