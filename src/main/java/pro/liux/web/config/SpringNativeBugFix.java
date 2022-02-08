package pro.liux.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.hint.TypeHint;

@NativeHint(trigger = org.HdrHistogram.Histogram.class, types =
@TypeHint(
        types = {
                org.HdrHistogram.Histogram.class,
                org.HdrHistogram.ConcurrentHistogram.class
        })
)
@Configuration
public class SpringNativeBugFix {
    // （0.11.2）这个版本的spring-native有bug，官方将在在下个版本修复（0.11.3）
    //  https://github.com/spring-projects-experimental/spring-native/issues/1484
}
