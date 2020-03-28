package jp.promari.curation.crawler.common;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class ResultBean {

    private String code;
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
