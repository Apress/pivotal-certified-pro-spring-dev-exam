package com.ps.beans.others;

import com.ps.beans.SimpleBean;
import com.ps.beans.SimpleBeanImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by iuliana.cosmina on 3/26/16.
 */
public class SpringFactoryBean implements FactoryBean<SimpleBean> {
    private Logger logger = LoggerFactory.getLogger(SpringFactoryBean.class);

    private SimpleBean simpleBean = new SimpleBeanImpl();

    /**
     * This constructor was created just to print a message in the log and make sure that this bean is being
     * created without being declared anywhere.
     */
    public SpringFactoryBean() {
        logger.info(">> Look ma, no definition!");
    }

    @Override
    public SimpleBean getObject() throws Exception {
        return this.simpleBean;
    }

    @Override
    public Class<?> getObjectType() {
        return SimpleBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
