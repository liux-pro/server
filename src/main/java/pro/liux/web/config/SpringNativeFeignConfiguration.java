package pro.liux.web.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.nativex.hint.JdkProxyHint;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import pro.liux.web.client.DateTestClient;

@Configuration
@NativeHint(trigger = SpringNativeFeignConfiguration.class,
        types = {
                @TypeHint(types = DateTestClient.class),
                @TypeHint(types = FeignClient.class),
                @TypeHint(typeNames = "org.springframework.cloud.openfeign.FeignClientSpecification"),
                @TypeHint(types = FeignClientsConfiguration.class)
        },
        jdkProxies = {
                @JdkProxyHint(types = DateTestClient.class),
                @JdkProxyHint(types = {PathVariable.class, SynthesizedAnnotation.class}),
                @JdkProxyHint(types = {RequestHeader.class, SynthesizedAnnotation.class}),
                @JdkProxyHint(types = {RequestBody.class, SynthesizedAnnotation.class}),
                @JdkProxyHint(types = {RequestParam.class, SynthesizedAnnotation.class})
        }
)
@EnableFeignClients(clients = {DateTestClient.class})
public class SpringNativeFeignConfiguration {
}