package club.banyuan.Tool;

import club.banyuan.BillServer.BillServer;
import club.banyuan.ProviderServer.ProviderServer;
import club.banyuan.UserServer.UserServer;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.Properties;



/**
 * @author edz
 * @version 1.0
 * @date 2020/12/14 1:53 下午
 */
public class Config {
    public static String BasePath;
    public static String UserData;
    public static String ProviderData;
    public static String BillData;
    public static int Port;
    public static int UserCount;
    public static int ProviderCount;
    public static int BillCount;
    private static final Properties properties = new Properties();
    private static final File file = new File("java-banyuan/Manager/src/app.properties");
    static {
//        File file = new File("java-banyuan/Manager/config.xml");
//        SAXReader reader = new SAXReader();
//        try {
//            final Document read = reader.read(file);
//            final Element root = read.getRootElement();
//            BasePath= root.elementText("basepath");
//            Port = Integer.parseInt(root.elementText("port"));
//            UserCount = Integer.parseInt(root.elementText("UserCount"));
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
        try (InputStream inputStream = new FileInputStream(file)){
            properties.load(inputStream);
            BasePath = properties.getProperty("BasePath");
            UserData = properties.getProperty("UserData");
            ProviderData = properties.getProperty("ProviderData");
            BillData = properties.getProperty("BillData");
            Port = Integer.parseInt(properties.getProperty("port"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserCount = new UserServer().findIdMax();
        ProviderCount = new ProviderServer().findMax();
        BillCount = new BillServer().findMax();
    }

}
