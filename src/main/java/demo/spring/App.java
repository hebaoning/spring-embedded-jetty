package demo.spring;

import demo.spring.config.ServerConfig;
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
    private static final App app = new App();
    private static final Server server = ServerConfig.getServer();

    public static App getInstance() {
        return app;
    }

    public static void main(String[] args) throws Exception {
        logger.info("start application");

        // setup handler
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

        try {
            app.init(webAppContext);
            app.start();
        } catch (Exception e) {
            logger.error("failed to start application", e);
            Runtime.getRuntime().exit(-1);
        }
        app.join();
    }

    private App() { }

    private void init(WebAppContext webAppContext) {
        server.setHandler(webAppContext);
        server.setStopAtShutdown(true);
    }

    private void start() throws Exception {
        server.start();
        logger.info(server.dump());
    }

    private void join() throws Exception {
        server.join();
    }

}
