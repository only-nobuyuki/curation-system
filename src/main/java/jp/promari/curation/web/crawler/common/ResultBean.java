package jp.promari.curation.web.crawler.common;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;


public class ResultBean {

    @Setter
    private String code;
    @Setter
    private String msg;
    private Map<String,Object> body = new LinkedHashMap<>();

    public ResultBean add(String key, Object value){
        this.body.put(key,value);
        return this;
    }

    public static ResultBean success(){
        ResultBean re = new ResultBean();
        re.setCode("1");
        re.setMsg("success");
        return re;
    }

    public static ResultBean fail(){
        ResultBean re = new ResultBean();
        re.setCode("-1");
        re.setMsg("fail");
        return re;
    }

}
