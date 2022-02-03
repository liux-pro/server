/**
  * Copyright 2022 bejson.com 
  */
package pro.liux.web.vo.s3;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;


@Data
public class Owner {
    @JacksonXmlProperty(localName = "ID")
    private String ID;

    @JacksonXmlProperty(localName = "DisplayName")
    private String DisplayName;

}