package github.com.bobgit.study.pinyin.conf;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 用于生成swagger文档
 * 
 * @author bob
 *
 */
@Configuration
//@ConditionalOnProperty(name="spring.profiles.active", havingValue="dev", matchIfMissing=true)
@EnableSwagger2
//@EnableWebMvc
public class SwaggerConfig //implements WebMvcConfigurer 此处用于MVC方面
{
	@Bean
	public Docket petApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				//.apis(RequestHandlerSelectors.basePackage("com.yier.platform.common.model")) // 仅显示 com.yier.platform.common.model 目录下的接口
				.build();
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("亿尔云平台-智慧医疗服务平台 api文档")
				.description("描述信息:restful 风格接口")
				//服务条款网址
				//.termsOfServiceUrl("")
				.version("1.0")
				.contact(new Contact("上海观谷科技有限公司", "https://www.vvhcc.com/yier_site1/index.html", "ccae@ccae.cc"))
				.build();
	}


}
