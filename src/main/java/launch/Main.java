package launch;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.deploy.LoginConfig;
import org.apache.catalina.deploy.SecurityCollection;
import org.apache.catalina.deploy.SecurityConstraint;
import org.apache.catalina.realm.MemoryRealm;
import org.apache.catalina.startup.Tomcat;

public class Main {
	private static final String AUTH_ROLE = "admin";

	public static void main(String[] args) throws Exception {

		String webappDirLocation = "WebContent/";
		Tomcat tomcat = new Tomcat();

		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			webPort = "5100";
		}

		tomcat.setPort(Integer.valueOf(webPort));

		tomcat.addWebapp("/", (new File(webappDirLocation)).getAbsolutePath());
		System.out.println("configuring app with basedir: "
				+ new File("./" + webappDirLocation).getAbsolutePath());

		Context rootContext = tomcat.addContext("",
				(new File(".")).getAbsolutePath());
		Tomcat.initWebappDefaults(rootContext);

		
		/*LoginConfig config = new LoginConfig();
        config.setAuthMethod("BASIC");
        rootContext.setLoginConfig(config);
        rootContext.addSecurityRole(AUTH_ROLE);
        SecurityConstraint constraint = new SecurityConstraint();
        constraint.addAuthRole(AUTH_ROLE);
        SecurityCollection collection = new SecurityCollection();
        collection.addPattern("/*");
        constraint.addCollection(collection);
        rootContext.addConstraint(constraint);

        String path = "tomcat-users.xml";
        MemoryRealm realm = new MemoryRealm();
        realm.setPathname(path);
        tomcat.getEngine().setRealm(realm);*/

        
		tomcat.start();
		tomcat.getServer().await();
	}
}
