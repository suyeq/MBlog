package com.cyc.pojo;

public class BlogDetail {

    private Integer blogid;

    private Integer userid;

    private String publishtime;

    private String content;

    private String username;

    private Integer headpic;

    public void setBlogid(Integer blogid) {
        this.blogid = blogid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHeadpic(Integer headpic) {
        this.headpic = headpic;
    }

    public Integer getBlogid() {
        return blogid;
    }

    public Integer getUserid() {
        return userid;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public String getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }

    public Integer getHeadpic() {
        return headpic;
    }
}
