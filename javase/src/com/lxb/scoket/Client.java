package com.lxb.scoket;

/*
*
*�ͻ��˴���
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
            System.out.println("��������Server��");
 
            Socket socket = new Socket("localhost", 8888);
 
            System.out.println("���ӳɹ�!");
            System.out.println();
 
            // �����������Ϣ
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    System.in));
            System.out.print("��������Ϣ:");
            out.println(br.readLine());
 
            // ���ܷ���������Ϣ
            BufferedReader in = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));
            System.out.println("Server�ظ�����Ϣ:  " + in.readLine());
 
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
