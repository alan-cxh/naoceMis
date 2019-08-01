package edu.sjtu.naocemis;

import edu.sjtu.infoplus.applicationToolkit.SubscriberServlet;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations= {"classpath:application-messengers.xml"})
public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * 流程servlet配置
	 * @return
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean (new SubscriberServlet(), "/infoplus_subscriber");
	}

	/**
	 * 配置listener
	 * @param application
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(NaocemisApplication.class);
	}

}
