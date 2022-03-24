package com.projectwebservice.service;

import com.projectwebservice.markxml.ListUser;
import com.projectwebservice.model.exam.Mark;

import java.util.List;

public interface MarkService {
    Mark addMark(Mark mark);
    ListUser getAllMark();
    ListUser marshalling(ListUser listUser);
}
