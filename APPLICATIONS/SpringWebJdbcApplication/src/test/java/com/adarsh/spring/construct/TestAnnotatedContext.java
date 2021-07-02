package com.adarsh.spring.construct;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;


/**
 * @author $CreatedBy Adarsh_K
 * @author $LastChangedBy: Adarsh_K
 * @version $Revision: 1.0 $, $Date:: 22/5/13 1:40 PM
 * @see
 */
@Lazy(value = true)
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({TransactionalTestExecutionListener.class
        , DirtiesContextTestExecutionListener.class
        , DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = "classpath*:/configuration/applicationContext.xml")
public abstract class TestAnnotatedContext implements ApplicationContextAware {


    protected Logger logger = null;
    protected ApplicationContext applicationContext;

    protected TestAnnotatedContext(Class clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}