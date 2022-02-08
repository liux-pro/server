/**
 * Copyright 2022 bejson.com
 */
package pro.liux.web.vo.oss;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Contents {
    @JacksonXmlProperty(localName = "Key")
    private String Key;

    @JacksonXmlProperty(localName = "ETag")
    private String ETag;

    @JacksonXmlProperty(localName = "Size")
    private Integer Size;

    @JacksonXmlProperty(localName = "LastModified")
    private Date LastModified;

    @JacksonXmlProperty(localName = "StorageClass")
    private String StorageClass;

    @JacksonXmlProperty(localName = "Owner")
    private Owner Owner;

}