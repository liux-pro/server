package pro.liux.web.config;

import feign.codec.Decoder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.http.MediaType;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.nativex.hint.JdkProxyHint;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import pro.liux.web.client.DateTestClient;
import pro.liux.web.client.S3Client;
import pro.liux.web.config.property.S3Property;
import pro.liux.web.utils.AWSSignatureVersion4;

import java.util.ArrayList;
import java.util.List;

@Configuration
@NativeHint(trigger = SpringNativeFeignConfiguration.class,
        types = {
                @TypeHint(types = S3Client.class),
                @TypeHint(types = DateTestClient.class),
                @TypeHint(types = FeignClient.class),
                @TypeHint(typeNames = "org.springframework.cloud.openfeign.FeignClientSpecification"),
                @TypeHint(types = FeignClientsConfiguration.class)
        },
        jdkProxies = {
                @JdkProxyHint(types = S3Client.class),
                @JdkProxyHint(types = DateTestClient.class),
                @JdkProxyHint(types = {PathVariable.class, SynthesizedAnnotation.class}),
                @JdkProxyHint(types = {RequestHeader.class, SynthesizedAnnotation.class}),
                @JdkProxyHint(types = {RequestBody.class, SynthesizedAnnotation.class}),
                @JdkProxyHint(types = {RequestParam.class, SynthesizedAnnotation.class})
        }
)
@EnableFeignClients(clients = {S3Client.class, DateTestClient.class})
@TypeHint(types = AWSSignatureVersion4.class)
@Setter
@Getter
public class SpringNativeFeignConfiguration {
    public static String region;
    public static String service;
    public static String accessKey;
    public static String secretKey;
    public static String endpoint;

    public SpringNativeFeignConfiguration(
            @Autowired S3Property s3Property
    ) {
        SpringNativeFeignConfiguration.region = s3Property.getRegion();
        SpringNativeFeignConfiguration.service = s3Property.getService();
        SpringNativeFeignConfiguration.accessKey = s3Property.getAccessKey();
        SpringNativeFeignConfiguration.secretKey = s3Property.getSecretKey();
        SpringNativeFeignConfiguration.endpoint = s3Property.getEndpoint();
    }

    /**
     * 七牛的s3返回的xml  content-type被设置成text/plain，造成无法解析
     * 扩展xml解析器，使其支持text/plain
     *
     * @return Decoder
     */
    @Bean
    public Decoder feignDecoder() {
        MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter = new MappingJackson2XmlHttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        mappingJackson2XmlHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(mappingJackson2XmlHttpMessageConverter);
        return new SpringDecoder(objectFactory);
    }

}