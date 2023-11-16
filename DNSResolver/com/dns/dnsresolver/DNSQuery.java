package com.dns.dnsresolver;

public class DNSQuery {
    private String domainName;
    private String type;
    private String className;
    private void createDNSQuestion(String s) {
        String[] querySplit = s.split(":");


        this.setDomainName(querySplit[0].trim());
        String[] questions = querySplit[1].split(",");
        this.setType(questions[0].trim().split(" ")[1].trim());
        this.setClassName(questions[1].trim().split(" ")[1].trim());

    }
    public DNSQuery(String s){
        createDNSQuestion(s);
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String toString(){
        return getDomainName()+": "+"type "+getType()+" , "+"class "+getClassName();
    }
}
