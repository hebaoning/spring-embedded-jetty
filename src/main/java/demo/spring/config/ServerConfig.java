package demo.spring.config;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ServerConfig implements EnvironmentAware {

    private static final Server server = new Server();

    @Value("${http.port:8000}")
    private Integer port;

    public static Server getServer() {
        return server;
    }

    @Override
    public void setEnvironment(@Nullable Environment env) {
        ServerConnector http = new ServerConnector(server);
        http.setPort(port);
        server.addConnector(http);
    }
}
