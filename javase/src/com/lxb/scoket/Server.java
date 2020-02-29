package com.lxb.scoket;

/*
*服务器端代码
*
*/
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
 
public class Server
{
    public static void main(String[] args)
    {
        new Server();
    }
 
    public Server()
    {
        try
        {
            ServerSocket ss = new ServerSocket(8888);
            while (true)
            {
                // 服务器接收消息
                Socket s = ss.accept();
                InputStreamReader isr = new InputStreamReader(s
                        .getInputStream());
                BufferedReader in = new BufferedReader(isr);
                String line = in.readLine();
                System.out.println("Client发送的消息是:  " + line);
 
                // 把成功信息反馈回客户端
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                out.println("已经接受到消息了!");
 
                out.close();
                in.close();
                isr.close();
                s.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
 
    }
}