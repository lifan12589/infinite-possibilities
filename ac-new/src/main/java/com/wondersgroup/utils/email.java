//package com.wondersgroup.utils;
//
//import java.util.Date;
//import java.util.Properties;
//
//import javax.mail.Message.RecipientType;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//
//
//public class email {//https://www.cnblogs.com/YJK923/p/10089758.html
//
//		public static final String SMTPSERVER = "smtp.126.com";
//	    public static final String SMTPPORT = "465";
//	    public static final String ACCOUT = "lifan@126.com";
//	    // 该密码不是登陆密码，而是授权密码
//	    public static final String PWD = "lifan";
//
//
//	    public void testSendEmail() throws Exception {
//
//	        Properties props = getMailProperties();
//
//	        // 根据邮件配置创建会话，注意session别导错包
//	        Session session = Session.getDefaultInstance(props);
//	        // 开启debug模式，可以看到更多详细的输入日志
//	        session.setDebug(true);
//	        //创建邮件
//	        MimeMessage message = createEmail(session);
//	        //获取传输通道
//	        Transport transport = session.getTransport();
//	        // 连接传输通道
//	        transport.connect(SMTPSERVER,ACCOUT, PWD);
//	        //发送邮件
//	        transport.sendMessage(message, message.getAllRecipients());
//	        transport.close();
//
//	    }
//
//
//	    /**
//	     * 设置邮件相关配置
//	     */
//	    private Properties getMailProperties() {
//	        // 创建邮件配置
//	        Properties props = new Properties();
//	        // 使用的协议（JavaMail规范要求）
//	        props.setProperty("mail.transport.protocol", "smtp");
//	        // 发件人的邮箱的 SMTP 服务器地址
//	        props.setProperty("mail.smtp.host", SMTPSERVER);
//	        // SMTP 服务器端口号，默认的端口号为 25 ，因为使用 SSL 连接方式，所以端口号为 465
//	        // 为什么使用 SSL，传输加密，更安全
//	        props.setProperty("mail.smtp.port", SMTPPORT);
//	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//	        props.setProperty("mail.smtp.ssl.enable", "true");
//	        // 设置需要请求认证
//	        props.setProperty("mail.smtp.auth", "true");
//	        return props;
//	    }
//
//
//	    /**
//	     * 创建邮件
//	     */
//	    public MimeMessage createEmail(Session session) throws Exception {
//	        // 根据会话创建邮件
//	        MimeMessage msg = new MimeMessage(session);
//	        // address 邮件地址, personal 邮件昵称, charset 编码方式
//	        InternetAddress fromAddress = new InternetAddress(ACCOUT,"Dear", "utf-8");
//	        // 设置发送邮件方
//	        msg.setFrom(fromAddress);
//	        InternetAddress receiveAddress = new InternetAddress("yu@qq.com", "test", "utf-8");
//	        // 设置邮件接收方
//	        msg.setRecipient(RecipientType.TO, receiveAddress);
//	        // 设置邮件标题
//	        msg.setSubject("测试标题", "utf-8");
//	        msg.setText("啥都不说，点赞吧！");
//	        // 设置显示的发件时间
//	        msg.setSentDate(new Date());
//	        // 保存设置
//	        msg.saveChanges();
//	        return msg;
//	    }
//
//}
