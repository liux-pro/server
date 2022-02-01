package pro.liux.web.config;

import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 七牛的s3返回的xml  content-type被设置成text/plain，造成无法解析
 */
@Configuration
public class XMLMessageConverter extends MappingJackson2XmlHttpMessageConverter {
    public XMLMessageConverter(){
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        setSupportedMediaTypes(mediaTypes);
    }

    @Bean
    public Decoder feignDecoder(){
        XMLMessageConverter xmlMessageConverter = new XMLMessageConverter();
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(xmlMessageConverter);
        return new SpringDecoder(objectFactory);
    }
}