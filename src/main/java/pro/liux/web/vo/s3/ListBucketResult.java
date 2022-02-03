package pro.liux.web.vo.s3;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;


@Data
public class ListBucketResult {
    @JacksonXmlProperty(localName = "Name")
    private String Name;

    @JacksonXmlProperty(localName = "Delimiter")
    private String Delimiter;

    @JacksonXmlProperty(localName = "Prefix")
    private String Prefix;

    @JacksonXmlProperty(localName = "Marker")
    private String Marker;

    @JacksonXmlProperty(localName = "MaxKeys")
    private Integer MaxKeys;

    @JacksonXmlProperty(localName = "IsTruncated")
    private String IsTruncated;

    @JacksonXmlProperty(localName = "Contents")
    @JacksonXmlElementWrapper(useWrapping=false)
    private List<Contents> Contents;

}