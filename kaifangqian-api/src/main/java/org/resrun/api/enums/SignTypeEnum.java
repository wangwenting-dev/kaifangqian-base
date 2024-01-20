package org.resrun.api.enums;

/**
 * @Description: 签署类型 1:关键字签署；2:指定位置签署
 * @Package: org.resrun.enums
 * @ClassName: SignTypeEnum
 * @author: FengLai_Gong
 */
public enum SignTypeEnum {


    KEYWORDS(1,"关键字签署"),
    POSITION(2,"指定位置签署"),


    ;

    private Integer code  ;

    private String name ;

    SignTypeEnum(Integer code, String name){
        this.code = code ;
        this.name = name;
    }


    public Integer getCode(){
        return this.code;
    }

    public String getName(){
        return this.name ;
    }


}