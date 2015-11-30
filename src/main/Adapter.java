package main;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter extends XmlAdapter<String,Integer> {
    @Override
    public Integer unmarshal(String v) throws Exception {
        return Integer.valueOf(v);
    }

    @Override
    public String marshal(Integer v) throws Exception {
        return v.toString();
    }
}
