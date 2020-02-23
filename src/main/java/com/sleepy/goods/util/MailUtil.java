package com.sleepy.goods.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
import javax.mail.search.SubjectTerm;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 邮箱工具类
 *
 * @author gehoubao
 * @create 2020-02-22 19:23
 **/
public class MailUtil {
    private static final String PS_FILE_PATH = "/goods_mail_file";

    /**
     * @param args
     */
    public static void main(String[] args) {
        new MailUtil();
    }

    private Store createConnection() throws Exception {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        URLName urlname = new URLName("pop3", "pop.qq.com", 110, null, "1822598516@qq.com", "utldgieepfluebfh");
        Store store = session.getStore(urlname);
        return store;
    }

    public Map<String, Object> getGoodsInfoInMail() throws Exception {
        Map<String, Object> goodsInfo = new HashMap<>();
        Store store = createConnection();
        store.connect();
        Folder folder = store.getDefaultFolder();
        if (folder == null) {
            goodsInfo.put("message", "服务器不可用");
            return goodsInfo;
        }
        Folder popFolder = folder.getFolder("INBOX");
        popFolder.open(Folder.READ_ONLY);
        Message[] messages = popFolder.getMessages();
        int msgCount = popFolder.getMessageCount();
        Message[] goodsMails = popFolder.search(new SubjectTerm("商品列表"));
        if (goodsMails.length > 1) {

            mailReceiver(goodsMails[0]);
        }
        goodsInfo.put("message", "未找到商品数据邮件");
        popFolder.close(true);
        store.close();
        return goodsInfo;
    }

    /**
     * 解析邮件
     *
     * @param msg
     * @throws Exception
     */
    private void mailReceiver(Message msg) throws Exception {
        Address[] froms = msg.getFrom();
        if (froms != null) {
            InternetAddress addr = (InternetAddress) froms[0];
            System.out.println("发件人地址:" + addr.getAddress());
            System.out.println("发件人显示名:" + addr.getPersonal());
        }
        System.out.println("邮件主题:" + msg.getSubject());
        Object o = msg.getContent();
        if (o instanceof Multipart) {
            Multipart multipart = (Multipart) o;
            reMultipart(multipart);
        } else if (o instanceof Part) {
            Part part = (Part) o;
            rePart(part);
        } else {
            System.out.println("类型" + msg.getContentType());
            System.out.println("内容" + msg.getContent());
        }
    }

    /**
     * @param part 解析内容
     * @throws Exception
     */
    private void rePart(Part part) throws Exception {
        if (part.getDisposition() != null) {
            String fileName = MimeUtility.decodeText(PS_FILE_PATH + part.getFileName()); //MimeUtility.decodeText解决附件名乱码问题
            System.out.println("发现附件: " + MimeUtility.decodeText(part.getFileName()));
            System.out.println("内容类型: " + MimeUtility.decodeText(part.getContentType()));
            System.out.println("附件内容:" + part.getContent());
            InputStream in = part.getInputStream();
            java.io.FileOutputStream out = new FileOutputStream(fileName);
            int data;
            while ((data = in.read()) != -1) {
                out.write(data);
            }
            in.close();
            out.close();
        }
    }

    private void reMultipart(Multipart multipart) throws Exception {
        for (int j = 0, n = multipart.getCount(); j < n; j++) {
            //解包, 取出 MultiPart的各个部分, 每部分可能是邮件内容,
            Part part = multipart.getBodyPart(j);
            // 也可能是另一个小包裹(MultiPart)
            // 判断此包裹内容是不是一个小包裹, 一般这一部分是 正文 Content-Type: multipart/alternative
            if (part.getContent() instanceof Multipart) {
                Multipart p = (Multipart) part.getContent();// 转成小包裹
                //递归迭代
                reMultipart(p);
            } else {
                rePart(part);
            }
        }
    }
}