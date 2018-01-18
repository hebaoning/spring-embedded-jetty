package demo.spring;

import demo.spring.config.WebInitializer;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.annotations.AnnotationConfiguration.ClassInheritanceMap;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.eclipse.jetty.annotations.AnnotationConfiguration.CLASS_INHERITANCE_MAP;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) {
        logger.debug("main");

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setConfigurations(
                new Configuration[] {
                        new AnnotationConfiguration(),
                });
        webAppContext.setResourceBase("src/main/webapp");

        Set<String> hashSet = ConcurrentHashMap.newKeySet();
        hashSet.add(WebInitializer.class.getName());

        ClassInheritanceMap classInheritanceMap = new ClassInheritanceMap();
        classInheritanceMap.put(WebApplicationInitializer.class.getName(), hashSet);

        webAppContext.setAttribute(CLASS_INHERITANCE_MAP, classInheritanceMap);

        Server server = new Server(8010);
        server.setHandler(webAppContext);

        try {
            server.start();
            server.dump(System.err);
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
