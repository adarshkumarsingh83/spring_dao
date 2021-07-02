package com.adarsh.spring.main;

import com.adarsh.spring.service.DatabaseDaoServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class ApplicationMain {

    private DatabaseDaoServiceImpl databaseDaoService;

    public DatabaseDaoServiceImpl getDatabaseDaoService() {
        return databaseDaoService;
    }

    public void setDatabaseDaoService(DatabaseDaoServiceImpl databaseDaoService) {
        this.databaseDaoService = databaseDaoService;
    }

    public static void main(String args[]) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:/configuration/applicationContext.xml");
        ApplicationMain applicationMain= (ApplicationMain) applicationContext.getBean("applicationMain");
        DatabaseDaoServiceImpl databaseDaoService =applicationMain.getDatabaseDaoService();
        List<Map<String, Object>> list = databaseDaoService.getDatabaseTables();
        for (Object object : list) {
            Map<String, Object> map = (Map) object;
            for (Map.Entry entry : map.entrySet()) {
                System.out.println(entry.getKey() + "  " + entry.getValue());
            }
        }
    }

}
