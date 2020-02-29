package com.lxb.scoket;

/*
*
*客户端代码
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
 
public class Client
{
    public static void main(String[] args)
    {
        new Client();
    }
 
    public Client()
    {
        try
        {
            System.out.println("正在连接Server中");
 
            Socket socket = new Socket("localhost", 8888);
 
            System.out.println("连接成功!");
            System.out.println();
 
            // 向服务器发消息
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    System.in));
            System.out.print("请输入信息:");
            out.println(br.readLine());
 
            // 接受服务器端消息
            BufferedReader in = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));
            System.out.println("Server回复的消息:  " + in.readLine());
 
            br.close();
            out.close();
            in.close();
            socket.close();
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
