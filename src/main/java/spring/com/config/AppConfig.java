package spring.com.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import spring.com.model.User;

@Configuration
@ComponentScan (basePackages = {
        "spring.com.dao.impl",
        "spring.com.service.impl"
})
public class AppConfig {
    @Bean
    public DataSource getDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3320/cinema?serverTimezone=UTC");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("root");
        return basicDataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionBean = new LocalSessionFactoryBean();
        sessionBean.setDataSource(getDataSource());

        Properties properties = new Properties();
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "create-drop");

        sessionBean.setHibernateProperties(properties);
        sessionBean.setAnnotatedClasses(User.class);

        return sessionBean;
    }
}
