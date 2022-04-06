package pro.liux.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.nativex.hint.TypeHint;


/**
 * 把用到的实体类写到types里
 * 否则spring-native会报错
 */
@Configuration
@TypeHint(types = {})
public class SpringNativeBeanConfiguration {
}
