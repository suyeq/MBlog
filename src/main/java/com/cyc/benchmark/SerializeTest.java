package com.cyc.benchmark;

import com.cyc.pojo.Blog;
import com.cyc.util.SerializeUtil;

import java.io.*;
import java.util.Date;

/**
 * 用于比较各种序列化的时间和空间
 **/
public abstract class SerializeTest
{
    public static void main(String[] args)
    {
        Test1 test1 = new Test1();
        test1.test();
        Test2 test2 = new Test2();
        test2.test();
    }

    public void test()
    {
        Blog blog = new Blog();
        blog.setUserid(1);
        blog.setBlogid(1);
        blog.setPublishtime(new Date());
        blog.setContent("abcdefabcdefabcdefabcdef");

        int times = 1000000;
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            byte[] bytes = templateMethod1(blog);
            if (i == 0) {
                System.out.println("space: " + bytes.length);
            }
            templateMethod2(bytes);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("time: " + (endTime - currentTime) / 1000.0 + "s");
    }

    abstract byte[] templateMethod1(Blog blog);

    abstract void templateMethod2(byte[] s);
}

class Test1 extends SerializeTest
{
    @Override
    byte[] templateMethod1(Blog blog)
    {
        return SerializeUtil.writeBlogObject(blog).getBytes();
    }

    @Override
    void templateMethod2(byte[] s)
    {
        SerializeUtil.readBlogObject(new String(s));
    }
}

class Test2 extends SerializeTest
{
    @Override
    byte[] templateMethod1(Blog blog)
    {
        return writeBlogObject(blog);
    }

    @Override
    void templateMethod2(byte[] s)
    {
        readBlogObject(s);
    }

    private byte[] writeBlogObject(Blog blog)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(blog);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    private Blog readBlogObject(byte[] bytes)
    {
        Blog blog = null;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            blog = (Blog) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return blog;
    }

}
