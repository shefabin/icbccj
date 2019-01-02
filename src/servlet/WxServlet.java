package servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * Servlet implementation class WxServlet
 */
@WebServlet(description = "΢�Ž�����", urlPatterns = { "/wxServlet" })
public class WxServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String signature = request.getParameter("signature");//΢�ż���ǩ����signature����˿�������д��token�����������е�timestamp������nonce������
		String timestamp = request.getParameter("timestamp");//ʱ���
		String nonce = request.getParameter("nonce");//�����
		String echostr = request.getParameter("echostr");//����ַ���
		System.out.println(signature);
		System.out.println(timestamp);
		System.out.println(nonce);
		System.out.println(echostr);
		// TODO Auto-generated method stub
//		WXBizMsgCrypt wbmc;
//		try {
//			wbmc = new WXBizMsgCrypt("tk4shefb", "yFyphSJWpgWHAVcEQgRERnczQss3uDOJcfoGhNeaDaT", "wxfb370c4b26f7989c");
//			System.out.println(wbmc.verifyUrl(signature, signature, nonce, echostr));
//		} catch (AesException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	     String[] str = { "tk4shefb", timestamp, nonce };  
	     Arrays.sort(str); // �ֵ�������  
	     String bigStr = str[0] + str[1] + str[2];  
	     // SHA1����  
		try {
			MessageDigest md = MessageDigest.getInstance("sha1");
			byte[] digest = md.digest(bigStr.getBytes());
			System.out.println(bytesToHexString(digest));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     //String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
	  
	     // ȷ����������΢��  
	     if ("".equals(signature)) {  
	         response.getWriter().print(echostr);  
	     }  
		
		response.getWriter().append(echostr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
        	System.out.println("--------");
        	System.out.println(src[i]);
        	System.out.println(Integer.toBinaryString(src[i]));
            int v = src[i] & 0xFF;
            System.out.println(v);
            System.out.println(Integer.toBinaryString(v));
            String hv = Integer.toHexString(v);
            System.out.println(hv);
            System.out.println("--------");
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


}
