package pro.liux.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.nativex.hint.TypeHint;
import pro.liux.web.vo.VditorImage;
import pro.liux.web.vo.VditorImageConvert;


@Configuration
@TypeHint(types = {VditorImage.class, VditorImageConvert.class})
public class SpringNativeBeanConfiguration {
}
