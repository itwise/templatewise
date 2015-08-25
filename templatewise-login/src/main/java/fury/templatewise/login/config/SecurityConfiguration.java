package fury.templatewise.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import fury.templatewise.login.security.handler.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
// @@Configuration + @EnableWebSecurity 라고 선언하면
// Default Spring boot Security 셋팅을 활성화 하지 않음
// Default 셋팅은 Basic Auth 방식
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	// example of configuring in memory authentication
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                .withUser("user").password("user").roles("USER");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
				.anyRequest().authenticated()
				.and()
			.formLogin()			// login 설정 (로그인에 대한 상세 설정 모두 확인 필요함)
				.loginPage("/login")
				.usernameParameter("username")
				.passwordParameter("password")
                .defaultSuccessUrl("/loginAfter", true)
//                .successHandler(new LoginSuccessHandler())
                .permitAll()
                .and()
            .logout()				// logout 설정 (로그아웃에 대한 상세 설정 모두 확인 필요함)
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            	.invalidateHttpSession(true)
            	.deleteCookies("JSESSIONID")	// JSESSIONID
            	.logoutSuccessUrl("/login")
            	.and();
//            .rememberMe()
//            	.useSecureCookie(true)
//            	.key("auth")
//            	.and();
//            .sessionManagement()	// session 설정 (세션에 대한 상세 설정 모두 확인 필요함)
//            	.sessionAuthenticationStrategy()
//            	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}
