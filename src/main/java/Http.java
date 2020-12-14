
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import view.Api;
public class Http {

    public static void start(String[] args) throws Exception {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(threadPoolExecutor);
        server.start();
    }
    public static String[] parseUrl(HttpExchange t){
        String path= t.getRequestURI().toString();
        System.out.println(path);
        Boolean flag=false;
        String[] res=new String[20];
        int arrCounter=0;
        String temp=new String();
        for (int i=0;i<path.length();i++){
            if (path.charAt(i)=='/'){
                if(flag){
                    System.out.println(temp);
                    res[arrCounter]=temp;
                    temp="";
                    arrCounter=arrCounter+1;
                }else {
                    flag=true;
                }
            }else{
                temp=temp+path.charAt(i);
            }
        }
        return res;
    }

    public static String mapToView(String[] url,HttpExchange t){
        switch (url[0]){
            default :
                return Api.home();
        }
    }
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String[] urlParsed=parseUrl(t);
            String response = mapToView(urlParsed,t);
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}

