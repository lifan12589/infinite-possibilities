package com.infinitePossibilities.sentinel;

import com.alibaba.fastjson.JSONObject;
import java.util.List;

public interface UserService {

    public List<User> getList();

    public List<User> queryAllAnnotation();

    public List<User> queryAllFallback();

    public List<User> queryAllDashboard();

    public List<User> NewTest();

    public JSONObject NewJson();
}
