package com.adarsh.spring.service;

import java.util.List;
import java.util.Map;


public interface DatabaseDaoService {

    public List<Map<String, Object>> getDatabaseTables() throws Exception;
}
