package pro.liux.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.nativex.hint.TypeHint;
import pro.liux.web.vo.VditorImage;


@Configuration
@TypeHint(types = VditorImage.class)
public class SpringNativeBeanConfiguration {
}
