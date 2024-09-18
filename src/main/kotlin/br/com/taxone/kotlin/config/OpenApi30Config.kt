package br.com.lkm.taxone.mapper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
class OpenApi30Config {

  val moduleName = "taxone-mapper-api";
  val apiVersion = "/v1";

//  public OpenApi30Config(
//      @Value("${module-name}") String moduleName,
//      @Value("${api-version}") String apiVersion) {
//    this.moduleName = moduleName;
//    this.apiVersion = apiVersion;
//    System.out.println("moduleName:" + moduleName + " - apiVersion:" + apiVersion);
//  }

  @Bean
  fun customOpenAPI(): OpenAPI {
    val securitySchemeName = "bearerAuth";
    val apiTitle = String.format("%s API", StringUtils.capitalize(moduleName));
    return OpenAPI()
        .addSecurityItem(SecurityRequirement().addList(securitySchemeName))
        .components(
            Components()
                .addSecuritySchemes(securitySchemeName,
                    SecurityScheme()
                        .name(securitySchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
        )
        .info(Info().title(apiTitle).version(apiVersion));
  }
}