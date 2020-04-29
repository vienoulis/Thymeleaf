package ru.vienoulis.crud_thymeleaf.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.sql.DataSource;
//@ComponentScan("crud_thymeleaf")

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class CrudConfig  {
    @Autowired
    private Environment env;


//    @PersistenceContext(type = PersistenceContextType.EXTENDED)
//    private EntityManager entityManager;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }
//
//    @Bean
//    public LocalSessionFactoryBean getSessionFactory() {
//        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
//        factoryBean.setDataSource(getDataSource());
//
//        Properties props = new Properties();
//        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//
//        factoryBean.setHibernateProperties(props);
//        factoryBean.setAnnotatedClasses(User.class, Role.class);
//        return factoryBean;
//    }
//
//    @Bean
//    public HibernateTransactionManager getTransactionManager() {
//        return new HibernateTransactionManager();
//    }

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        resolver.setCache(false);
//        resolver.setViewClass(TemplateMode.class);

        return resolver;
    }


//    @Bean
//    public SpringResourceTemplateResolver templateResolver(){
//        // SpringResourceTemplateResolver automatically integrates with Spring's own
//        // resource resolution infrastructure, which is highly recommended.
//        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//        templateResolver.setApplicationContext(this.applicationContext);
//        templateResolver.setPrefix("/templates/");
//        templateResolver.setSuffix(".html");
//        // HTML is the default value, added here for the sake of clarity.
//        templateResolver.setTemplateMode(TemplateMode.HTML);
//        // Template cache is true by default. Set to false if you want
//        // templates to be automatically updated when modified.
//        templateResolver.setCacheable(false);
//        return templateResolver;
//    }
//
//    @Bean
//    public SpringTemplateEngine templateEngine(){
//        // SpringTemplateEngine automatically applies SpringStandardDialect and
//        // enables Spring's own MessageSource message resolution mechanisms.
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//        // Enabling the SpringEL compiler with Spring 4.2.4 or newer can
//        // speed up execution in most scenarios, but might be incompatible
//        // with specific cases when expressions in one template are reused
//        // across different data types, so this flag is "false" by default
//        // for safer backwards compatibility.
//        templateEngine.setEnableSpringELCompiler(true);
//        return templateEngine;
//    }
}

